package bg.duosoft.bpo.email.producer.service.impl;

import bg.duosoft.bpo.email.producer.dto.MailBase;
import bg.duosoft.bpo.email.producer.dto.TemplateMailRequest;
import bg.duosoft.bpo.email.producer.dto.SimpleMailRequest;
import bg.duosoft.bpo.email.producer.service.EmailType;
import bg.duosoft.bpo.email.producer.exception.EmailQueueException;
import bg.duosoft.bpo.email.producer.exception.EmailTemplateNotFoundException;
import bg.duosoft.bpo.email.producer.property.EmailProducerPropertyAccess;
import bg.duosoft.bpo.email.producer.service.EmailNotificationMessage;
import bg.duosoft.bpo.email.producer.service.MailQueueService;
import bg.duosoft.bpo.email.producer.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

/**
 * User: ggeorgiev
 * Date: 25.02.2025
 * Time: 14:49
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailQueueServiceImpl implements MailQueueService {
    private final RabbitTemplate rabbitTemplate;
    private final EmailProducerPropertyAccess propertyAccess;
    @Override
    public UUID addTemplateMailToQueue(TemplateMailRequest mail) {
        checkTemplateDefinition(mail);
        return sendTemplateMailToQueue(mail);
    }

    public UUID addSimpleMailToQueue(SimpleMailRequest rq) {
        if (!ObjectUtils.isEmpty(rq.getRecipients())) {
            return sendSimpleMailToQueue(rq);
        }
        return null;
    }

    private static void checkTemplateDefinition(TemplateMailRequest request) {
        if (ObjectUtils.isEmpty(request.getTemplate())) {
            log.error("===[MAIL PRODUCER]=== Either email template or actionType should be provided for request {}", request);
            throw new EmailTemplateNotFoundException("===[MAIL PRODUCER]=== Either email template or actionType should be provided for request " + request);
        }
    }

    private void sendMailToQueue(String message) {
        try {
            rabbitTemplate.convertAndSend(propertyAccess.getEmail().getExchangeName(), propertyAccess.getEmail().getRoutekey(), message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EmailQueueException(e.getMessage(), e);
        }
    }

    private UUID sendTemplateMailToQueue(TemplateMailRequest rq) {
        EmailNotificationMessage.EmailNotificationMessageBuilder b = EmailNotificationMessage.builder();
        b.type(EmailType.TEMPLATE_MAIL)
                .template(rq.getTemplate())
                .templateParams(rq.getTemplateParams());
        addBaseMailParamsToMessage(rq, b);
        EmailNotificationMessage message = b.build();
        sendMailToQueue(JsonUtil.createJson(message));
        return message.getUuid();
    }

    private UUID sendSimpleMailToQueue(SimpleMailRequest request) {
        EmailNotificationMessage.EmailNotificationMessageBuilder b = EmailNotificationMessage.builder();
        b.type(EmailType.SIMPLE_MAIL)
                .message(request.getMessage())
                .subject(request.getSubject())
                .isHtml(request.isHtml());
        addBaseMailParamsToMessage(request, b);
        EmailNotificationMessage message = b.build();
        sendMailToQueue(JsonUtil.createJson(message));
        return message.getUuid();
    }
    private void addBaseMailParamsToMessage(MailBase rq, EmailNotificationMessage.EmailNotificationMessageBuilder builder) {
        builder
                .attachmentIds(rq.getAttachmentIds())
                .recipients(rq.getRecipients())
                .uuid(UUID.randomUUID())
                .cc(rq.getCc())
                .bcc(rq.getBcc());

    }

}
