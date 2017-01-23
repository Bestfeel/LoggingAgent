package com.gizwits.main;

import com.gizwits.logging.Logging;

/**
 * Created by feel on 2017/1/22.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {


        Long sleepTime = 5 * 1000L * 60;
        long start = System.currentTimeMillis();
        Logging logging = new Logging();
        while (true) {

            logging.parse();

            logging.pay(100.0);


            Thread.sleep(1000L);
            long end = System.currentTimeMillis();

            if ((end - start) > sleepTime) {

                break;
            }
        }


    }
}
