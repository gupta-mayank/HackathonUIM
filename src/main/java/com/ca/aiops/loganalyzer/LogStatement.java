package com.ca.aiops.loganalyzer;

public class LogStatement {

    private String line;
    private boolean processed;

    public LogStatement(String line, boolean processed) {
        this.line = line;
        this.processed = processed;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
