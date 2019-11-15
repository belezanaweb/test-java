package br.com.blz.testjava.Mother;

import org.springframework.stereotype.Component;

@Component
public class StandardMother {

	public StandardMother getStandardMother() {
		final StandardMother domain = new StandardMother();

		return domain;
	}

}