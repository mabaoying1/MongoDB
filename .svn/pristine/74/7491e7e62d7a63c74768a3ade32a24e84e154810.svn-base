package com.healthpay.common.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于接口JSON校验一个字段是否为空
 * Description:
 * Filename   :   NotNull.java  
 * @author    :   yyl 
 * @version   :   1.0  
 * Create at  :   2016年6月25日 下午5:36:37  
 *  
 *
 */
@Target(value={ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    public String name() default "fieldName";
}
