package com.ca.aiops.Prediction;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PredictionExecutor {

    public static void predictionRunner() {
        ScheduledExecutorService runThis = Executors.newSingleThreadScheduledExecutor();
        runThis.scheduleAtFixedRate(new PredictionCommitRateWorker(), 0, 10, TimeUnit.SECONDS);
        ScheduledExecutorService runThis1 = Executors.newSingleThreadScheduledExecutor();
        runThis1.scheduleAtFixedRate(new ProcessorPrediction(),0,1,TimeUnit.MINUTES);
    }
}
