package br.com.blz.testjava.testjava.handler

import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
 class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
        String mensagemTecnica = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage(mensagemUsuario, mensagemTecnica));

        return handleExceptionInternal(ex,errors,headers,HttpStatus.BAD_REQUEST,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErrorMessage> errors = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex,errors,headers,HttpStatus.BAD_REQUEST,request);
    }


    @ExceptionHandler([EmptyResultDataAccessException.class])
    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex , WebRequest webRequest){

        String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado",null, LocaleContextHolder.getLocale());
        String mensagemTecnica = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage(mensagemUsuario, mensagemTecnica));

        return handleExceptionInternal(ex,errors,new HttpHeaders(),HttpStatus.NOT_FOUND,webRequest);

    }

    @ExceptionHandler([DataIntegrityViolationException.class])
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex , WebRequest webRequest){

        String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida",null, LocaleContextHolder.getLocale());
        String mensagemTecnica = ExceptionUtils.getRootCauseMessage(ex);
        List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage(mensagemUsuario, mensagemTecnica));

        return handleExceptionInternal(ex,errors,new HttpHeaders(),HttpStatus.BAD_REQUEST,webRequest);

    }

    private List<ErrorMessage> createErrorList(BindingResult bindingResult){

        List<ErrorMessage> errors = new ArrayList<>();

        for(FieldError fieldError:bindingResult.getFieldErrors()){

            String mensagemUsuario = messageSource.getMessage(fieldError,LocaleContextHolder.getLocale());
            String mensagemTecnica = fieldError.toString();

            errors.add(new ErrorMessage(mensagemUsuario, mensagemTecnica));
        }

        return errors;
    }

    static class ErrorMessage{

        String mensagemUsuario;
        String mensagemTecnica;

        ErrorMessage(String mensagemUsuario, String mensagemTecnica) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemTecnica = mensagemTecnica;
        }

    }
}
