
package com.gizwits.agent;


import com.gizwits.annotation.GizwitsLogging;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * 参考官方文档：https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/package-summary.html
 */
public class AppAgent {

    public static void premain(String arg, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.any())
                .transform((builder, type, classLoader, module) -> builder.method(ElementMatchers.isAnnotatedWith(GizwitsLogging.class))
                        .intercept(MethodDelegation.to(LoggingInterceptor.class)
                        )
                )
                .installOn(inst);
    }


}
