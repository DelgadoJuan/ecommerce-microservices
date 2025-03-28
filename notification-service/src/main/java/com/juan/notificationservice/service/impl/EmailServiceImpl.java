package com.juan.notificationservice.service.impl;

import com.juan.notificationservice.dto.Product;
import com.juan.notificationservice.enums.EmailTemplate;
import com.juan.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    @Override
    public void sendPaymentSuccessEmail(String destinationEmail, String customerName,
                                        BigDecimal amount, String orderReference) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        helper.setFrom("juandelgado@live.com.ar");
        final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            helper.setText(html, true);
            helper.setTo(destinationEmail);
            javaMailSender.send(message);
            log.info(String.format("INFO - Email successfully sent to %s", destinationEmail));
        } catch (MessagingException e) {
            throw new MessagingException("Failed to send email", e);
        }
    }

    @Async
    @Override
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName,
                                           BigDecimal amount, String orderReference,
                                           List<Product> products) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        helper.setFrom("juandelgado@live.com.ar");
        final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());

        try {
            String html = templateEngine.process(templateName, context);
            helper.setText(html, true);
            helper.setTo(destinationEmail);
            javaMailSender.send(message);
            log.info(String.format("INFO - Email successfully sent to %s", destinationEmail));
        } catch (MessagingException e) {
            throw new MessagingException("Failed to send email", e);
        }
    }
}
