package br.com.blz.testjava.startup;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.blz.testjava.entidades.Config;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DatabaseStartup {
  
  @Autowired
  private Config config; 
  
  @PostConstruct
  public EbeanServer createConnection(){
    log.info("Iniciando configuração do Banco de Dados");
    
    if( config == null ){
      log.error("As configurações do sistema não puderam ser carregadas! Não será possível configurar conexão com Banco de Dados.");
      return null;
    }
    log.info("Configuração importada: ( URL: {} )", config.getDb().getJdbcUrl() );
    
    Properties props = new Properties();
    props.setProperty("ebean.db.ddl.generate",        "true" );
    props.setProperty("ebean.db.ddl.run",             "true" );
    props.setProperty("ebean.search.packages",        Config.class.getPackage().getName() );
    props.setProperty("datasource.db.username",       config.getDb().getUsername() );
    props.setProperty("datasource.db.password",       config.getDb().getPassword() );
    props.setProperty("datasource.db.databaseUrl",    config.getDb().getJdbcUrl() );
    props.setProperty("datasource.db.databaseDriver", config.getDb().getDriverClass() );
    ServerConfig config = new ServerConfig();
    config.loadFromProperties(props);
    config.setName("h2");
    config.setDefaultServer(true);
    
    EbeanServer conn = EbeanServerFactory.create(config);
    log.info("Conexão com Banco de Dados H2 configurada");
    return conn;
  }
  
}
