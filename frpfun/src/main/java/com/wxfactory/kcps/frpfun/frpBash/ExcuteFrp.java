package com.wxfactory.kcps.frpfun.frpBash;

import java.io.File;
import java.io.IOException;

/**
 * 命令行执行
 * @author  xhvvvv
 * @time    2024/3/18
 */
public interface ExcuteFrp {
    
    Process excute(File sh , String[] parmes);
    
    
    /**
     * 默认的配置文件启动
     * @author xhvvvv
     * @date 2024/3/18
     * @param sh
     * @return
     */
    default Process excute(File sh) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder( );
        builder.command(sh.getAbsolutePath(), "-c" , "frpc.toml");
        // 启动进程
        Process process = builder.start();
        // 等待命令执行完成
        int exitCode = process.waitFor();
        return process;
    };
}
