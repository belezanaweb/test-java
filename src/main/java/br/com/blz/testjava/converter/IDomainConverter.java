package br.com.blz.testjava.converter;

public interface IDomainConverter<DTO, DOMAIN> {
	DOMAIN toDomain(final DTO dto);
	DTO toDTO(final DOMAIN domain);
}