// File: src/main/java/com/tiara/peminjaman_service/config/MongoConfig.java
package com.tiara.peminjaman_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories (basePackages = "com.tiara.peminjaman_service.repository") 
public class MongoConfig {
}

