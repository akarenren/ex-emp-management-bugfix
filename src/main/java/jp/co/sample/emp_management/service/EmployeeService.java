package jp.co.sample.emp_management.service;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.InsertEmployeeForm;
import jp.co.sample.emp_management.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得します.
	 * 
	 * @return　従業員情報一覧
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}
	
	/**
	 * 従業員情報を取得します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 * @throws 検索されない場合は例外が発生します
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}
	
	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee　更新した従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
	
	/**
	 * 従業員検索.
	 * 
	 * @param name 従業員名
	 * @return　入力された名前に該当する従業員
	 */
	public List<Employee> findByName(String name) {
		return employeeRepository.findByName(name);
	}
	
	
	/**
	 * 従業員登録.
	 * 
	 * @param employee:従業員情報
	 */
	public void insert(Employee employee, InsertEmployeeForm form) {
		Integer nextId = employeeRepository.FindLastid() + 1;
		employee.setId(nextId);
		
		
		//画像データをBase64にエンコード
		try {
			StringBuffer data = new StringBuffer();
			String base64 = new String(Base64.encodeBase64(form.getImage().getBytes()),"ASCII");
			
			String fileName = form.getImage().getOriginalFilename();
			//ファイル拡張子
			String extension = fileName.substring(fileName.lastIndexOf("."));
			if(".png".equals(extension)) {
				data.append("data:image/png;base64,");
			} else {
				data.append("data:image/jpeg;base64,");
			}
			
	        data.append(base64);
	        employee.setImage(data.toString());
		} catch (Exception e) {
			System.out.println("画像変換でエラー発生");
			System.err.println("メッセージ：" + e);
		}
		
		employeeRepository.insert(employee);
	}
	
	/**
	 * メールアドレスによる従業員検索.
	 * 
	 * @param mailAddress:メールアドレス
	 * @return　従業員情報
	 */
	public Employee findByMailAddress(String mailAddress) {
		return employeeRepository.findByMailAddress(mailAddress);
	}
	
	public Integer findLastId() {
		return employeeRepository.FindLastid();
	}
}
