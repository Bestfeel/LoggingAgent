
package com.gizwits.agent;


import com.gizwits.annotation.GizwitsLogging;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;
import static net.bytebuddy.matcher.ElementMatchers.none;

/**
 * 参考官方文档：https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/package-summary.html
 */
public class AppAgent {

    public static void premain(String args, Instrumentation inst) {

        loadAdvice(inst);
    }

    public static void agentmain(String args, Instrumentation inst) {

        loadAdviceAttach(inst);
    }

    /**
     * byte buddy包中的 build.method(...).intercept(...)
     * byte buddy包中的 advice注解
     * <p>
     * build.method(...).intercept(...) 这种方式其实是对真实的方法做了一层代理， 这种方式会影响代码的执行流。但是使用起来相对比advice注解更加直接和方便。 并且这种方式可以很方便的进行调试，方便于代码的开发
     * advice注解 这种方式是在启动的时候，通过asm来注入字节码，这种方式是不会影响和改变原始代码的执行流， 并且在初始化的时候就把相关注解的代码植入到字节码中。
     * </p>
     *
     * @param inst
     */
    private static void load(Instrumentation inst) {


        new AgentBuilder.Default()
                .type(nameStartsWith("com.gizwits")) // 扫描指定包下的文件
                //.type(ElementMatchers.any())
                .transform((builder, type, classLoader, module) -> builder.method(ElementMatchers.isAnnotatedWith(GizwitsLogging.class))
                        .intercept(MethodDelegation.to(LoggingInterceptor.class))

                )
                .with(new AgentListener())
                .installOn(inst);


    }

    /**
     * 通过advice注解
     *
     * @param inst
     */
    private static void loadAdvice(Instrumentation inst) {
        new AgentBuilder.Default()
                .type(nameStartsWith("com.gizwits")) // 扫描指定包下的文件
                .transform((builder, type, classLoader, module) ->
                        builder.visit(Advice.to(AdviceInterceptor.class).on(ElementMatchers.isAnnotatedWith(GizwitsLogging.class)))
                )
                .with(new AgentListener())
                .installOn(inst);
    }

    /**
     * 通过advice注解
     *
     * @param inst
     */
    private static void loadAdviceAttach(Instrumentation inst) {
        new AgentBuilder.Default()
                .ignore(none())
                .disableClassFormatChanges()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .type(nameStartsWith("com.gizwits")) // 扫描指定包下的文件
                .transform((builder, type, classLoader, module) ->
                        builder.visit(Advice.to(AdviceInterceptor.class).on(ElementMatchers.isAnnotatedWith(GizwitsLogging.class)))
                )
                .with(new AgentListener())
                .installOn(inst);
    }
}
