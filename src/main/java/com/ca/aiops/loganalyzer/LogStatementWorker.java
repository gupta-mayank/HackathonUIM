package com.ca.aiops.loganalyzer;

import com.ca.aiops.DataSpikeThread.DataControl;
import com.ca.aiops.DatabaseInteraction.DataTable;
import com.ca.aiops.DataTableCollection;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* This class will read all the statements that need to be processed and do the regular expression
and pass it to store the same in the DB
 */
public class LogStatementWorker implements Runnable {

    private LogStatement logStatement;
    private static Map<String,String> listOfThreads = new ConcurrentHashMap<String, String>();
    private static  AtomicInteger threadNumber = new AtomicInteger(1);
    private static  AtomicInteger incrementNumber = new AtomicInteger(1);
    private static DecimalFormat f = new DecimalFormat("##.00");

    LogStatementWorker(LogStatement log) {
        this.logStatement = log;
    }

    public void run() {
        // Get the logs which are not processed
       // System.out.println("My thread id is " + Thread.currentThread().getId() + " statement is : " + logStatement.getLine());
        DataTable dt = new DataTable(fetchThreadName(),getNumberOfRowsInserted(),Double.valueOf(f.format(getCommmitRate().doubleValue())),fetchTableName(),System.currentTimeMillis());
        // System.out.println("My thread id is " + Thread.currentThread().getId() + " dataobject is : " +dt.toString());
        DataTableCollection.insertDataInQueue(dt);
    }

    private Double getCommmitRate() {
        if(LogStatementProcessor.dc == DataControl.SPIKE) {
            return fetchCommitRateSpiked();
        }
        else {
            return fetchCommitRate();
        }
    }

    private String fetchThreadName() {
        synchronized (listOfThreads) {
            String threadId = logStatement.getLine().substring(logStatement.getLine().indexOf('[') + 1, logStatement.getLine().lastIndexOf(']'));
            if (listOfThreads.containsKey(threadId)) {
                return listOfThreads.get(threadId);
            } else {
                listOfThreads.put(threadId, "Thread".concat(threadNumber.toString()));
                threadNumber.incrementAndGet();
                return listOfThreads.get(threadId);
            }
        }
    }

    private int getNumberOfRowsInserted() {
        String line = logStatement.getLine();
        String rowsInserted = line.substring(line.indexOf("inserted")+9 , line.indexOf("rows")-1).trim();
        return Integer.valueOf(rowsInserted);
    }

    private double fetchCommitRate() {
        String line = logStatement.getLine();
        String timeTaken = line.substring(line.indexOf("in ")+3, line.indexOf("ms")-1).trim();
        incrementNumber.incrementAndGet();
        return (Double.valueOf(timeTaken) / getNumberOfRowsInserted());
    }

    private double fetchCommitRateSpiked() {
        String line = logStatement.getLine();
        String timeTaken = line.substring(line.indexOf("in ")+3, line.indexOf("ms")-1).trim();
        incrementNumber.set(incrementNumber.get()+10);
        return (Double.valueOf(timeTaken) / getNumberOfRowsInserted())+(incrementNumber.get());
    }

    private String fetchTableName() {
        String line = logStatement.getLine();
        return line.substring(line.indexOf("to ")+3, line.indexOf("in ")-1).trim();
    }

}
