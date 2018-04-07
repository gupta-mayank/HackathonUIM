package com.ca.aiops.DatabaseInteraction;

public class PredictionTable {

    private int predictionId;
    private String predictionName;
    private String predictionDescription;

    public String getRemediation_status() {
        return remediation_status;
    }

    public void setRemediation_status(String remediation_status) {
        this.remediation_status = remediation_status;
    }

    private String remediation_status;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timestamp;

    public int getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(int predictionId) {
        this.predictionId = predictionId;
    }

    public String getPredictionName() {
        return predictionName;
    }

    public void setPredictionName(String predictionName) {
        this.predictionName = predictionName;
    }

    public String getPredictionDescription() {
        return predictionDescription;
    }

    public void setPredictionDescription(String predictionDescription) {
        this.predictionDescription = predictionDescription;
    }

}
