package com.ca.aiops.Prediction;

import com.ca.aiops.DataTableCollection;
import com.ca.aiops.DatabaseInteraction.DBConnection;
import com.ca.aiops.DatabaseInteraction.PredictionTable;
import com.ca.aiops.Remediation.RemediationStatus;
import com.ca.aiops.UIMRest.FetchConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessorPrediction implements Runnable {

    @Override
    public void run() {
        runPredictionOnProcessors();
    }

    public void runPredictionOnProcessors() {

        //Check the number of cores in the CPU
        int numberOfProcessors = Runtime.getRuntime().availableProcessors();
        int dataEngineThreadCount = Integer.valueOf(FetchConfiguration.getDataEngineThreadCount());

        if(dataEngineThreadCount < 2*numberOfProcessors) {
            PredictionTable pt = new PredictionTable();
            pt.setPredictionId(PredictionCommitRateWorker.id.getAndIncrement());
            pt.setPredictionName("Data Engine threads not configured with processor threads");
            pt.setPredictionDescription("Data Engine configured with threads:" + dataEngineThreadCount + " Processors Core count: " + numberOfProcessors);
            pt.setTimestamp(System.currentTimeMillis());
            pt.setRemediation_status(RemediationStatus.NOT_REMEDIATED.toString());
            DBConnection.insertPrediction(DataTableCollection.conn,pt);
        }
    }
}
