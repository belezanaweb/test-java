package br.com.blz.testjava.util;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.blz.testjava.config.MessagesUtils;

public class Utils {
	
	public static final Long skuVerify(Long sku) {
		return Optional.ofNullable(sku)
				.filter(i -> NumberUtils.isNumber(i.toString()))
				.filter(i -> Objects.equals(Long.signum(i), NumberUtils.INTEGER_ONE))
				.orElseThrow(() -> new IllegalArgumentException(MessagesUtils.getMessage("valid.sku.number")));
	}
	
	public static final String verifyName(String name) {
		return Optional.ofNullable(name)
				.filter(StringUtils::isNotBlank)
				.orElseThrow(() -> new IllegalArgumentException(MessagesUtils.getMessage("valid.product.name")));
	}
	
	public static final String verifyLocality(String name) {
		return Optional.ofNullable(name)
				.filter(StringUtils::isNotBlank)
				.filter(StringUtils::isAlphaSpace)
				.orElseThrow(() -> new IllegalArgumentException(MessagesUtils.getMessage("valid.product.locality")));
	}
	
	public static final Integer quantityVerify(Integer quantity) {
		return Optional.ofNullable(quantity)
				.filter(i -> NumberUtils.isNumber(i.toString()))
				.filter(i -> Objects.equals(Integer.signum(i), NumberUtils.INTEGER_ONE))
				.orElseThrow(() -> new IllegalArgumentException(MessagesUtils.getMessage("valid.quantity.number")));
	}

}
