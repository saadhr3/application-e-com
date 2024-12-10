package com.example.customerservice;

import com.example.customerservice.config.CustomerConfigParams;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
											   RepositoryRestConfiguration restConfiguration) {
		return args -> {
			// car par defaut spring data rest nexpose pas les ID quand on consulte une entite
			restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
					List.of(Customer.builder().name("Hassan").email("hassan@gmail.com").build(),
                    Customer.builder().name("Hanane").email("hanane@gmail.com").build(),
                    Customer.builder().name("Imane").email("imane@gmail.com").build()
            ));

			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
