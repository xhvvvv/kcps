package com.wxfactory.kcps.common.public.validate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cn.hutool.core.util.StrUtil
import org.apache.commons.validator.routines.DoubleValidator
import org.apache.commons.validator.routines.InetAddressValidator
import org.apache.commons.validator.routines.IntegerValidator
val nnullValidator= object : NnullValidator(){}
val emailValidator= object : EmailValidator(){}
val numberValidator= object : NumberValidator(){}
val ipValidator= object : IpValidator(){}
val intValidator= object : IntValidator(){}
class ValidataeObject(primitV:String?) {
    var value:String? by mutableStateOf(primitV)
    var error:String? by mutableStateOf(null)
   
}




abstract class Validator(val remindText:String) {
    
    //此校验器校验没通过也继续下一个校验器
    var keepOn :Boolean = true
    abstract fun virfy(obj : String?):Boolean
}

open class EmailValidator(remindText:String = "请输入正确的邮箱格式！")  : Validator(remindText) {
    val v :org.apache.commons.validator.routines.EmailValidator = org.apache.commons.validator.routines.EmailValidator. getInstance(true)
    override fun virfy(obj: String?): Boolean {
        if (obj == null ) return true
        else return v.isValid(obj);
    }

}
open class NnullValidator(remindText:String = "请输入！")  : Validator(remindText) {
    init {
        keepOn = false
    }
    override fun virfy(obj: String?): Boolean {
        return StrUtil.isNotEmpty(obj);
    }
}

open class NumberValidator(remindText:String = "请输入数字形式！")  : Validator(remindText) {
    val v:DoubleValidator = DoubleValidator()
    override fun virfy(obj: String?): Boolean {
        if (obj == null ) return true
        else return v.isValid(obj);
    }
}

open class IntValidator(remindText:String = "请输入整数形式！")  : Validator(remindText) {
    val v: IntegerValidator = IntegerValidator()
    override fun virfy(obj: String?): Boolean {
        if (obj == null ) return true
        else return v.isValid(obj);
    }
}

open class MinLValidator(val minL: Int  ,   remindText:String = "请输入长度最少为${minL}位字符！")  : Validator(remindText) {
    override fun virfy(obj: String?): Boolean {
        if (obj!=null){
            if (obj.length < minL){
                return false;
            }
        }
        return  true
    }
}

open class MaxLValidator(val maxL: Int  ,   remindText:String = "请输入长度最多为${maxL}位字符！")  : Validator(remindText) {
    override fun virfy(obj: String?): Boolean {
        if (obj!=null){
            if (obj.length > maxL){
                return false;
            }
        }
        return  true
    }
}

open class IpValidator( remindText:String = "请输入正确的ip字符！")  : Validator(remindText) {

    val v: InetAddressValidator = InetAddressValidator()
    override fun virfy(obj: String?): Boolean {
        if (obj == null ) return true
        else return v.isValid(obj);
    }
}  