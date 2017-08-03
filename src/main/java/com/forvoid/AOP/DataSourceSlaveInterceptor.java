package com.forvoid.AOP;

import com.forvoid.enums.DataSource;
import com.forvoid.util.DataSourceTypeManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by forvoid on 2017/8/2.
 */
@Aspect
@Component
@Order(0)
public class DataSourceSlaveInterceptor {
    private static Logger logger = LoggerFactory.getLogger(DataSourceSlaveInterceptor.class);
    @Pointcut("execution(public * com.forvoid.mapper2.*.*(..))")
    public void dataSourceMaster() {
        logger.info("dataSource Slave");
    }

    @Before("dataSourceMaster()")
    public void before(JoinPoint jp) {
        logger.info("set DataSource Slave");
        DataSourceTypeManager.set(DataSource.SLAVE);
    }
}
