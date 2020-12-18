package br.com.blz.testjava.api.exceptions;

import br.com.blz.testjava.api.dtos.ProblemDTO;
import br.com.blz.testjava.model.enums.ProblemTypeEnum;
import br.com.blz.testjava.model.exceptions.ProductUniqueKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiErrors  extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        ProblemDTO problem = ProblemDTO.builder()
            .dateTime(LocalDateTime.now())
            .type(ProblemTypeEnum.ENTIDADE_NAO_ENCONTRADA.getUri())
            .title(ProblemTypeEnum.ENTIDADE_NAO_ENCONTRADA.getTitle())
            .status(status.value())
            .details(ex.getMessage())
            .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ProductUniqueKeyException.class)
    public ResponseEntity<?> handleProductUniqueKeyException(ProductUniqueKeyException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ProblemDTO problem = ProblemDTO.builder()
            .dateTime(LocalDateTime.now())
            .type(ProblemTypeEnum.ENTIDADE_DUPLICADA.getUri())
            .title(ProblemTypeEnum.ENTIDADE_DUPLICADA.getTitle())
            .status(status.value())
            .details(ex.getMessage())
            .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ProblemDTO problem = ProblemDTO.builder()
            .dateTime(LocalDateTime.now())
            .type(ProblemTypeEnum.ENTIDADE_EM_USO.getUri())
            .title(ProblemTypeEnum.ENTIDADE_EM_USO.getTitle())
            .status(status.value())
            .details(ex.getMessage())
            .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ProblemDTO.builder()
                .title(status.getReasonPhrase())
                .status(status.value())
                .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request, BindingResult bindingResult) {

        List<ProblemDTO.Field> fields = bindingResult.getAllErrors().stream().map(objectError -> {
            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

            FieldError field = (FieldError) objectError;

            return ProblemDTO.Field.builder()
                .name(field.getField())
                .message(message)
                .build();
        })
            .collect(Collectors.toList());

        ProblemDTO problem = ProblemDTO.builder()
            .dateTime(LocalDateTime.now())
            .type(ProblemTypeEnum.DADOS_INVALIDOS.getUri())
            .title(ProblemTypeEnum.DADOS_INVALIDOS.getTitle())
            .status(status.value())
            .details(ProblemTypeEnum.DADOS_INVALIDOS.getTitle())
            .fields(fields)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);

    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        List<ProblemDTO.Field> fields = bindingResult.getFieldErrors().stream()
            .map(fieldError -> {
                String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                return ProblemDTO.Field.builder()
                    .name(fieldError.getField())
                    .message(message)
                    .build();
            })
            .collect(Collectors.toList());

        ProblemDTO problem = ProblemDTO.builder()
            .dateTime(LocalDateTime.now())
            .type(ProblemTypeEnum.DADOS_INVALIDOS.getUri())
            .title(ProblemTypeEnum.DADOS_INVALIDOS.getTitle())
            .status(status.value())
            .details(ProblemTypeEnum.DADOS_INVALIDOS.getTitle())
            .fields(fields)
            .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }
}
