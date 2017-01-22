
package com.gizwits.agent;


import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class LoggingInterceptor {


    private static void logger(Class clazz, String msg, Object... args) {
        Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
        if (clazz == null) {
            logger = LoggerFactory.getLogger(clazz);
        }
        logger.info(msg, args);

    }

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
            logger(method.getDeclaringClass(), "方法调用method:{},value:{},耗时:{}", method, Arrays.toString(args), (System.currentTimeMillis() - start));
        }
    }
}
