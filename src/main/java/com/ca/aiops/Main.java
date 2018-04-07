package com.ca.aiops;


import com.ca.aiops.DatabaseInteraction.DBConnection;
import com.ca.aiops.DatabaseInteraction.ShutdownHook;
import com.ca.aiops.Prediction.PredictionExecutor;
import com.ca.aiops.UIMRest.FetchConfiguration;
import com.ca.aiops.loganalyzer.DataEngineLogAnalyzer;

public class Main {


    public static void main(String[] args) {

        DBConnection.getNextPrimaryKeyPrediction();

        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

        PredictionExecutor.predictionRunner();
        DataTableCollection.checkForFile();

        DataEngineLogAnalyzer deLogAn = new DataEngineLogAnalyzer();
        deLogAn.startTailingLog();
    }
}
