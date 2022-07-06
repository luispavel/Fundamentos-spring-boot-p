package com.fundamentosp.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {
    private MyOperation myOperation;
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }
    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    @Override
    public void printWithDependency() {
        LOGGER.info("MyBeanWithDependencyImplement.printWithDependency()");
        int num = 1;
        LOGGER.debug("El numero es: " + num);
        System.out.println(myOperation.sum(num));
        System.out.println("Hi from la implementacion de un bean con dependencia");

    }
}
