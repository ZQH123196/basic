package com.example.rbac1.conf;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.rbac1.dao.entity.Permission;
import com.example.rbac1.dao.entity.Role;
import com.example.rbac1.dao.entity.RolePermission;
import com.example.rbac1.dao.service.impl.RolePermissionServiceImpl;
import com.example.rbac1.service.impl.ServiceCommonImpl;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class AuthInterceptor implements HandlerInterceptor {

    @Value("${Auth.http.head.authHeadName}")
    String headAuthTokenName;

    @Value("${Auth.http.head.loginName}")
    String headLoginName;

    @Value("${Auth.http.head.loginPass}")
    String headLoginPass;


    /**
     * 如果是登录接口就直接放过
     * 如果不是登录接口全部归为 token 校验，但是这里是测试，因此 token 直接放过
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (testLoginReq(request)) {
            refreshAdminPerm();
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        if (testToken(request)) {
            // 这个本来是不需要的，但是这里没有做真正的 token 解析，也就不会从 token 中拿到可用的权限
            refreshAdminPerm();
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } else {
            throw new SecurityException("Token 校验失败！");
        }

    }


    @Resource
    DataSourceTransactionManager trManager;

    @Resource
    TransactionDefinition transactionDefinition;

    @Resource
    ServiceCommonImpl serviceCommonImpl;


    @Resource
    RolePermissionServiceImpl rolePermissionService;

    /**
     * 确保本次进入时，admin 能获取当前时刻所有的权限
     * 取所有的权限作为全集，取当前 admin 的权限作为子集，比较获得差集，然后保存差集到数据库。
     */
    void refreshAdminPerm() {
        Role adminRole = new Role().selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getRole, "admin"));
        TransactionStatus trStatus = trManager.getTransaction(transactionDefinition);

        try {
            HashMap<String, List<Permission>> rolePermMap = serviceCommonImpl.getRolePerm(adminRole.getRole());
            List<Permission> allPermissionList = new Permission().selectAll().stream().collect(Collectors.toList());
            List<Permission> curAdminPermList = rolePermMap.get(adminRole.getRole());
            if (allPermissionList == null) {
                allPermissionList = Lists.newArrayList();
            }
            if (curAdminPermList == null) {
                curAdminPermList = Lists.newArrayList();
            }

            Set<Integer> allPermMap = allPermissionList.stream().map(p -> p.getId()).collect(Collectors.toSet());
            Set<Integer> curPermMap = curAdminPermList.stream().map(p -> p.getId()).collect(Collectors.toSet());

            // 取在左不在右的集合
            ImmutableSet<Integer> diffPermSet = Sets.difference(allPermMap, curPermMap).immutableCopy();
            Set<RolePermission> beUpdatePermList = diffPermSet.stream()
                    .map(pid -> RolePermission
                            .builder()
                            .role(adminRole.getRole())
                            .permissionId(pid)
                            .build())
                    .collect(Collectors.toSet());
            boolean saveNum = rolePermissionService.saveBatch(beUpdatePermList);
            trManager.commit(trStatus);
        } catch (RuntimeException e) {
            e.printStackTrace();
            trManager.rollback(trStatus);
        }

    }

    private boolean testToken(HttpServletRequest request) {
        return StrUtil.isNotEmpty(headLoginName) && StrUtil.isNotEmpty(headLoginPass);
    }

    private boolean testLoginReq(HttpServletRequest request) {
        return StrUtil.isNotEmpty(headLoginName) && StrUtil.isNotEmpty(headLoginPass)
                && StrUtil.contains(request.getRequestURI(), "login");
    }
}
