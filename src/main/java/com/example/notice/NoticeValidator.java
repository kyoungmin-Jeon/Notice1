package com.example.notice;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class NoticeValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		
		return Notice.class.isAssignableFrom(clazz);
		
	}
	
	public void validate(Object object, Errors errors) {
		
		Notice notice = (Notice)object;
		
		String ttl = notice.getTtl();
		String cont = notice.getCont();
		String rgstrNm = notice.getRgstrNm();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ttl", "제목을 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cont", "내용을 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rgstrNm", "작성자를 입력해주세요");
		
		if (ttl == null || ttl.trim().isEmpty()) {
			errors.rejectValue("ttl", "제목을 작성해주세요");
		} else if (cont == null || cont.trim().isEmpty()) {
			errors.rejectValue("cont", "내용을 작성해주세요");
		} else if (rgstrNm == null || cont.trim().isEmpty()) {
			errors.rejectValue("rgstrNm", "작성자를 입력해주세요");
		}
		
	}
}
