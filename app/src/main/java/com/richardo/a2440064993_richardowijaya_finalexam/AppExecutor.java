package com.richardo.a2440064993_richardowijaya_finalexam;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutor {
    private static AppExecutor instance;
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public static AppExecutor getInstance()
    {
        if(instance==null)
        {
            instance = new AppExecutor();
        }
        return instance;
    }

    public ScheduledExecutorService getmNetworkIO() {
        return mNetworkIO;
    }
}
