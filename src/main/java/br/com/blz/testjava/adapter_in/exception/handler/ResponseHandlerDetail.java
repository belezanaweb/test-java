package br.com.blz.testjava.adapter_in.exception.handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Builder
public class ResponseHandlerDetail {

    private Integer status;
    private LocalDateTime datetime = LocalDateTime.now();
    private String classeDeError;
    private List<String> errosMensagem;
    private Map<String, String> listaCamposObrigatorios;

    public ResponseHandlerDetail addMenssagemErro(final String messagem) {
        this.errosMensagem = Optional.ofNullable(this.errosMensagem).orElse(new ArrayList<>());
        this.errosMensagem.add(messagem);
        return this;
    }

    public ResponseHandlerDetail addCamposObrigarios(Map<String, String> listCamposObrigarios) {
        this.listaCamposObrigatorios = Optional.ofNullable(this.listaCamposObrigatorios).orElse(new HashMap<>());
        this.listaCamposObrigatorios.putAll(listCamposObrigarios);
        return this;
    }
    public ResponseHandlerDetail addListMenssagemError(final List<String> messagem) {
        messagem.forEach(this::addMenssagemErro);
        return this;
    }

}
