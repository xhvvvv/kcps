package com.wxfactory.kcps.frpfun.entity.auths;

import com.wxfactory.kcps.frpfun.entity.Authentication;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xhvvvv
 * @time 2024/4/18
 */
@Getter
@Setter
public class TokenAuth extends Authentication {
    private String token ;
    public TokenAuth(MethodType method,String token) {
        super(method);
        this.token = token;
    }
}
