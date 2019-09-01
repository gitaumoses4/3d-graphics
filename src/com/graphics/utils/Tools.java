package com.graphics.utils;

import java.util.Timer;
import java.util.TimerTask;

public class Tools {
    public interface TimerCallback {
        public void run();
    }

    public static void setInterval(TimerCallback callback, int interval) {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                callback.run();
            }
        }, 0, interval);
    }
}
