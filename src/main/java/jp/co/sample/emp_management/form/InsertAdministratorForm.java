package jp.co.sample.emp_management.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.util.StringUtils;



/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank
	private String name;
	/** メールアドレス */
	@NotBlank
	@Email(message = "形式が不正です")
	private String mailAddress;
	/** パスワード */
	@NotBlank
	private String password;
	/** 確認用パスワード */
	@NotBlank
	private String confPassword;
	@AssertTrue(message = "パスワードが一致していません")
	public boolean ischeckPass() {
		if(StringUtils.isEmpty(password)) {
			return true;
		}
		if(!password.equals(confPassword)) {
			return false;
		}
		return true;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}
	
}
