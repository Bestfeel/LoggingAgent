package com.gizwits.main;

import com.gizwits.logging.Logging;

/**
 * Created by feel on 2017/1/22.
 */
public class App {
    public static void main(String[] args) {


        Logging logging = new Logging();

        logging.parse();

        logging.pay(100.0);

    }
}
