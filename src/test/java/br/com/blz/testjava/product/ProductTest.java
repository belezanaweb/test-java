package br.com.blz.testjava.product;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/resources/product")
public class ProductTest {

}
