package com.sixliu.app.user.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sixliu.app.user.service.RoleService;

/**
*@author:MG01867
*@date:2018年11月20日
*@email:359852326@qq.com
*@version:
*@describe //TODO
*/
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleValidation.Checker.class)
@Documented
public @interface RoleValidation {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value() default "";

	boolean inclusive() default true;

	public class Checker implements ConstraintValidator<RoleValidation, String> {

		@Autowired
		private RoleService roleService;

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if(StringUtils.isBlank(value)) {
				return false;
			}
			if (null == roleService.get(value)) {
				return false;
			}
			return true;
		}
	}
}