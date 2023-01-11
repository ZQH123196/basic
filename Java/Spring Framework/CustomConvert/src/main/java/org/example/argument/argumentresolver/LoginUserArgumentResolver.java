package org.example.argument.argumentresolver;

import org.apache.logging.log4j.util.Strings;
import org.example.argument.annotation.LoginUser;
import org.example.argument.controller.vo.LoginUserVo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return resolve(parameter, mavContainer, webRequest, binderFactory);
    }

    /**
     * 假设 id 在 url 中，username 在 head 中
     * @return
     */
    private LoginUserVo resolve(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        LoginUserVo loginUserVo = new LoginUserVo();
        String id = request.getParameter("id");
        String userName = request.getHeader("userName");

        loginUserVo.setId(id);
        loginUserVo.setName(userName);
        return loginUserVo;
    }
}
