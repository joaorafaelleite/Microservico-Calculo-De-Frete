package com.itaulatam.mscalculodefrete.infrastructure.exceptions.controller;

import com.itaulatam.mscalculodefrete.infrastructure.exceptions.BusinessException;
import com.itaulatam.mscalculodefrete.infrastructure.exceptions.ObjetoErro;
import com.itaulatam.mscalculodefrete.infrastructure.exceptions.PedidoDeFreteException;
import com.itaulatam.mscalculodefrete.infrastructure.exceptions.TransporteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PedidoDeFreteException.class)
    private ResponseEntity<ObjetoErro> pedidoIncorreto(PedidoDeFreteException exception){
        ObjetoErro erro = new ObjetoErro(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(TransporteException.class)
    private ResponseEntity<ObjetoErro> transporteInvalido(TransporteException exception){
        ObjetoErro erro = new ObjetoErro(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ObjetoErro> calculoDeFreteInvalido(BusinessException exception){
        ObjetoErro erro = new ObjetoErro(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjetoErro> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).map(mensagem -> new ObjetoErro(status,mensagem)).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<ObjetoErro>> getErrorsMap(List<ObjetoErro> errors) {
        Map<String, List<ObjetoErro>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}
