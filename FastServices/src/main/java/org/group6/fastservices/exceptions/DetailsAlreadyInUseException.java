package org.group6.fastservices.exceptions;

public class DetailsAlreadyInUseException extends RuntimeException {
    public DetailsAlreadyInUseException(String message) {
        super(message);
    }
}

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(String to, String name) {
        if (mailSender == null) {
            logger.warn("MailSender is not configured. Skipping email to: {}", to);
            return;
        }

        String subject = "Welcome to FastServices!";
        String body = String.format(
                "Hello %s,\n\nThank you for registering with FastServices! We're glad to have you on board.\n\nBest Regards,\nThe FastServices Team",
                name
        );

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);

            logger.info("✅ Registration email sent successfully to: {}", to);

        } catch (Exception e) {
            logger.error("❌ Error occurred while sending registration email to: {}", to, e);
        }
    }
}


