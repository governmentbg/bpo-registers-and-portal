package bg.duosoft.bpo.portal.service.impl;

import bg.duosoft.bpo.common.service.validator.BadRequestValidator;
import bg.duosoft.bpo.email.producer.dto.TemplateMailRequest;
import bg.duosoft.bpo.email.producer.dto.TemplateType;
import bg.duosoft.bpo.email.producer.service.MailQueueService;
import bg.duosoft.bpo.portal.dto.Feedback;
import bg.duosoft.bpo.portal.entity.FeedbackEntity;
import bg.duosoft.bpo.portal.enums.MessageType;
import bg.duosoft.bpo.portal.mapper.FeedbackMapper;
import bg.duosoft.bpo.portal.repository.FeedbackRepository;
import bg.duosoft.bpo.portal.service.FeedbackService;
import bg.duosoft.bpo.portal.validator.FeedbackValidator;
import bg.duosoft.bpo.publik.core.service.nomenclature.AttachmentService;
import bg.duosoft.bpo.publik.core.service.nomenclature.ObjectTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackValidator feedbackValidator;
    private final AttachmentService attachmentService;
    private final MailQueueService mailQueueService;
    private final ObjectTypeService objectTypeService;

    @Override
    public void saveFeedback(Feedback feedback) {
        BadRequestValidator.validateRequest(feedbackValidator, feedback);
        attachmentService.processAttachments(feedback.getAttachments(), "bpo-portal", "feedback");
        FeedbackEntity e = this.feedbackRepository.save(this.feedbackMapper.toEntity(feedback));
        List<Integer> attachmentIds = e.getAttachments() == null ? null : e.getAttachments().stream().map(r -> r.getAttachment().getId()).toList();
        this.sendEmail(feedback, attachmentIds);
    }

    private void sendEmail(Feedback feedback, List<Integer> attachmentIds) {
        mailQueueService.addTemplateMailToQueue(TemplateMailRequest.builder().template(getEmailTemplateName(feedback.getMessageType())).attachmentIds(attachmentIds).recipients(Collections.singletonList(feedback.getEmail())).templateParams(fillEmailParams(feedback)).build());
    }

    private String getEmailTemplateName(MessageType messageType) {
        if (Objects.requireNonNull(messageType) == MessageType.IP_OBJECT_ERROR) {
            return TemplateType.ERROR_MESSAGE;
        }
        return TemplateType.FEEDBACK;
    }

    private Map<String, String> fillEmailParams(Feedback feedback) {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("first_name", feedback.getFirstName());
        paramsMap.put("last_name", feedback.getLastName());
        paramsMap.put("email", feedback.getEmail());
        paramsMap.put("description", feedback.getDescription());
        if (feedback.getMessageType() == MessageType.IP_OBJECT_ERROR) {
            paramsMap.put("number", feedback.getApplicationNumber());
            paramsMap.put("type", this.objectTypeService.getById(feedback.getObjectType()).getDescription());
        }
        return paramsMap;
    }


}
