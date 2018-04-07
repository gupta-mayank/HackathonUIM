package com.ca.aiops.DataSpikeThread;

import com.ca.aiops.Prediction.PredictionProcessor;
import com.ca.aiops.loganalyzer.LogStatementProcessor;

import java.io.File;

public class CheckForLogFile implements Runnable {

    private String fileName;

    public void run() {

        //Check for file existence
        File file = new File("C:\\test.txt");
        if(file.exists()) {
           // System.out.println("Spiking up the data");
            if(LogStatementProcessor.dc != DataControl.SPIKE) {
                LogStatementProcessor.dc = DataControl.SPIKE;
                PredictionProcessor.clearRegressionData();
            }
        }
        else {
            if(LogStatementProcessor.dc != DataControl.LOW) {
                LogStatementProcessor.dc = DataControl.LOW;
                PredictionProcessor.clearRegressionData();
            }
        }
    }
}
