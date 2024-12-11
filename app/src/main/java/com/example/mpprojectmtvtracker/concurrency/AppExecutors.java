package com.example.mpprojectmtvtracker.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static final int THREAD_COUNT = 4; // Shared pool size
    private final ExecutorService executorService;

    private AppExecutors() {
        this.executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    private static final AppExecutors INSTANCE = new AppExecutors();

    public static ExecutorService getExecutorService() {
        return INSTANCE.executorService;
    }
}

