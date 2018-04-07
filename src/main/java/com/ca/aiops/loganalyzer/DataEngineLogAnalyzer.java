package com.ca.aiops.loganalyzer;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

public class DataEngineLogAnalyzer {

    public void startTailingLog() {
        TailerListener listener = new TailerListenerAdapter() {
            @Override
            public void handle(String line) {
                // Check the logs for the statements referring to the time it took to commit
                // Statement looks like Apr  2 13:06:57:273 [7464] de: Commit - inserted    2 rows to RN_QOS_DATA_0059 in   9 ms (ms/r:4)
                String patternString = ".*Commit.*inserted.*";
                Pattern pattern = Pattern.compile(patternString);
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()) {
                   LogStatement log = new LogStatement(line,false);
                   LogStatementProcessor.processStatement(log);
                }
            }
        };

        System.out.println("Base directiry is getBasedir() : " + getBasedir());
        File file = new File(getBasedir() + File.separator + getLogFileName());

        Tailer tailer = new Tailer(file, listener, 1000);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        try {
            thread.join();
        }
        catch(Exception e) {
            System.out.println("Thread Join failed" + e.getStackTrace());
        }
    }

    private String getBasedir() {
      //return "C:\\Program Files (x86)\\Nimsoft\\probes\\slm\\data_engine";
       return "Y:\\";
    }

    private String getLogFileName() {
        return "data_engine.log";
    }
}
