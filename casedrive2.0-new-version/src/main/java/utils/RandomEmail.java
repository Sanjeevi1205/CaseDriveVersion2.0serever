package utils;

import java.util.Random;

import com.github.javafaker.Faker;

public class RandomEmail {
	private static final Faker faker = new Faker();
	private static final Random random = new Random();

	public RandomEmail() {
		throw new IllegalStateException("Utility class");
	}
	// Generate a random email for signup
	public static String generateSignupEmail() {
		return "Tom" + random.nextInt(10000) + "@lezdotechmed.com";
	}

	// Generate a random email for incharge
	public static String generateInchargeEmail() {
		return "Ben" + random.nextInt(10000) + "@lezdotechmed.com";
	}

	// Generate a random email for payment
	public static String generatePaymentEmail() {
		return "Julia" + random.nextInt(10000) + "@lezdotechmed.com";
	}

	// Generate a random email for payment
	public static String generatePartnerEmail() {
		return "Brad" + random.nextInt(10000) + "@lezdotechmed.com";
	}

	// Generate a random email for employee
	public static String employeeMail() {
		return "employee" + random.nextInt(10000) + "@lezdotechmed.com";
	}

	// Generate a random email for employee
	public static String memberMail() { return "member" + random.nextInt(10000) + "@lezdotechmed.com";}

	// Generate a random email for organisation
	public static String organisationMail() { return "lezdotechmed" + random.nextInt(10000) + "@lezdotechmed.com";}

	// Generate a random email for Creating a Client
	public static String createClientMail() { return "createclient" + random.nextInt(10000) + "@lezdotechmed.com";}

	// Generate a random email for Creating a lead
	public static String createLeadMail() { return "createlead" + random.nextInt(10000) + "@lezdotechmed.com";}

	//Generate a random email for Creating a patientmail
	public static String createPatientMail() { return "patient" + random.nextInt(10000) + "@lezdotechmed.com";}

	// Generate a random first name
	public static String generateFirstName() {
		return faker.name().firstName();
	}

	// Generate a random last name
	public static String generateLastName() {
		return faker.name().lastName();
	}

	// Generate a full name (First + Last)
	public static String generateFullName() {
		return faker.name().fullName();
	}

	// Generates a 10-digit number
	public static String generateNumericPhoneNumber() {
		return faker.number().digits(10);
	}

	public static String generateInvalidEmail() {
		return "Tom" + random.nextInt(10000) + "lezdotechmedcom";
	}

	public static String generateInvalidPhoneNumber() {
		return faker.number().digits(15);
	}
}