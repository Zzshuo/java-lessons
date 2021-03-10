package org.geektimes.projects.user.validator.bean.validation;

import org.geektimes.projects.user.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidAnnotationValidator implements ConstraintValidator<UserValid, User> {

    private int idRange;
    private int min;
    private int max;
    private Pattern p = Pattern.compile("^[0-9]{11}$");

    public void initialize(UserValid annotation) {
        this.idRange = annotation.idRange();
        min = annotation.min();
        max = annotation.max();
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {

        // 获取模板信息
        context.getDefaultConstraintMessageTemplate();

        String password = user.getPassword();
        String phoneNumber = user.getPhoneNumber();
        boolean bool = true;
        if (password.length() < min || password.length() > max) {
            bool = false;
        }
        Matcher matcher = p.matcher(phoneNumber);
        if (!matcher.matches()) {
            bool = false;
        }

        return bool;
    }
}
