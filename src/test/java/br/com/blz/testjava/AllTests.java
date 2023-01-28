package br.com.blz.testjava;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    ProductControllerIntegrationTests.class
})
public class AllTests {

}
