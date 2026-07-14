package bg.duosoft.bpo.common.service.validator;

import bg.duosoft.bpo.common.util.validation.ValidationError;

import java.util.List;

public interface Validator<T> {
    List<ValidationError> validate(T obj, Object... args);

}
