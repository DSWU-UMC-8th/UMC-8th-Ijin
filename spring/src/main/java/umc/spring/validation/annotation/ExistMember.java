package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.ExistMemberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistMemberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMember {
    String message() default "MEMBER_NOT_FOUND";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}