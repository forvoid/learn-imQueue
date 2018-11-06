package com.forvoid.AOP;

import com.forvoid.enums.DataSource;
import com.forvoid.util.DataSourceTypeManager;
import com.mchange.v2.c3p0.DataSources;
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
// execute before @Transactional 这里存在多个Aspect之间的一个执行顺序的问题，
// 必须保证切换数据源的Aspect必须在@Transactional这个Aspect之前执行，
// 所以这里使用了@Order(0)来保证切换数据源先于@Transactional执行
public class DataSourceInterceptor {
    private static Logger logger = LoggerFactory.getLogger(DataSourceInterceptor.class);
    @Pointcut("execution(public * com.forvoid.mapper.*.*(..))")
    public void dataSourceMaster() {
        logger.info("dataSource Master");
    }

    @Before("dataSourceMaster()")
    public void before(JoinPoint jp) {
        logger.info("set DataSource masters");
        DataSourceTypeManager.set(DataSource.MASTER);
    }
}
