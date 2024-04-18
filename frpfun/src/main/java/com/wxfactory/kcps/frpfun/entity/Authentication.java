package com.wxfactory.kcps.frpfun.entity;

import com.wxfactory.kcps.frpfun.entity.auths.MethodType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public abstract class Authentication implements Serializable {
    private static final long serialVersionUID = 8600227906425698299L;
    /**
     * {@link com.wxfactory.kcps.frpfun.entity.auths.MethodType}
     */
    private MethodType method;
    private List<String> additionalScopes;
    
    public Authentication(MethodType method){
        this.method = method;
    }
    
}
