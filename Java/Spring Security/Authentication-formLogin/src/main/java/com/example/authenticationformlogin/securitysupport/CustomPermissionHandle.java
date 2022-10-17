package com.example.authenticationformlogin.securitysupport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("ps")
@Slf4j
public class CustomPermissionHandle {
    public boolean authorization(String permissionExpress) {
        log.info("into [{}]，进行自定义鉴权。", CustomPermissionHandle.class.getSimpleName());
        return true;
    }
}
