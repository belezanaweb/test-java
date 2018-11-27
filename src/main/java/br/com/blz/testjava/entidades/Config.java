package br.com.blz.testjava.entidades;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "config")
public class Config {
  
  private DatabaseConfig db = new DatabaseConfig();
  
  // ================================
  
  @Data
  public static class DatabaseConfig {
    private String username =     "";
    private String password =     "";
    private String jdbcUrl =      "";
    private String driverClass =  "";
  }
  
}