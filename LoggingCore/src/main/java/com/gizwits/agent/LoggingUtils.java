package com.gizwits.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by feel on 2017/1/23.
 */
public class LoggingUtils {

    public static void logger(Class clazz, String msg, Object... args) {
        Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
        if (clazz != null) {
            logger = LoggerFactory.getLogger(clazz);
        }
        logger.info(msg, args);

    }
}
