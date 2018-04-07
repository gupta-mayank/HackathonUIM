package com.ca.aiops.DatabaseInteraction;

import com.ca.aiops.Commons;
import com.ca.aiops.DataTableCollection;
import com.ca.aiops.Prediction.PredictionCommitRateWorker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class DBConnection {
	
    public static synchronized Connection connectDB(){

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.238.20.89:1433;databaseName="+ Commons.DATABASE_NAME,"sa", "interOP@123");
            System.out.println("Database Connected");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized boolean insertDataInDataTable(Connection conn, DataTable db) {
        try {
            Statement statement = conn.createStatement();
            String queryString = "insert into " + Commons.Data_Table_NAME +
                    " (Thread_name,recordsinserted,timetakenforinsertion,commit_date_time,rn_table_name) VALUES ('" +
                    db.getThreadname() + "'," + db.getRowInserted() + "," + db.getTimetakenForInsertion() + ","
                    + db.getCommitDateTime() + ",'" + db.getTableName() + "')";
            statement.executeUpdate(queryString);           
        }
        catch(Exception e) {
        	System.out.println("Update Failed");
        }
        return false;
    }

    public static  int getNextPrimaryKeyPrediction() {
        try {
            Statement statement = DataTableCollection.conn.createStatement();
            String queryString = "select max(prediction_id) from " + Commons.PREDICTION_TABLE;
            ResultSet rs = statement.executeQuery(queryString);
            while(rs.next()) {
                Integer i = Integer.valueOf(rs.getString(1));
                PredictionCommitRateWorker.setId(i);
            }
        }
        catch(Exception e) {
            System.out.println("Update Failed");
        }
        return 1;
    }

    public static synchronized boolean insertPrediction(Connection conn, PredictionTable pt) {
        try {
            Statement statement = conn.createStatement();
            String queryString = "insert into " + Commons.PREDICTION_TABLE +
                    " VALUES (" + pt.getPredictionId()  +
                    ",'" + pt.getPredictionName() + "'" +
                    ",'" + pt.getPredictionDescription() + "'" +
                     ",'" + pt.getRemediation_status() + "'" +
                     "," + pt.getTimestamp() +
                    ")";
            statement.executeUpdate(queryString);
        }
        catch(Exception e) {
            System.out.println("Update Failed");
        }
        return false;
    }


    
    public static void closeDBConnection(Connection conn) {
    	try {
    		conn.close();
    		System.out.println("Database closed successfully");
    	}
    	catch(Exception e) {
    		System.out.println("Database closed failed.");
    	}
    }
}