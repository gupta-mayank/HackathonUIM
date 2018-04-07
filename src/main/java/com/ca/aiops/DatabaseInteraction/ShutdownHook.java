package com.ca.aiops.DatabaseInteraction;

import com.ca.aiops.DataTableCollection;

public class ShutdownHook extends Thread {

    public void run() {
        DBConnection.closeDBConnection(DataTableCollection.conn);
    }
}
