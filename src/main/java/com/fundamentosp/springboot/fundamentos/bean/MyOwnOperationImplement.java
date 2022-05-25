package com.fundamentosp.springboot.fundamentos.bean;

public class MyOwnOperationImplement implements MyOwnOperation {
    @Override
    public int res(int number) {
        return number - 1;
    }

}
