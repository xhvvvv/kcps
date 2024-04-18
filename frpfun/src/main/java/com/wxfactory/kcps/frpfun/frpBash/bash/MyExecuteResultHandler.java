package com.wxfactory.kcps.frpfun.frpBash.bash;

import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;

/**
 * @author xhvvvv
 * @time 2024/4/18
 */
public class MyExecuteResultHandler  extends DefaultExecuteResultHandler {
    private ExpandExecuteResultHandler handler ;
    
    public MyExecuteResultHandler(ExpandExecuteResultHandler handler) {
        this.handler = handler;
    }
    
    @Override
    public void onProcessComplete(int exitValue) {
        super.onProcessComplete(exitValue);
        handler.onProcessComplete(exitValue );
    }
    
    @Override
    public void onProcessFailed(ExecuteException e) {
        super.onProcessFailed(e);
        handler.onProcessFailed(e);
    }
}
