package com.ca.aiops.Prediction;

import com.ca.aiops.DatabaseInteraction.DataTable;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class PredictionProcessor {

    //static ExecutorService executor = Executors.newFixedThreadPool(5);
    private static SimpleRegression linearRegression = new SimpleRegression();

    public static synchronized void addPredictionData(DataTable dt) {
        linearRegression.addData(dt.getCommitDateTime(),dt.getTimetakenForInsertion());
    }

    public static double breachPoint(long timestamp) {
        return linearRegression.predict(timestamp);
    }

    public static void clearRegressionData() {
        linearRegression.clear();
    }
}
