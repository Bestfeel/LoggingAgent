
package com.gizwits.agent;


import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static com.gizwits.agent.LoggingUtils.logger;

public class LoggingInterceptor {


    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @AllArguments Object args[],
                                   @SuperCall Callable<?> callable) {

        long start = System.currentTimeMillis();
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            logger(method.getDeclaringClass(), "方法调用method:{},value:{},耗时:{}毫秒", method, Arrays.toString(args), (System.currentTimeMillis() - start));
        }
    }


}
