package com.wxfactory.kcps.frpfun.translate;

import cn.hutool.core.io.FileUtil;
import com.wxfactory.kcps.frpfun.entity.FrpConfig;
import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.translate.impl.TomlTTP;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author xhvvvv
 * @time 2024/4/13
 */
public class TranslateTool {
    private static ToTypeProcessor ttp = new TomlTTP();
    /**
     * 将配置翻译到指定的文件当中去
     * //默认为toml格式
     * 
     * @author xhvvvv
     * @date 2024/4/13
     * @param fc 配置
     * @param aimFile 目标位置，工作目录
     * @param alone 连带配置翻译
     * @return
     */
    public static File doIt(FrpConfigC fc , File aimFile , boolean alone , ConfigTypes type ){
        File configF = new File(aimFile,fc.getName()+"."+type.name().toLowerCase());
        StringBuilder sb  = new StringBuilder();
        if (ConfigTypes.JSON.equals(type)){
            
        }else{
            if (alone){
                sb.append(ttp.handleHead(fc));
                ttp.handleSpecifyFcc(fc,sb);
            }else{
                sb.append(ttp.transfer(fc));
            }
        }
        
        FileUtil.writeString(sb.toString() ,  configF, StandardCharsets.UTF_8);
        return configF;
    }
    
}
