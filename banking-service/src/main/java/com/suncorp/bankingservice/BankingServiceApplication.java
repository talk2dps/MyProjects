package com.suncorp.bankingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *This is main class from where the spring boot application execution starts.
 */
@SpringBootApplication
public class BankingServiceApplication extends SpringBootServletInitializer {

	/**
	 * The main method.
	 *
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BankingServiceApplication.class, args);
	}
}
