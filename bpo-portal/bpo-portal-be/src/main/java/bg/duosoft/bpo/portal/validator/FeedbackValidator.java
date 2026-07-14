package bg.duosoft.bpo.portal.validator;

import bg.duosoft.bpo.common.service.validator.DefaultValidator;
import bg.duosoft.bpo.common.util.regex.RegexUtils;
import bg.duosoft.bpo.common.util.validation.ValidationError;
import bg.duosoft.bpo.portal.dto.Feedback;
import bg.duosoft.bpo.portal.enums.MessageType;
import bg.duosoft.bpo.publik.core.dto.common.FileStoreEntryDTO;
import bg.duosoft.bpo.registers.dto.ipobject.IpObjectShortDTO;
import bg.duosoft.bpo.registers.service.ipobject.IpObjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static bg.duosoft.bpo.common.service.validator.ValidatorConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeedbackValidator extends DefaultValidator<Feedback> {

    @Autowired
    private final IpObjectService ipObjectService;

    @Override
    protected void validate(List<ValidationError> errors, Feedback obj, Object... args) {
        validateFirstName(errors, obj);
        validateLastName(errors, obj);
        validateDescription(errors, obj);
        validateEmail(errors, obj);
        validateObjectType(errors, obj);
        validateApplicationNumber(errors, obj);
        validateMessageType(errors, obj);
        validateAttachments(errors, obj);
    }

    private void validateFirstName(List<ValidationError> errors, Feedback obj) {
        String firstName = obj.getFirstName();

        rejectIfEmpty(errors, firstName, "firstName", "validation.field.required");
        if (StringUtils.hasText(firstName)) {
            rejectIfTrue(errors, firstName.length() > MAX_INPUT_LENGTH_XXS, "firstName", "validation.field.max.length");
        }
    }

    private void validateLastName(List<ValidationError> errors, Feedback obj) {
        String lastName = obj.getLastName();

        rejectIfEmpty(errors, lastName, "lastName", "validation.field.required");
        if (StringUtils.hasText(lastName)) {
            rejectIfTrue(errors, lastName.length() > MAX_INPUT_LENGTH_XXS, "lastName", "validation.field.max.length");
        }
    }

    private void validateDescription(List<ValidationError> errors, Feedback obj) {
        String description = obj.getDescription();

        rejectIfEmpty(errors, description, "description", "validation.field.required");
        if (StringUtils.hasText(description)) {
            rejectIfTrue(errors, description.length() > MAX_INPUT_LENGTH_XXL, "description", "validation.field.max.length");
        }
    }

    private void validateEmail(List<ValidationError> errors, Feedback obj) {
        String email = obj.getEmail();

        rejectIfEmpty(errors, email, "email", "validation.field.required");
        if (StringUtils.hasText(email)) {
            rejectIfTrue(errors, email.length() > MAX_INPUT_LENGTH_S, "email", "validation.field.max.length");
            rejectIfNotMatchRegex(errors, email, RegexUtils.EMAIL_REGEX, "email", "validation.field.invalid.email");
        }
    }

    public void validateObjectType(List<ValidationError> errors, Feedback obj) {
        String objectType = obj.getObjectType();

        rejectIfTrue(errors, Objects.isNull(objectType) && MessageType.IP_OBJECT_ERROR == obj.getMessageType(), "objectType", "validation.field.required");
        if (objectType != null) {
            rejectIfTrue(errors, objectType.length() > MAX_INPUT_LENGTH_20, "objectType", "validation.field.max.length");
        }
    }

    public void validateApplicationNumber(List<ValidationError> errors, Feedback obj) {
        String applicationNumber = obj.getApplicationNumber();

        rejectIfTrue(errors, Objects.isNull(applicationNumber) && MessageType.IP_OBJECT_ERROR == obj.getMessageType(), "applicationNumber", "validation.field.required");
        if (applicationNumber != null) {
            IpObjectShortDTO ipObjectShortDTO = this.ipObjectService.getObjectById(applicationNumber);
            rejectIfTrue(errors, Objects.isNull(ipObjectShortDTO), "applicationNumber", "validation.field.invalid.applicationNumber");
            if (!Objects.isNull(ipObjectShortDTO)) {
                rejectIfFalse(errors, Objects.equals(ipObjectShortDTO.getObjectType(), obj.getObjectType()), "objectType", "validation.field.invalid.objectType");
            }
            rejectIfTrue(errors, applicationNumber.length() > MAX_INPUT_LENGTH_20, "applicationNumber", "validation.field.max.length");
        }
    }


    public void validateMessageType(List<ValidationError> errors, Feedback obj) {
        MessageType messageType = obj.getMessageType();

        rejectIfEmpty(errors, messageType, "messageType", "validation.field.required");
        if (Objects.nonNull(messageType)) {
            rejectIfBothAreNotTrue(errors, messageType == MessageType.IP_OBJECT_ERROR, (obj.getObjectType() == null && obj.getApplicationNumber() == null), "messageType", "validation.field.wrong.FEEDBACK.data");
            rejectIfBothAreNotTrue(errors, messageType == MessageType.FEEDBACK, (obj.getObjectType() != null && obj.getApplicationNumber() != null), "messageType", "validation.field.wrong.IP_OBJECT_ERROR.data");
        }
    }

    public void validateAttachments(List<ValidationError> errors, Feedback obj) {
        List<FileStoreEntryDTO> attachments = obj.getAttachments();

        if (attachments != null) {
            rejectIfTrue(errors, attachments.size() > MAX_INPUT_LENGTH_20, "attachments", "validation.field.max.attachments");
        }
    }

}
