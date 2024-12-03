package com.ecom.Ecommerce.Exception;

public class OrderedProdMoreThanNumOfProd extends RuntimeException{
    int numOfProd;
    int numOfOrderedProd;
    public OrderedProdMoreThanNumOfProd(int numOfProd, int numOfOrderedProd){
        super(String.format("num of ordered products %d are more than number of products %d", numOfProd,numOfOrderedProd));
        this.numOfProd = numOfOrderedProd;
        this.numOfOrderedProd = numOfProd;

    }
    public OrderedProdMoreThanNumOfProd(int quantity){
        super(String.format("Product currently not available to order %d prod", quantity));
        this.numOfProd = numOfOrderedProd;
        this.numOfOrderedProd = quantity;

    }

}
