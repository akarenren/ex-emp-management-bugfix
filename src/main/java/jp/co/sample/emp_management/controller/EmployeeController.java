package jp.co.sample.emp_management.controller;

import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.InsertEmployeeForm;
import jp.co.sample.emp_management.form.UpdateEmployeeForm;
import jp.co.sample.emp_management.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員登録用フォーム生成.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public InsertEmployeeForm setUpInsertForm() {
		InsertEmployeeForm insertEmployeeForm = new InsertEmployeeForm();
		insertEmployeeForm.setGender("男性");
		return insertEmployeeForm;
		
		
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}


	
	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form
	 *            従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		
		return "redirect:/employee/showList";
	}
	
	/**
	 * 従業員検索機能.
	 * 
	 * @param name：従業員名
	 * @param model：モデル
	 * @return　従業員検索結果画面
	 */
	@RequestMapping("/search")
	public String search(String name, Model model) {
		if("".equals(name)) {
			return showList(model);
		}
		
		List<Employee> employeeList = employeeService.findByName(name);
		
		if(employeeList.size() == 0) {
			model.addAttribute("message", "１件もありませんでした");
			return showList(model);
		}
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
		
		
	}
	
	/**
	 * 従業員登録フォーム.
	 * 
	 * @return 従業員登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "employee/insert";
	}
	
	@RequestMapping("/insert")
	public String insert(@Validated InsertEmployeeForm form, BindingResult result, Model model) {
		Employee getEmployee = employeeService.findByMailAddress(form.getMailAddress());
		//重複メールアドレスチェック
		if(!(getEmployee == null)) {
			FieldError fieldError = new FieldError(result.getObjectName(), "mailAddress", "既にこのメールアドレスは登録されています");
			result.addError(fieldError);
		}
		
		//画像のバリデーション
		if(form.getImage().isEmpty()) {
			FieldError fieldError = new FieldError(result.getObjectName(), "image", "画像を選択してください");
			result.addError(fieldError);
		}
		
		if(result.hasErrors()) {
			return toInsert();
		}
		
		Employee employee = new Employee();
		BeanUtils.copyProperties(form, employee);
		
		
		employeeService.insert(employee, form);
		
		return "redirect:/employee/showList";
	}
}
