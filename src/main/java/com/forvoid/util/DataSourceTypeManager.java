package com.forvoid.util;

import com.forvoid.enums.DataSource;

/**
 * Created by forvoid on 2017/8/2.
 */
public class DataSourceTypeManager {
    private static final ThreadLocal<DataSource> dataSourceTypes = new ThreadLocal<DataSource>(){
        @Override
        protected DataSource initialValue() {
            return DataSource.MASTER;
        }
    };

    public static DataSource get() {
        return dataSourceTypes.get();
    }

    public static void set(DataSource dataSource) {
        dataSourceTypes.set(dataSource);
    }

    public static void reset() {
        dataSourceTypes.set(DataSource.MASTER);
    }

}
