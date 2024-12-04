package com.ecom.Ecommerce.Exception;

public class EmailAlreadyExists extends RuntimeException {
    String resourceName;
    String fieldName;
    String fieldValue;
    public EmailAlreadyExists(String resourceName,String fieldName,String fieldValue){
        super(String.format("%s already exists with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }

}
