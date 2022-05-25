package com.fundamentosp.springboot.fundamentos.configuration;

import com.fundamentosp.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation() {
        return new MyBean2Implement();
    }

    @Bean
    public MyOperation beanOperationOperation() {
        return new MyOperationImplement();
    }

    @Bean
    public MyOwnOperation beanOperationMyOwn() {
        return new MyOwnOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation) {
        return new MyBeanWithDependencyImplement(myOperation);
    }

    @Bean
    public MyOwnBeanWithDependency beanOperationMyOwnWithDependency(MyOwnOperation myOwnOperation) {
        return new MyOwnBeanWithDependencyImplement(myOwnOperation);
    }

}
