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

	private String shippingFirstName;
	private String shippingLastName;
	private String shippingCompany;
	private String shippingAddress1;
	private String shippingAddress2;
	private String shippingCity;
	private String shippingPostCode;
	private String shippingCountry;
	private String shippingRegion;
	
	
	public AddPersonalDetails(WebDriver driver) {
		this.driver = driver;
		this.accountRegisterPage = new AccountRegisterPage(driver);
	}

		/**
	 * REGISTERED USER  form fill.
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
	 * GUEST SHIPPING ADDRESS form fill 
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
			String region,

			String ShippingFirstName,
			String ShippingLastName,
			String ShippingCompany,
			String ShippingAddress1,
			String ShippingAddress2,
			String ShippingCity,
			String ShippingPostCode,
			String ShippingCountry,
			String ShippingRegion		
			
			) throws InterruptedException {
		this.driver = driver;
		this.accountRegisterPage = new AccountRegisterPage(driver);
				// Your Personal Details
		setFirstName(fn);
		setLastName(ln);
		setEmail(email);
		setTelephone(phone);		
		setCompany(company);
				// Billing Address details
		setAddress1(addr);
		setAddress2(addr);
		setCity(City);
		setPostCode(PO);
		selectCountry(country);
		selectRegion(region);
				// Shipping Address details
		setShippingFirstName(ShippingFirstName);
		setShippingLastName(ShippingLastName);
		setShippingCompany(ShippingCompany);
		setShippingAddress1(ShippingAddress1);
		setShippingAddress2(ShippingAddress2);
		setShippingCity(ShippingCity);
		setShippingPostCode(ShippingPostCode);
		
		selectShippingCountry(ShippingCountry);
		selectShippingRegion(ShippingRegion);
		
		
	}	
	
	/**
	 * Shipping Address 
	 */
	 public String getShippingFirstName() {
		return shippingFirstName;
	 }

	 public void setShippingFirstName(String shippingFirstName) {
		this.shippingFirstName = shippingFirstName;
		accountRegisterPage.setBillngFirstName(shippingFirstName);
	}

	public String getShippingLastName() {
		return shippingLastName;
	}
	public void setShippingLastName(String shippingLAstName) {
		this.shippingLastName = shippingLAstName;
		accountRegisterPage.setBillingLastName(shippingLAstName);
	}

	public String getShippingCompany() {
		return shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
		accountRegisterPage.setShippingCompany(shippingCompany);
	}

	public String getShippingAddress1() {
		return shippingAddress1;
	}

	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
		accountRegisterPage.setShippingAddress1(shippingAddress1);
	}

	public String getShippingAddress2() {
		return shippingAddress2;
	}

	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
		accountRegisterPage.setShippingAddress2(shippingAddress2);
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
		accountRegisterPage.setShippingCity(shippingCity);
	}

	public String getShippingPostCode() {
		return shippingPostCode;
	}

	public void setShippingPostCode(String shippingPostCode) {
		this.shippingPostCode = shippingPostCode;
		accountRegisterPage.setShippingPostCode(shippingPostCode);
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void selectShippingCountry (String shippingCountryy) throws InterruptedException {
		this.shippingCountry = shippingCountryy;
		System.out.println("Shipping Country is " + shippingCountry);
		accountRegisterPage.selectShippingCountry(shippingCountry);
		
	}

	public String getShippingRegion() {
		return shippingRegion;
	}

	public void selectShippingRegion (String shippingRegionn) throws InterruptedException {
		this.shippingRegion = shippingRegionn;
		System.out.println("Shipping Region is " + shippingRegion);
		accountRegisterPage.selectShippingRegionState(shippingRegion);
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
