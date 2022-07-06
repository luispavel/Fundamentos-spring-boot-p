package com.fundamentosp.springboot.fundamentos;

import com.fundamentosp.springboot.fundamentos.bean.*;
import com.fundamentosp.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosp.springboot.fundamentos.entity.User;
import com.fundamentosp.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosp.springboot.fundamentos.repository.UserRepository;
import com.fundamentosp.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependency;

	private MyOwnBeanWithDependency myOwnBeanWithDependency;

	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
								  MyOwnBeanWithDependency myOwnBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myOwnBeanWithDependency = myOwnBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com",  LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);
		userService.saveTransactional(users);
		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("User from transactional method: " + user));
	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("User with email: " +
				userRepository.findByUserEmail("julie@domain.com")
						.orElseThrow(() -> new RuntimeException("User not found"))
		);

		userRepository.findAndSort("user", Sort.by("id").ascending())
				.stream()
				.forEach(user -> LOGGER.info("User with method sort" + user.toString()));

		userRepository.findByNameContaining("Luis")
				.stream()
				.forEach(user -> LOGGER.info("User with method findByName " + user.toString()));

		LOGGER.info("User with query method findByEmailAndName " +userRepository.findUsersByNameAndAndEmail("Luis", "luis@domain.com")
				.orElseThrow(() -> new RuntimeException("User not found by name and email"))
		);

		userRepository.findByBirthDateBetween(LocalDate.of(2022, 03, 15), LocalDate.of(2022, 03, 25))
				.stream()
				.forEach(user -> LOGGER.info("User with method findByBirthDateBetween:" + user));

		userRepository.findByNameLikeOrderByIdDesc("%T%")
				.stream()
				.forEach(user -> LOGGER.info("User with method findByNameLikeOrderByIdDesc:" + user));

		userRepository.findAndSort("Test", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("User with method findByAndSort:" + user));

//		LOGGER.info("User with method findByNameOrEmail: " + userRepository.findByNameOrEmail(null, "user5@domain.com")
//				.orElseThrow(() -> new RuntimeException("No se encontro el usuario por el email dado")));

		LOGGER.info("EL usuario a partir de name parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2022,04,13), "user3@domain.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario por el name parameter"))
		);
	}

	private void saveUsersInDataBase(){
		User user1 = new User("Luis", "luis@domain.com", LocalDate.of(2022, 05, 29));
		User user2 = new User("Luis", "julie@domain.com", LocalDate.of(2022, 03, 25));
		User user3 = new User("user3", "user3@domain.com", LocalDate.of(2022, 04, 13));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(2022, 03, 11));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2022, 02, 15));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2022, 01, 19));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2022, 11, 25));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2022, 12, 21));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2022, 10, 20));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2022, 01, 19));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		list.stream().forEach(user -> userRepository.save(user));


	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		myOwnBeanWithDependency.printWithOwnDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
		try {
			int value = 10/ 0;
			LOGGER.info("El valor de value es: " + value);
		} catch (Exception e) {
			LOGGER.error("Error de al dividir por cero " + e.getMessage());
		}
	}
}
