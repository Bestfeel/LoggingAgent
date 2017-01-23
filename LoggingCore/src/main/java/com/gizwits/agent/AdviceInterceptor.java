package com.gizwits.agent;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.gizwits.agent.LoggingUtils.logger;

/**
 * Created by feel on 2017/1/23.
 */
public class AdviceInterceptor {

    @Advice.OnMethodEnter
    static long enter(@Advice.This Object obj, @Advice.AllArguments Object args[], @Advice.Origin Method method) {

        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit(onThrowable = RuntimeException.class)
    static void exit(@Advice.Enter long startTime,
                     @Advice.This Object obj,
                     @Advice.AllArguments Object args[],
                     @Advice.Origin Method method,
                     @Advice.Thrown Throwable t) {


        logger(method.getDeclaringClass(), "方法调用method:{},value:{},耗时:{}毫秒", method, Arrays.toString(args), (System.currentTimeMillis() - startTime));

    }

}
