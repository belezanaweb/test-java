package br.com.blz.testjava;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

public class ArchitectTest {
	
	  JavaClasses importedClasses = new ClassFileImporter().importPackages("br.com.blz.testjava");

	    @Test
	    public void checkDependenciesForPersistenceLayer() {

	        ArchRule rule = classes()
	                .that().resideInAPackage("..repository..")
	                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..repository..", "..service..");

	        rule.check(importedClasses);

	    }

	    @Test
	    public void checkDependenciesPersistenceLayer() {

	        ArchRule rule = noClasses()
	                .that().resideInAPackage("..repository..")
	                .should().dependOnClassesThat().resideInAnyPackage("..service..");

	        rule.check(importedClasses);

	    }

	    @Test
	    public void checkNamesForPersistenceLayer() {

	        ArchRule rule = classes()
	                .that().haveSimpleNameEndingWith("repository")
	                .should().resideInAPackage("..repository..");

	        rule.check(importedClasses);

	    }

	    @Test
	    public void checkCyclicalDependencies() {

	        ArchRule rule = slices()
	                .matching("br.com.blz.testjava.(*)..").should().beFreeOfCycles();

	        rule.check(importedClasses);

	    }

	    @Test
	    public void checkLayerViolation() {

	        ArchRule rule = layeredArchitecture()
	                .layer("Service").definedBy("..service..")
	                .layer("repository").definedBy("..repository..")

	                .whereLayer("repository").mayOnlyBeAccessedByLayers("Service");

	        rule.check(importedClasses);

	    }

}
