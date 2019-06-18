package br.com.blz.testjava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.minidev.json.parser.ParseException;

@SpringBootApplication
public class TestJavaApplication {
	
	static FileInputStream fis;
	static Properties props;

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		SpringApplication.run(TestJavaApplication.class, args);
		
		fis = new FileInputStream("C:/DEV/test-java/src/main/resources/application.properties");
		props = new Properties();
		try{
			props.load(fis);
			fis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(props.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(props.getProperty("spring.datasource.url"));
		dataSource.setUsername(props.getProperty("spring.datasource.username"));
		dataSource.setPassword(props.getProperty("spring.datasource.password"));
		return (DataSource) dataSource;
	}
}
