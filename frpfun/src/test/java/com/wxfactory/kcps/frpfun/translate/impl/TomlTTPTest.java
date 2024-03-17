package com.wxfactory.kcps.frpfun.translate.impl;

import com.wxfactory.kcps.frpfun.entity.FrpConfigC;
import com.wxfactory.kcps.frpfun.entity.frpconfigcs.TcpFcc;
import com.wxfactory.kcps.frpfun.translate.ToTypeProcessor;
import org.junit.jupiter.api.Test;

/**
 * @author xhvvvv
 * @time 2024/3/16
 */
public class TomlTTPTest {
    /**
     * 
     */
    @Test
    public void xv_IJEUN2tP(){
        ToTypeProcessor<FrpConfigC> toTypeProcessor = new TomlTTP();
        TcpFcc tcpFcc = new TcpFcc();
        
        toTypeProcessor.transfer()
    }
    
    
}
