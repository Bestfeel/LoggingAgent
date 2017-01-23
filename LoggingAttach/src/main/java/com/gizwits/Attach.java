package com.gizwits;


import com.sun.tools.attach.VirtualMachine;
import net.bytebuddy.agent.ByteBuddyAgent;

import java.io.File;

public class Attach {
    public static void main(String[] args) {


        if (args.length < 2) {
            System.out.println("agentPath[jar]  pid");
        }

        ByteBuddyAttach(args);

    }


    private static void ByteBuddyAttach(String[] args) {
        ByteBuddyAgent.attach(new File(args[0]), args[1], "com.sun.management.jmxremote");
    }

    private static void vmAttach(String[] args) throws Exception {

        VirtualMachine vm = null;
        String agentjarpath = args[0]; //agentjar路径
        vm = VirtualMachine.attach(args[1]);//目标JVM的进程ID（PID）

        vm.loadAgent(agentjarpath, "com.sun.management.jmxremote");
        vm.detach();
    }
}
