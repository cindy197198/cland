package vn.edu.vinaenter.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.edu.vinaenter.models.Land;

public class CategoryValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Land.class);
	}

	@Override
	public void validate(Object obj, Errors error) {
		Land objLand=(Land) obj;
		if (objLand.getCat().getCname()=="") {
			error.reject("cat", "Vui lòng chọn tên danh mục");
		}
	}

}
