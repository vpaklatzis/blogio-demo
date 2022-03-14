package com.vpaklatzis.blogio.service;

import com.vpaklatzis.blogio.exception.BlogioException;
import com.vpaklatzis.blogio.model.NotificationEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;
    private final MailContentBuilderService mailContentBuilderService;

    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("info@blogio.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setText(mailContentBuilderService.build(notificationEmail.getBody()));
        };

        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Activation email sent to " + notificationEmail.getRecipient());
        } catch (MailException e) {
            throw new BlogioException("An exception occured while sending email to " + notificationEmail.getRecipient());
        }
    }
}
