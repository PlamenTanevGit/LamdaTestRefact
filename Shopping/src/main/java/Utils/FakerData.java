package Utils;

import java.util.Locale;

import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

public class FakerData {
	
	Locale locale = new Locale("en-GB");
	Faker faker = new Faker(locale);
		

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}

	public String getTelephone() {
		return faker.phoneNumber().phoneNumber();
	}

	public String getPassword() {
		return faker.internet().password();
	}

	public String getCompany() {
		return faker.company().name();
	}

	public String getAddress1() {
		return faker.address().fullAddress();
	}

	public String getPostCode() {
		return faker.address().zipCode();
	}







}
