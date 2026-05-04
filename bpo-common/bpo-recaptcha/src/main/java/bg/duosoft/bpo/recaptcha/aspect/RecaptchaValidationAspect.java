package bg.duosoft.bpo.recaptcha.aspect;


import bg.duosoft.bpo.common.util.exception.ValidationErrorException;
import bg.duosoft.bpo.common.util.validation.ValidationError;
import bg.duosoft.bpo.recaptcha.service.RecaptchaService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Aspect to handle reCAPTCHA validation by intercepting methods with the @ValidateRecaptcha annotation.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class RecaptchaValidationAspect {

    private final RecaptchaService recaptchaService;

    @Before("@annotation(bg.duosoft.bpo.recaptcha.annotation.ValidateRecaptcha)")
    public void validateRecaptchaToken(JoinPoint joinPoint) throws Exception {
        Method method = getMethodFromJoinPoint(joinPoint);
        Object[] args = joinPoint.getArgs();
        Annotation[][] paramAnnotations = method.getParameterAnnotations();

        for (int i = 0; i < paramAnnotations.length; i++) {
            for (Annotation annotation : paramAnnotations[i]) {
                if (annotation instanceof RequestBody) {
                    Object dtoObject = args[i];
                    String recaptchaToken = getRecaptchaToken(dtoObject);
                    executeValidation(recaptchaToken);
                }
            }
        }
    }

    private void executeValidation(String recaptchaToken) {
        if (recaptchaToken != null && !recaptchaService.verifyRecaptcha(recaptchaToken)) {
            List<ValidationError> errors = new ArrayList<>();
            errors.add(ValidationError.builder().pointer("recaptchaValidationPointer").message("validation.recaptcha.verification.failed").build());
            throw new ValidationErrorException(errors);
        }
    }

    private String getRecaptchaToken(Object dtoObject) throws IllegalAccessException {
        if (dtoObject != null) {
            for (Field field : dtoObject.getClass().getDeclaredFields()) {
                if ("recaptchaToken".equals(field.getName())) {
                    field.setAccessible(true);
                    Object value = field.get(dtoObject);
                    if (value instanceof String) {
                        return (String) value;
                    }
                }
            }
        }
        return null;
    }

    private Method getMethodFromJoinPoint(JoinPoint joinPoint) throws NoSuchMethodException {
        String methodName = joinPoint.getSignature().getName();
        Class<?>[] parameterTypes = ((org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature()).getParameterTypes();
        return joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes);
    }
}
