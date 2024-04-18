package com.wxfactory.kcps.frpfun.frpBash.bash;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;

/**
 * @author xhvvvv
 * @time 2024/4/18
 */
public interface ExpandExecuteResultHandler extends ExecuteResultHandler{
    @Override
    default void onProcessComplete(int exitValue) {
        onCons(exitValue,null);
    }
    
    @Override
    default void onProcessFailed(ExecuteException e) {
        onCons( null,e);
    }
    
    public void onCons(Integer exitValue,ExecuteException e);
}
