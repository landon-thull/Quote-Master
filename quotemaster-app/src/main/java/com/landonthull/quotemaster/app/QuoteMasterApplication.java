package com.landonthull.quotemaster.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.landonthull.quotemaster.app",
    "com.landonthull.quotemaster.core",
    "com.landonthull.quotemaster.infrastructure.common"})
public class QuoteMasterApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuoteMasterApplication.class, args);
  }
}
