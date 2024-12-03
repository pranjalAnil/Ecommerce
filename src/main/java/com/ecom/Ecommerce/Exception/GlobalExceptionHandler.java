package com.ecom.Ecommerce.Exception;

import com.ecom.Ecommerce.payloads.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderedProdMoreThanNumOfProd.class)
    public ResponseEntity<APIResponse> numOfOrderedProdMoreThanNumOfProd(OrderedProdMoreThanNumOfProd ex){
        String message= ex.getMessage();
        APIResponse apiResponse=new APIResponse(message,false);
        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.CONFLICT);

    }
}
