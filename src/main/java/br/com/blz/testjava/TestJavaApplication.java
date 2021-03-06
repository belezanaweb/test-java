package br.com.blz.testjava;

import br.com.blz.testjava.Repository.WarehouseRepository;
import br.com.blz.testjava.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
public class TestJavaApplication implements ApplicationRunner {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        warehouseRepository.save(new Warehouse("SP", "ECOMMERCE"));
        warehouseRepository.save(new Warehouse("MOEMA", "PHYSICAL_STORE"));
    }

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
	}


}
