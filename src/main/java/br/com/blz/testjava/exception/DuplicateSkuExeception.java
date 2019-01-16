package br.com.blz.testjava.exception;

import static br.com.blz.testjava.config.BelezaNaWebStringConfig.DUPLICATE_SKU_EXCEPTION_MESSAGE;

public class DuplicateSkuExeception extends RuntimeException {

	private static final long serialVersionUID = 2019010901L;

	public DuplicateSkuExeception() {
		super(DUPLICATE_SKU_EXCEPTION_MESSAGE);
	}
}
