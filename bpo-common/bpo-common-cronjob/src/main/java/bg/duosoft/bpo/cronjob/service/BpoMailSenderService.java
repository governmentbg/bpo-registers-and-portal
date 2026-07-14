package bg.duosoft.bpo.cronjob.service;

import bg.duosoft.bpo.email.producer.dto.SimpleMailRequest;
import bg.duosoft.bpo.email.producer.service.MailQueueService;
import bg.duosoft.cronjob.mail.CronjobMailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * User: ggeorgiev
 * Date: 01.04.2025
 * Time: 15:57
 */
@RequiredArgsConstructor
public class BpoMailSenderService implements CronjobMailSenderService, ApplicationContextAware {
    private final MailQueueService mailQueueService;
    @Override
    public boolean sendEmail(String to, String header, String body) {
        mailQueueService.addSimpleMailToQueue(SimpleMailRequest.builder().subject(header).message(body).recipients(Arrays.stream(to.split(",")).map(r -> r.trim()).toList()).build());
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
