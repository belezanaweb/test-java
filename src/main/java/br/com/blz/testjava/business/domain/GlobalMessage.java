package br.com.blz.testjava.business.domain;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public enum GlobalMessage {

	SUCCESS("0", "Operacao realizada com sucesso.", HttpStatus.OK),
	CREATED("1", "criado com sucesso.", HttpStatus.CREATED),
	NOT_CONTENT("3", "Não existem resultados a serem exibidos para a pesquisa informada.", HttpStatus.NOT_FOUND),
	PRODUCT_DUPLICATED("-3", "The product already exist.", HttpStatus.BAD_REQUEST),
	DIFFERENT_IDS("-3", "The identifications are differents", HttpStatus.BAD_REQUEST);
	
	@Getter
    private final String code;

    @Getter
    private String description;
    
    @Getter
    private HttpStatus httpStatus;

    GlobalMessage(final String code, final String description, final HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
    
    public String show(String... params) {
        Integer i = 0;
        String content = this.description;

        for (String param : params) {
            content = content.replace("${" + i + "}", param);
            i += 1;
        }

        return content;
    }
}
