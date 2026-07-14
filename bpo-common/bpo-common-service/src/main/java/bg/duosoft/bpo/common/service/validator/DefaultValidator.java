package bg.duosoft.bpo.common.service.validator;

import bg.duosoft.bpo.common.util.validation.ValidationError;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

/**
 * User: ggeorgiev
 * Date: 30.01.2024
 * Time: 11:51
 */
public abstract class DefaultValidator<T> implements Validator<T>{
    @Override
    public final List<ValidationError> validate(T obj, Object... args) {
        ArrayList<ValidationError> errors = new ArrayList<>();
        validate(errors, obj, args);
        return errors;
    }
    protected abstract void validate(List<ValidationError> errors, T obj, Object... args);


    protected void rejectIfEmpty(List<ValidationError> errors, Object value, String pointer, String message) {
        if (Objects.isNull(value) || (value instanceof Optional<?> o && o.isEmpty())) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfEmpty(List<ValidationError> errors, Object value, String pointer) {
        rejectIfEmpty(errors, value, pointer, "Empty");
    }

    protected void rejectIfContentEmpty(List<ValidationError> errors, byte[] value, String pointer, String message) {
        if (Objects.isNull(value)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfNumberIsNull(List<ValidationError> errors, Integer value, String pointer, String message) {
        if (Objects.isNull(value)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfEmptyString(List<ValidationError> errors, String value, String pointer, String message) {
        if (!StringUtils.hasText(value)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfEmptyDate(List<ValidationError> errors, Date value, String pointer, String message) {
        if (Objects.isNull(value)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfEmptyBoolean(List<ValidationError> errors, Boolean value, String pointer, String message) {
        if (Objects.isNull(value)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected boolean rejectIfEmptyString(List<ValidationError> errors, String value, String pointer) {
        if (!StringUtils.hasText(value)) {
            errors.add(ValidationError.builder().pointer(pointer).message("Empty").build());
            return true;
        }
        return false;
    }

    protected void rejectIfNotMatchRegex(List<ValidationError> errors, String value, String regex, String pointer, String message) {
        if (StringUtils.hasText(value) && !value.matches(regex)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfMatchRegex(List<ValidationError> errors, String value, String regex, String pointer, String message) {
        if (StringUtils.hasText(value) && value.matches(regex)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfNotPositiveNumber(List<ValidationError> errors, Integer number, String pointer, String message) {
        if (Objects.nonNull(number) && number < 1) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected <E> void rejectIfEmptyCollection(List<ValidationError> errors, List<E> value, String pointer, String message) {
        if (CollectionUtils.isEmpty(value)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected <E> void rejectIfEmptyCollection(List<ValidationError> errors, List<E> value, String pointer) {
        if (CollectionUtils.isEmpty(value)) {
            errors.add(ValidationError.builder().pointer(pointer).message("Empty").build());
        }
    }

    protected void rejectIfBothAreNotTrue(List<ValidationError> errors, Boolean first, Boolean second, String pointer, String message) {
        if ((Objects.isNull(first) || !first) && (Objects.isNull(second) || !second)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfTrue(List<ValidationError> errors, Boolean value, String pointer, String message) {
        if (Objects.nonNull(value) && value) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfFalse(List<ValidationError> errors, Boolean value, String pointer, String message) {
        if (Objects.nonNull(value) && !value) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfFirstDateIsAfterSecond(List<ValidationError> errors, LocalDate firstDate, LocalDate secondDate, String pointer, String message) {
        if (firstDate.isAfter(secondDate)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfFirstDateIsBeforeSecond(List<ValidationError> errors, LocalDate firstDate, LocalDate secondDate, String pointer, String message) {
        if (firstDate.isBefore(secondDate)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfBothDatesAreEquals(List<ValidationError> errors, LocalDate firstDate, LocalDate secondDate, String pointer, String message) {
        if (firstDate.isEqual(secondDate)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfFirstDateIsAfterSecond(List<ValidationError> errors, Date firstDate, Date secondDate, String pointer, String message) {
        if (firstDate.after(secondDate)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void rejectIfFirstDateIsBeforeSecond(List<ValidationError> errors, Date firstDate, Date secondDate, String pointer, String message) {
        if (firstDate.before(secondDate)) {
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }

    protected void reject(List<ValidationError> errors, String pointer, String message) {
        errors.add(ValidationError.builder().pointer(pointer).message(message).build());
    }

    protected void rejectIfStringLengthBigger(List<ValidationError> errors, String value, int maxLength, String pointer){
        if(StringUtils.hasText(value) && value.length() > maxLength){
            errors.add(ValidationError.builder().pointer(pointer).message("validation.charCount.invalid."+maxLength).build());
        }
    }

    protected void rejectIfStringLengthBigger(List<ValidationError> errors, String value, int maxLength, String pointer, String message){
        if(StringUtils.hasText(value) && value.length() > maxLength){
            errors.add(ValidationError.builder().pointer(pointer).message(message).build());
        }
    }
}
