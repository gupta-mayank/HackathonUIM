package com.ca.aiops.DatabaseInteraction;

public class DataTable {

    private String threadname;
    private int rowInserted;
    private double timetakenForInsertion;
    private String tableName;
    private long commitDateTime;

    @Override
    public String toString() {
        return "DataTable{" +
                "threadname='" + threadname + '\'' +
                ", rowInserted=" + rowInserted +
                ", timetakenForInsertion=" + timetakenForInsertion +
                ", tableName='" + tableName + '\'' +
                ", commitDateTime=" + commitDateTime +
                '}';
    }

    public DataTable(String threadname, int rowInserted, double timetakenForInsertion, String tableName, long commitDateTime) {
        this.threadname = threadname;
        this.rowInserted = rowInserted;
        this.timetakenForInsertion = timetakenForInsertion;
        this.tableName = tableName;
        this.commitDateTime = commitDateTime;
    }

    public String getThreadname() {
        return threadname;
    }

    public void setThreadname(String threadname) {
        this.threadname = threadname;
    }

    public int getRowInserted() {
        return rowInserted;
    }

    public void setRowInserted(int rowInserted) {
        this.rowInserted = rowInserted;
    }

    public double getTimetakenForInsertion() {
        return timetakenForInsertion;
    }

    public void setTimetakenForInsertion(double timetakenForInsertion) {
        this.timetakenForInsertion = timetakenForInsertion;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getCommitDateTime() {
        return commitDateTime;
    }

    public void setCommitDateTime(long commitDateTime) {
        this.commitDateTime = commitDateTime;
    }
}
