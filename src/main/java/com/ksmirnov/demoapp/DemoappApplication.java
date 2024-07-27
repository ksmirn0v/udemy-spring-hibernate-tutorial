package com.ksmirnov.demoapp;

import com.ksmirnov.demoapp.dao.AccountDAO;
import com.ksmirnov.demoapp.dao.MembershipDAO;
import com.ksmirnov.demoapp.model.Account;
import com.ksmirnov.demoapp.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AccountDAO accountDAO,
			MembershipDAO membershipDAO,
			TrafficFortuneService trafficFortuneService
	) {

		return runner -> {
			runAdvices(accountDAO, membershipDAO, trafficFortuneService);
		};
	}

	private void runAdvices(
			AccountDAO accountDAO,
			MembershipDAO membershipDAO,
			TrafficFortuneService trafficFortuneService
	) {
		accountDAO.addAccount();
		accountDAO.addAccount(new Account("John", "Smith"));
		accountDAO.addAccount(new Account("Michael", "Jones"), true);
		membershipDAO.addMember();
		membershipDAO.setMember();
		membershipDAO.getMember();
		List<Account> accounts = accountDAO.findAccounts();
		System.out.println("accounts: " + accounts);
		try {
			membershipDAO.findMembers();
		} catch (Exception exc) {
			System.out.println("The exception is thrown: " + exc.getMessage());
		}
		System.out.println("\n\n=== @Around Advice ===\n\n");
		String prediction = trafficFortuneService.getFortune();
		System.out.println("Prediction: " + prediction);
		prediction = trafficFortuneService.throwOneException();
		System.out.println("Prediction: " + prediction);
		try {
			trafficFortuneService.throwAnotherException();
		} catch (Exception exc) {
			System.out.println("Exception: " + exc.getMessage());
		}
	}
}
