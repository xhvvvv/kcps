package com.wxfactory.kcps.frpfun.frpBash;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

/**
 * 命令行启动的数据载体
 * @author xhvvvv
 * @time 2024/4/13
 */
@Getter
@Setter
public class ExcuteEntity {
    
    /***工作目录*/
    private File workDir;
    /**执行文件*/
    private File excuteFile;
    /**配置文件*/
    private File configFile;
    /**除了配置文件、和执行文件的其它参数
    private List<String> parmas;*/
}
