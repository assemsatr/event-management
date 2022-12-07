package com.SimpleEventMaster.awesomeAPP.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//javaMailSender is an interface providing to us APIs for sending, creating massages. So you need
// to configure these connections, with our properties
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//after we configured the properties file and created the notificatioProperties class
//this class is for sending email

@Configuration
public class NotificationConfiguration {

    //my own mail sender, and we need to create notificationService so we can send email.
    @Bean
    public JavaMailSender getJavaMailSender(NotificationProperties properties) {
        final var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getSmtpHost());
        mailSender.setPort(properties.getSmtpPort());

        mailSender.setUsername(properties.getUsername());
        mailSender.setPassword(properties.getPassword());


        final var props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.socketFactory.port", properties.getSmtpPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        return mailSender;

    }

}
