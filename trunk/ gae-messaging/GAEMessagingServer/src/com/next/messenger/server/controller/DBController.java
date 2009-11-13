package com.next.messenger.server.controller;

import com.next.core.db.PMF;
import com.next.viewer.server.DBServiceAbstract;

public class DBController extends DBServiceAbstract{
    @Override
    public Object getPersistanceManagerFactory() {
            return PMF.get();
    }
}