package com.landonthull.quotemaster.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "com.landonthull.quotemaster.app",
    "com.landonthull.quotemaster.core",
    "com.landonthull.quotemaster.infrastructure.common"})
public class CoreConfig {

}
