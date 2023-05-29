package Pages;

import org.openqa.selenium.WebDriver;

public class AddPersonalDetails {
	
	private AccountRegisterPage accountRegisterPage;
	protected WebDriver driver;
	
	
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String password;
	private String confrimPassword;
	private String company;
	private String address1;
	private String address2;
	private String city;
	private String postCode;
	private String country;
	private String region;
	private String VAT;
	
	public AddPersonalDetails(WebDriver driver) {
		this.driver = driver;
		this.accountRegisterPage = new AccountRegisterPage(driver);
	}
	
	/**
	 * GUEST form fill 
	 */
	public AddPersonalDetails(WebDriver driver, 
			String fn, 
			String ln, 
			String email, 
			String phone,
			String company,
			String addr,
			String City, 
			String PO, 
			String country, 
			String region ) throws InterruptedException {
		this.driver = driver;
		this.accountRegisterPage = new AccountRegisterPage(driver);
		setFirstName(fn);
		setLastName(ln);
		setEmail(email);
		setTelephone(phone);		
		setCompany(company);
		
		setAddress1(addr);
		setAddress2(addr);
		setCity(City);
		setPostCode(PO);
		selectCountry(country);
		selectRegion(region);
		
		
		
	}
	
	/**
	 * VISITOR  form fill 
	 */
	public AddPersonalDetails(WebDriver driver, 
			String fn, 
			String ln, 
			String email, 
			String phone,
			
			String pass,			
			String company,
			
			String addr,
			String City, 
			String PO, 
			String country, 
			String region ) throws InterruptedException {
		this.driver = driver;
		this.accountRegisterPage = new AccountRegisterPage(driver);
		setFirstName(fn);
		setLastName(ln);
		setEmail(email);
		setTelephone(phone);	
		
		setPassword(pass);
		setConfrimPassword(pass);
		
		
		setCompany(company);		
		setAddress1(addr);
		setAddress2(addr);
		setCity(City);
		setPostCode(PO);
		selectCountry(country);
		selectRegion(region);
		
		
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		accountRegisterPage.setFirstName(firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		accountRegisterPage.setLastName(lastName);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		accountRegisterPage.setEmail(email);
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
		accountRegisterPage.setTelephone(telephone);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		accountRegisterPage.setPassword(password);
	}

	public String getConfrimPassword() {
		return confrimPassword;
	}

	public void setConfrimPassword(String confrimPassword) {
		this.confrimPassword = confrimPassword;
		accountRegisterPage.setConfirmPassword(confrimPassword);
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
		accountRegisterPage.setCompany(company);
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
		accountRegisterPage.setAddress1(address1);
	}

	public String getAddress2() {
		return address2;		
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
		accountRegisterPage.setAddress2(address2);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		accountRegisterPage.setCity(city);
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode (String postCode) {
		this.postCode = postCode;
		accountRegisterPage.setPostCode(postCode);
	}

	public String getCountry() {
		return country;
	}

	public void selectCountry (String country) throws InterruptedException {
		this.country = country;
		accountRegisterPage.selectCountry(country);
	}

	public String getRegion() {
		return region;
	}

	public void selectRegion (String region) throws InterruptedException {
		this.region = region;
		accountRegisterPage.selectRegionState(region);
	}

	public String getVAT() {
		return VAT;
	}

	public void setVAT(String vAT) {
		this.VAT = vAT;
		
	}

}
