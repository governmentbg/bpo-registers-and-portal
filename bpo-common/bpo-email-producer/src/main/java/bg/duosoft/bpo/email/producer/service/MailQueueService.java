package bg.duosoft.bpo.email.producer.service;

import bg.duosoft.bpo.email.producer.dto.TemplateMailRequest;
import bg.duosoft.bpo.email.producer.dto.SimpleMailRequest;

import java.util.UUID;

/**
 * User: ggeorgiev
 * Date: 25.02.2025
 * Time: 14:48
 */
public interface MailQueueService {
    UUID addTemplateMailToQueue(TemplateMailRequest request);
    UUID addSimpleMailToQueue(SimpleMailRequest request);
}
