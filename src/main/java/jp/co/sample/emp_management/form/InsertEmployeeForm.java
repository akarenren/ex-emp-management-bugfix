package jp.co.sample.emp_management.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


public class InsertEmployeeForm {

	/** 従業員名 */
	@NotBlank(message="名前を入力してください")
	private String name;
	/** 画像 */
	private MultipartFile image;
	/** 性別 */
	@NotBlank(message="性別を選択してください")
	private String gender;
	/** 入社日 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="入社日を選択してください")
	private Date hireDate;
	/** メールアドレス */
	@NotBlank(message="メールアドレスを入力してください")
	@Email(message = "形式が不正です")
	private String mailAddress;
	/** 郵便番号 */
	@NotBlank(message="郵便番号を入力してください")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}$", message="郵便番号形式にしてください")
	private String zipCode;
	/** 住所 */
	@NotBlank(message="住所を入力してください")
	private String address;
	/** 電話番号 */
	@NotBlank(message="電話番号を入力してください")
	private String telephone;
	/** 給料 */
	@NotNull(message="給料を入力してください")
	private Integer salary;
	/** 特性 */
	@NotBlank(message="特性を入力してください")
	private String characteristics;
	/** 扶養人数 */
	@NotNull(message="扶養人数を入力してください")
	private Integer dependentsCount;


	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Integer getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate="
				+ hireDate + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address
				+ ", telephone=" + telephone + ", salary=" + salary + ", characteristics=" + characteristics
				+ ", dependentsCount=" + dependentsCount + "]";
	}

}
