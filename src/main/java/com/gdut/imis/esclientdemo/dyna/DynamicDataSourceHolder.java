package com.gdut.imis.esclientdemo.dyna;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lulu
 * @Date 2019/6/8 12:14
 */
public class DynamicDataSourceHolder {
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }

    public static void setDBType(String str) {
        contextHolder.set(str);
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
