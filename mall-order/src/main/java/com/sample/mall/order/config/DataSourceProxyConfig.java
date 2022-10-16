package com.sample.mall.order.config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 描述
 *
 * @author mistra@future.com
 * @date 2022/10/16
 */
@Configurable
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 创建数据源代理，设置为@Primary主数据源，否则不能支持Seata
     *
     * @param druidDataSource DruidDataSource
     * @return DataSource
     */
    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }
}
