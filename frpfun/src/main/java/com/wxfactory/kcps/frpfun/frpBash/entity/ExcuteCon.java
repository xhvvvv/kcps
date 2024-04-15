package com.wxfactory.kcps.frpfun.frpBash.entity;

import com.wxfactory.kcps.frpfun.frpBash.ExcuteEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.exec.ExecuteWatchdog;

import java.io.FileOutputStream;

/**
 * @author xhvvvv
 * @time 2024/4/13
 */
@Getter
@Setter
public class ExcuteCon {
    public ExcuteCon(ExcuteEntity excuteEntity) {
        this.excuteEntity = excuteEntity;
    }
    
    private ExcuteEntity excuteEntity;
    private ExecuteWatchdog executeWatchdog;
    private FileOutputStream fileOutputStream;
}
