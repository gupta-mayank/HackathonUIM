package com.ca.aiops.loganalyzer;

import com.ca.aiops.DataSpikeThread.DataControl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogStatementProcessor {

    static ExecutorService executor = Executors.newFixedThreadPool(5);
    static public DataControl dc;

    public static synchronized void processStatement(LogStatement logStatement) {
        Runnable runnable = new LogStatementWorker(logStatement);
        executor.execute(runnable);
    }
}
