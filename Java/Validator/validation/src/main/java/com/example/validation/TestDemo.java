package com.example.validation;


import com.example.validation.config.ValidationHelper;
import com.example.validation.entity.Address;
import com.example.validation.entity.UserInfo;
import com.example.validation.entity.group.TestGroupBean;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * hibernate validation 的官网有许多示例
 */
public class TestDemo {


    @Test
    public void t1() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("       ");
        userInfo.setAge(-1);
        userInfo.setEmail("a@gmial.com");

        // 放个未来日期试试
        userInfo.setRegisterTime(LocalDateTime.now().plusDays(1));
        List<String> violationList = ValidationHelper.validate(userInfo);
        if (violationList.size() > 0) {
            violationList.stream().forEach(System.out::println);
        }
    }

    /**
     * 测试分组校验的效果，注意继承 Default
     */
    @Test
    public void testGroup() {
        TestGroupBean testGroupBean = new TestGroupBean(-1L);
        // 这里用 validateByGroup
        // 场景为新增，且 id 不为 null，而且为负数，此时应当报一个 Add 分组和一个 Default 分组的错误
        List<String> violationList_Add = ValidationHelper.validateByGroup(testGroupBean, TestGroupBean.Add.class);
        if (violationList_Add.size() > 0) {
            violationList_Add.stream().forEach(System.out::println);
        }

        System.out.println("----------------------------------------");

        // 场景为更新，此时只报一个 Update 分组的错误，因为 Update 没有继承自 Default 所有不会校验 Default 分组内容
        testGroupBean.setId(-1L);
        List<String> violationList_update = ValidationHelper.validateByGroup(testGroupBean, TestGroupBean.Update.class);
        if (violationList_update.size() > 0) {
            violationList_update.stream().forEach(System.out::println);
        }
    }

    /**
     * 测试 @valid 对于组合的效果
     * 可以屏蔽 @valid 来测试
     */
    @Test
    public void testValid() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("eor");
        userInfo.setEmail("zqh123196@gmail.com");
        userInfo.setAge(1);
        userInfo.setRegisterTime(LocalDateTime.now());
        userInfo.setUserSatus(1000);

        // 去掉对应的 @valid 之后即使传入未初始化的对象也不会校验
        // 存在 @valid 时才会去扫描组合类的内部校验规则
        userInfo.setAddress(new Address());

        List<String> violationList = ValidationHelper.validate(userInfo);
        if (violationList.size() > 0) {
            violationList.stream().forEach(System.out::println);
        }
    }

    @Test
    public void testCustom() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("eor");
        userInfo.setEmail("zqh123196@gmail.com");
        userInfo.setAge(1);
        userInfo.setRegisterTime(LocalDateTime.now());
        userInfo.setAddress(new Address(){{setProvince("海南");}});

        // 通不过
        userInfo.setUserSatus(1);

        List<String> violationList = ValidationHelper.validate(userInfo);
        if (violationList.size() > 0) {
            violationList.stream().forEach(System.out::println);
        }
    }


}
