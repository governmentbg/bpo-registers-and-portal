package bg.duosoft.bpo.email.producer.dto;

/**
 * User: ggeorgiev
 * Date: 27.02.2025
 * Time: 16:10
 */
public interface TemplateType {
    String BASE = "BASE";
    String FEEDBACK = "FEEDBACK";
    String APPLICATION_STUDY_ERROR = "APPLICATION_STUDY_ERROR";
    String APPLICATION_STUDY_SUCCESS = "APPLICATION_STUDY_SUCCESS";
    String ERROR_MESSAGE = "ERROR_MESSAGE";
    String EMAIL_VERIFICATION = "EMAIL_VERIFICATION";
    String PASSWORD_RESET = "PASSWORD_RESET";
    String PASSWORD_CHANGED = "PASSWORD_CHANGED";
    String NEW_INCOMING_CORRESPONDENCE = "NEW_INCOMING_CORRESP";
}
