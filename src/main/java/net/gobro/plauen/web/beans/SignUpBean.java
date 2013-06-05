package net.gobro.plauen.web.beans;

public class SignUpBean {

	private boolean agreedWithTermsOfUse;

	private String captcha;

	private String city;

	private String email;

	private String firstName;

	private String lastName;

	private String login;

	private String password;

	private String passwordConfirm;

	private String phone;

	private String street;

	private String zip;

	public String getCaptcha() {
		return captcha;
	}

	public String getCity() {
		return city;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public String getPhone() {
		return phone;
	}

	public String getStreet() {
		return street;
	}

	public String getZip() {
		return zip;
	}

	public boolean isAgreedWithTermsOfUse() {
		return agreedWithTermsOfUse;
	}

	public void setAgreedWithTermsOfUse(boolean agreedWithTermsOfUse) {
		this.agreedWithTermsOfUse = agreedWithTermsOfUse;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
