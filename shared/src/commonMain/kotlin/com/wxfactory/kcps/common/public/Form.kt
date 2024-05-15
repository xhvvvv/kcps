package com.wxfactory.kcps.common.public

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.wxfactory.kcps.common.public.validate.ValidataeObject
import com.wxfactory.kcps.common.public.validate.Validator
import com.wxfactory.kcps.common.public.validate.intValidator
import kotlinx.serialization.Serializable
import java.text.Normalizer.Form
data class FormState(
    var validate:Boolean = true,
    var virfedCallBack: (String,Boolean)->Unit = { str,con -> } , //校验完成后的回调
){
    //控件的校验器
    val validators : MutableMap<String,MutableList<Validator>>  = mutableMapOf()
    val nowValues : MutableMap<String, ValidataeObject> = mutableMapOf()
    fun virfySingle(id:String):Boolean{
        val list  = validators[id]
        val value = nowValues[id]
        if (list!=null && value!=null){
            var str:String? = null
            var con : Boolean = true;
            for (v in list) {
                if (!v.virfy(value.value)){
                    con = false
                    if (str==null) str=""
                    str+= v.remindText
                    if (!v.keepOn) break
                }
            }
            if (con){
                value.error = null
                virfedCallBack(id,true)
            }else{
                value.error = str
                virfedCallBack(id,false)
                return false
            }
        }
        return true
    }

    fun virfyAll():Boolean{
        var con = 0
        validators.forEach { id, _ ->
            if (!virfySingle(id)){
                con++
            }
        }
        
        return con==0
    }
    fun regiest(
        id:String,
        value: ValidataeObject
    ){
        nowValues.put(id,value)
    }
    
    fun unRegiest( id:String? ){
        nowValues.remove(id)
    }

    fun add(id:String , validator:Validator){
        var v = validators.get(id)
        if(v==null) {
            v = mutableListOf()
            validators.put(id, v )
        }
        v!!.add(validator);
    }

    fun add(id:String , validator:List<Validator> ){
        var v = validators.get(id)
        if(v==null) {
            v = mutableListOf()
            validators.put(id, v )
        } 
        v!!.addAll(validator)
    }
    operator fun invoke(block:FormState.()->Unit){
        block()
    }

    infix fun String.useValidator(validator:Validator){
        add(this,validator )
    }
    
    infix fun String.useValidators(validators:List<Validator>){
        add(this,validators )
    }
}

val LocalFormState : ProvidableCompositionLocal<FormState> = compositionLocalOf<FormState> { FormState() }


/**
 * 模拟html表单校验器
 *  Form {
 *             val formState = LocalFormState.current
 *             LaunchedEffect(Unit){
 *                 //注册校验器
 *                 formState{
 *                     "configName" useValidators listOf(
 *                         nnullValidator,
 *                         MaxLValidator(10)
 *                     )
 *                     "port" useValidators listOf(
 *                         intValidator
 *                     )
 *                 }
 *             }
 * }
 * 可以任意注册校验器，如果存在控件id，则会校验，不存在则不会
 * 
 */
@Composable
fun Form(
    content: @Composable () -> Unit
){
    CompositionLocalProvider(LocalFormState provides FormState()) {
        content()
    }
}