package com.forvoid.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by forvoid on 2017/8/2.
 */
public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.get();
    }
}
