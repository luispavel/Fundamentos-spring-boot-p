package com.fundamentosp.springboot.fundamentos.bean;

public class MyOwnBeanWithDependencyImplement implements MyOwnBeanWithDependency {
    private MyOwnOperation myOwnOperation;

    public MyOwnBeanWithDependencyImplement(MyOwnOperation myOwnOperation) {
        this.myOwnOperation = myOwnOperation;
    }

    public void printWithOwnDependency() {
        int num = 1;
        System.out.println(myOwnOperation.res(num));
        System.out.println("Hi from la implementacion de my own bean with dependency");
    }

}
