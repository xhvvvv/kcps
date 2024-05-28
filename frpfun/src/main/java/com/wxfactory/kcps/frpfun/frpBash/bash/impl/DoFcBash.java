package com.wxfactory.kcps.frpfun.frpBash.bash.impl;

import com.wxfactory.kcps.frpfun.frpBash.ExcuteEntity;
import com.wxfactory.kcps.frpfun.frpBash.StartException;
import com.wxfactory.kcps.frpfun.frpBash.bash.ExpandExecuteResultHandler;
import com.wxfactory.kcps.frpfun.frpBash.bash.MyExecuteResultHandler;
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteResultHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 客户端的执行
 * @author xhvvvv
 * @time 2024/4/13
 */
public class DoFcBash {
    public static ExcuteCon startFc(ExcuteEntity ec, ExpandExecuteResultHandler handler) throws StartException {
        ExcuteCon excuteCon = new ExcuteCon(ec);
        final MyExecuteResultHandler resultHandler = new MyExecuteResultHandler(handler);
        DefaultExecutor executor =  DefaultExecutor.builder().get();
        // 重定向stdout和stderr到文件
        File f = new File(ec.getWorkDir(),"log/log.txt");
        FileOutputStream fileOutputStream = null ; 
        if (!f.exists()){
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
                fileOutputStream = new FileOutputStream( f );
            } catch (IOException e) {
                throw new StartException(4 , "无法创建日志文件"+f.getAbsolutePath() , e);
            }
        }
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(fileOutputStream);
        ExecuteWatchdog executeWatchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
        executor.setStreamHandler(pumpStreamHandler);
        executor.setWatchdog(executeWatchdog);
        StringBuilder sb = new StringBuilder();
        sb.append(ec.getExcuteFile().getAbsolutePath())
                .append(" ")
                .append("-c ")
                .append(ec.getConfigFile());
        CommandLine cl = CommandLine.parse(sb.toString());
        excuteCon.setExecuteWatchdog(executeWatchdog);
        excuteCon.setFileOutputStream(fileOutputStream);
        try {
            executor.execute(cl,resultHandler);
        } catch (IOException e) {
            throw new StartException(0 , "启动失败，请检查配置" , e);
        }
        excuteCon.setExcuteDone(true);
        return excuteCon;
    }
    public static ExcuteCon startFs(ExcuteEntity ec, ExpandExecuteResultHandler handler) throws StartException {
        ExcuteCon excuteCon = new ExcuteCon(ec);
        final MyExecuteResultHandler resultHandler = new MyExecuteResultHandler(handler);
        DefaultExecutor executor =  DefaultExecutor.builder().get();
        // 重定向stdout和stderr到文件
        File f = new File(ec.getWorkDir(),"log/logs.txt");
        FileOutputStream fileOutputStream = null ;
        if (!f.exists()){
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
                fileOutputStream = new FileOutputStream( f );
            } catch (IOException e) {
                throw new StartException(4 , "无法创建日志文件"+f.getAbsolutePath() , e);
            }
        }
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(fileOutputStream);
        ExecuteWatchdog executeWatchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
        executor.setStreamHandler(pumpStreamHandler);
        executor.setWatchdog(executeWatchdog);
        StringBuilder sb = new StringBuilder();
        sb.append(ec.getExcuteFile().getAbsolutePath())
                .append(" ")
                .append("-c ")
                .append(ec.getConfigFile());
        CommandLine cl = CommandLine.parse(sb.toString());
        excuteCon.setExecuteWatchdog(executeWatchdog);
        excuteCon.setFileOutputStream(fileOutputStream);
        try {
            executor.execute(cl,resultHandler);
        } catch (IOException e) {
            throw new StartException(0 , "启动失败，请检查配置" , e);
        }
        excuteCon.setExcuteDone(true);
        return excuteCon;
    }
}
