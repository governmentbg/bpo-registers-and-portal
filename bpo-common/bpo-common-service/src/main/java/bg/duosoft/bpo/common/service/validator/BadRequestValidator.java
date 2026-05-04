package bg.duosoft.bpo.common.service.validator;


import bg.duosoft.bpo.common.util.exception.ValidationErrorException;
import bg.duosoft.bpo.common.util.validation.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class BadRequestValidator {

    public static <T extends Validator, O> void validateRequest(T validator, O object, Object... args) {
        List<ValidationError> errors = validator.validate(object, args);
        if (!CollectionUtils.isEmpty(errors)) {
            log.error("[BAD REQUEST] {} \n{}", errors, object);
            throw new ValidationErrorException(errors);
        }
    }

}