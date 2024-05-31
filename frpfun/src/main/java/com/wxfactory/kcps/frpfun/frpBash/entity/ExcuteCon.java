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
    private Boolean excuteDone = false;//执行是否完成，执行结果是未知的，这里仅能作为是否执行完成的参考
    private String  remark = null;//执行结果备注
    
    /**
     * 判断执行结果是否成功
     * @return
     */
    public Boolean isExcuteSuccess(){
        if(excuteDone && executeWatchdog !=null && executeWatchdog.isWatching()){
            return true; 
        }else {
            return false;
        }
    }
    
    public ExcuteCon(Boolean excuteDone, String remark) {
        this.excuteDone = excuteDone;
        this.remark = remark;
    }
}
