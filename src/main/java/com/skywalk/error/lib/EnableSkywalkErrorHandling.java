package com.skywalk.error.lib;

import com.skywalk.error.lib.config.CommonControllerAdvice;
import com.skywalk.error.lib.config.ErrorHandlingConfig;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ErrorHandlingConfig.class, CommonControllerAdvice.class})
public @interface EnableSkywalkErrorHandling {

}
