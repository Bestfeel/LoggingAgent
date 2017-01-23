package com.gizwits.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;


/**
 * A listener that is informed about events that occur during an instrumentation process
 */
public class AgentListener implements AgentBuilder.Listener {

    @Override
    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {


        // System.out.println("AgentListener.onTransformation");
    }

    @Override
    public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

        //System.out.println("AgentListener.onIgnored");

    }

    @Override
    public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {


        //  System.out.println("AgentListener.onError");
    }

    @Override
    public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {


        // System.out.println("AgentListener.onComplete");
    }
}