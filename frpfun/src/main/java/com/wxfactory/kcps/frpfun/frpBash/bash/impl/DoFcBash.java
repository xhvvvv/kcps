package com.wxfactory.kcps.frpfun.frpBash.bash.impl;

import com.wxfactory.kcps.frpfun.frpBash.ExcuteEntity;
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 客户端的执行
 * @author xhvvvv
 * @time 2024/4/13
 */
public class DoFcBash {
    public static ExcuteCon startFc(ExcuteEntity ec) throws IOException {
        ExcuteCon excuteCon = new ExcuteCon(ec);
        DefaultExecutor executor =  DefaultExecutor.builder().get();
        // 重定向stdout和stderr到文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File(ec.getWorkDir(),"log/log.txt") );
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(fileOutputStream);
        ExecuteWatchdog executeWatchdog = new ExecuteWatchdog(6000);
        executor.setStreamHandler(pumpStreamHandler);
        executor.setWatchdog(executeWatchdog);
        
        StringBuilder sb = new StringBuilder();
        sb.append(ec.getExcuteFile().getAbsolutePath())
                .append(" ")
                .append("-c ")
                .append(ec.getConfigFile());
        CommandLine cl = CommandLine.parse(sb.toString());
        int exitValue = executor.execute(cl);
        
        excuteCon.setExecuteWatchdog(executeWatchdog);
        excuteCon.setFileOutputStream(fileOutputStream);
        return excuteCon;
    }
}
