package com.fundamentosp.springboot.fundamentos;

import com.fundamentosp.springboot.fundamentos.bean.MyBean;
import com.fundamentosp.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosp.springboot.fundamentos.bean.MyOwnBeanWithDependency;
import com.fundamentosp.springboot.fundamentos.bean.MyOwnBeanWithDependencyImplement;
import com.fundamentosp.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;

	private MyOwnBeanWithDependency myOwnBeanWithDependency;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
	MyOwnBeanWithDependency myOwnBeanWithDependency) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myOwnBeanWithDependency = myOwnBeanWithDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		myOwnBeanWithDependency.printWithOwnDependency();
	}
}
