package com.wxfactory.kcps.frpfun.frpBash;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xhvvvv
 * @time 2024/5/28
 */
@Getter
@Setter
public class StartException extends RuntimeException{
    
    /**
     * 0 : 未知异常
     * 1 : 启动文件未找到
     * 2 : 启动文件配置有问题
     * 3 : 系统资源问题，端口异常，文件资源...
     **/
    private Integer code;
    
    public StartException(Integer code , String message, Throwable cause ) {
        super(message, cause );
        this.code = code;
    }
    public StartException(Integer code , String message ) {
        super(message );
        this.code = code;
    }
}
