package com.itheima.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fescar.rm.datasource.DataSourceProxy;
import com.alibaba.fescar.spring.annotation.GlobalTransactionScanner;
import com.itheima.filter.FescarXidFilter;
import com.itheima.hystriThread.RequestContextHystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class Conf {

    @Bean
    public FescarXidFilter fescarXidFilter() {
        return new FescarXidFilter();
    }

    @Bean(name = "globalTransactionScanner")
    public GlobalTransactionScanner globalTransactionScanner() {
        return new GlobalTransactionScanner("fescar-order", "my_test_tx_group");
    }

    @Bean(name = "druidDataSource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql:///db5?useUnicode=true&characterEncoding=utf8&autoReconnect=true");
        druidDataSource.setPassword("root");
        druidDataSource.setUsername("root");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return druidDataSource;
    }

    @Bean(name = "dataSourceProxy")
    public DataSourceProxy dataSourceProxy(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        return sqlSessionFactoryBean;
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer ma = new MapperScannerConfigurer();
        ma.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        ma.setBasePackage("com.itheima.mapper");
        return ma;
    }

}
