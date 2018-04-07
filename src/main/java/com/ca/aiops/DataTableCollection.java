package com.ca.aiops;

import com.ca.aiops.DataSpikeThread.CheckForLogFile;
import com.ca.aiops.DatabaseInteraction.DBConnection;
import com.ca.aiops.DatabaseInteraction.DataTable;
import com.ca.aiops.Prediction.PredictionProcessor;

import java.sql.Connection;
import java.util.concurrent.*;

public class DataTableCollection {

    public static Connection conn = DBConnection.connectDB();

    public static void checkForFile() {
        ScheduledExecutorService runThis = Executors.newSingleThreadScheduledExecutor();
        runThis.scheduleAtFixedRate(new CheckForLogFile(), 0, 2, TimeUnit.SECONDS);
    }

	public static void insertDataInQueue(DataTable dt) {
	    DBConnection.insertDataInDataTable(conn, dt);
        PredictionProcessor.addPredictionData(dt);
	}

}
