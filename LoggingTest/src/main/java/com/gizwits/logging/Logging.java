package com.gizwits.logging;

import com.gizwits.annotation.GizwitsLogging;

/**
 * Created by feel on 2017/1/22.
 */
public class Logging {


    @GizwitsLogging
    public void parse() {

        System.out.println("Logging.parse");

    }

    @GizwitsLogging
    public Double pay(double money) {


        return money;
    }
}
