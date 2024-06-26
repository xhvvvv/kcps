package com.wxfactory.kcps.frpfun;

import cn.hutool.core.util.StrUtil;
import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.FrpConfigS;
import com.wxfactory.kcps.frpfun.frpBash.ExcuteEntity;
import com.wxfactory.kcps.frpfun.frpBash.StartException;
import com.wxfactory.kcps.frpfun.frpBash.bash.ExpandExecuteResultHandler;
import com.wxfactory.kcps.frpfun.frpBash.bash.impl.DoFcBash;
import com.wxfactory.kcps.frpfun.frpBash.entity.ExcuteCon;
import com.wxfactory.kcps.frpfun.entity.ConfigTypes;
import com.wxfactory.kcps.frpfun.translate.TranslateTool;

import java.io.File;
import java.util.Map;

/**
 * @author xhvvvv
 * @time 2024/4/13
 */
public class Fc2Start {
    /**必要：执行文件的位置 */
    public static final String EXE_LOCATION = "exeLocation";
    /**是否单独启动一个进程**/
    public static final String EXE_ALONE = "exeAlone";
    /**这个配置的工作目录**/
    public static final String EXE_WORK_DIR = "workDir";
    /**配置的文件格式**/
    public static final String EXE_CONFIG_TYPE = "configType";
    /**必要：进程执行完毕的回调方法 {@link com.wxfactory.kcps.frpfun.frpBash.bash.ExpandExecuteResultHandler}**/
    public static final String EXE_CALLBACK = "exeCallback";
    /**
     * 给我一个配置，我就能将它启动起来
     * @author xhvvvv
     * @date 2024/4/13
     * @param fc
     * @return
     */
    public static ExcuteCon start(FrpConfigC fc, Map<String, Object> others)  {
        ExcuteCon excuteCon ;
        try{
            String exeFile = (String) others.get(EXE_LOCATION);
            File   exeFileF = new File(exeFile);
            if (!exeFileF.exists()){
                excuteCon = new ExcuteCon(false,"执行文件不存在");
                return excuteCon;
            }
            ConfigTypes configType = (ConfigTypes) others.get(EXE_CONFIG_TYPE);
            Boolean exeAlone = (Boolean) others.get(EXE_ALONE);
            ExpandExecuteResultHandler executeResultHandler = (ExpandExecuteResultHandler) others.get(EXE_CALLBACK);
            exeAlone = (exeAlone==null ? true : false);
            //默认工作目录
            String workDir = (String) others.get(EXE_WORK_DIR);
            File workDirF ;
            if (StrUtil.isEmpty(workDir)){
                workDirF = new File(exeFileF.getParentFile(),fc.getId());
            }else{
                workDirF = new File(workDir);
            }
            //首先翻译
            File config = TranslateTool.doIt(fc , workDirF , exeAlone , configType);
            //然后启动
            ExcuteEntity ec = new ExcuteEntity();
            ec.setConfigFile(config);
            ec.setWorkDir(workDirF);
            ec.setExcuteFile(new File(exeFile));
            return DoFcBash.startFc(ec,executeResultHandler);
        }catch (StartException e){
            excuteCon = new ExcuteCon(false,e.getMessage());
            return excuteCon;
        }
        
    }
    /**
     * 给我一个配置服务端，我就能将它启动起来
     * @author xhvvvv
     * @date 2024/5/19
     */
    public static ExcuteCon startS(FrpConfigS fc, Map<String, Object> others)  {
        ExcuteCon excuteCon ;
        try{
            String exeFile = (String) others.get(EXE_LOCATION);
            File   exeFileF = new File(exeFile);
            if (!exeFileF.exists()){
                excuteCon = new ExcuteCon(false,"执行文件不存在");
                return excuteCon;
            }
            ConfigTypes configType = (ConfigTypes) others.get(EXE_CONFIG_TYPE);
            ExpandExecuteResultHandler executeResultHandler = (ExpandExecuteResultHandler) others.get(EXE_CALLBACK);
            //默认工作目录
            String workDir = (String) others.get(EXE_WORK_DIR);
            File workDirF ;
            if (StrUtil.isEmpty(workDir)){
                workDirF = new File(exeFileF.getParentFile(),fc.getId());
            }else{
                workDirF = new File(workDir);
            }
            //首先翻译
            File config = TranslateTool.doIt(fc , workDirF , false , configType);
            //然后启动
            ExcuteEntity ec = new ExcuteEntity();
            ec.setConfigFile(config);
            ec.setWorkDir(workDirF);
            ec.setExcuteFile(exeFileF);
            return DoFcBash.startFs(ec,executeResultHandler);
        }catch (StartException e){
            excuteCon = new ExcuteCon(false,e.getMessage());
            return excuteCon;
        }
    }
    
}
