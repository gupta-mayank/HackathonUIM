package com.ca.aiops.Prediction;

import com.ca.aiops.Commons;
import com.ca.aiops.DataTableCollection;
import com.ca.aiops.DatabaseInteraction.DBConnection;
import com.ca.aiops.DatabaseInteraction.PredictionTable;
import com.ca.aiops.Remediation.RemediationStatus;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class PredictionCommitRateWorker implements Runnable {

    public static void setId(int newValue) {
        id.set(newValue);
    }

    public static AtomicInteger id = new AtomicInteger();

    public void run() {
        try {
                long time = System.currentTimeMillis() + 3600000;
                double valuePredictedForCommitrate = PredictionProcessor.breachPoint(time);
                System.out.println("probable commit rate after 1 hour: " + valuePredictedForCommitrate);
                if(valuePredictedForCommitrate >= Commons.predictionValue) {
                    PredictionTable pt = new PredictionTable();
                    Date currentDate = new Date(time);
                    DateFormat df = new SimpleDateFormat("dd,MMM yyyy HH:mm:ss");
                    pt.setPredictionId(id.getAndIncrement());
                    pt.setPredictionName("Date Engine slowing down");
                    pt.setPredictionDescription("Data Engine data rate will breach limit by " + df.format(currentDate));
                    pt.setTimestamp(System.currentTimeMillis());
                    pt.setRemediation_status(RemediationStatus.NOT_REMEDIATED.toString());
                    DBConnection.insertPrediction(DataTableCollection.conn,pt);
                }
        }
        catch(Exception e) {
            System.out.println("Something failed");
        }
    }
}
