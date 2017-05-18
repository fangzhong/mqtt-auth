package com.minorfish.iot.boss.tsdb.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class InfluxDBConfig {
    @Value("${influxdb.url}")
    private String url;
    @Value("${influxdb.username}")
    private String username;
    @Value("${influxdb.password}")
    private String password;
    @Value("${influxdb.database}")
    private String database;
    @Value("${influxdb.retention-policy}")
    private String retentionPolicy;

    @Bean
    public InfluxDB influxDB() {
        try {
            System.out.println(this.url + " - " + this.username + " - " + this.password);
            return InfluxDBFactory.connect(this.url, this.username, this.password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("influx db connection failed!!!!!");
        }
    }


    public String getDatabase() {
        return database;
    }

    public String getRetentionPolicy() {
        return retentionPolicy;
    }
}
