package com.suncorp.bankingservice.exception;

import com.suncorp.bankingservice.model.ApiError;
import com.suncorp.bankingservice.model.ApiSubError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception handler class
 *
 * @author Debi Prasad Sahoo(755703)
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *Handles MethodArgumentNotValidException and send back error to the client.
     *
     * @param ex MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @param request WebRequest
     * @return ApiError
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        List<ApiSubError> apiErrorList = bindingResult.getFieldErrors().stream().map(
                fieldError -> new ApiSubError(fieldError.getObjectName(),
                                                fieldError.getField(),
                                                fieldError.getRejectedValue(),
                                                fieldError.getCode()

        )).collect(Collectors.toList());

        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST,"Invalid Input",apiErrorList),HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * Handles HttpMessageNotReadableException and send back error to the client.
     *
     * @param ex HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @param request WebRequest
     * @return ApiError
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException  ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request){
        String error = "Malformed JSON request";
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, error),HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles HttpMessageConversionException and send back error to the client
     *
     * @param ex HttpMessageConversionException
     * @return ApiError
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    protected ResponseEntity<Object> handleHttpMessageConversionException(HttpMessageConversionException ex){
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Invalid Input"),HttpStatus.BAD_REQUEST);
    }

    /**
     *Handles InsufficientAmountException and send back error to the client
     *
     * @param ex InsufficientAmountException
     * @return ApiError
     */
    @ExceptionHandler(InsufficientAmountException.class)
    protected ResponseEntity<Object> handleInsufficientAmountException(InsufficientAmountException ex){
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles NonExistentResourceException and send back error to the client
     *
     * @param ex NonExistentResourceException
     * @return ApiError
     */
    @ExceptionHandler(NonExistentResourceException.class)
    protected ResponseEntity<Object> handleNonExistenceResourceException(NonExistentResourceException ex){
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidInputException and send back error to the client
     *
     * @param ex InvalidInputException
     * @return ApiError
     */
    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex){
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
