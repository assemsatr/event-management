package com.SimpleEventMaster.awesomeAPP.config;
//we are mapping the class with properties file of the sms part


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

//@Configuration means this class will map configuration properties under prefix 'notification' from
// properties file. Here we need getters and setter because spring needs them to bind it with properties file.
//@validated here means, I have configuration of email server, and I want for ex: variable smtpHost to be validated,
//so all below variables are validated. This validated is very important because when we deploy.

//Now we configured our properties, and now what we have to do is that we need to configure our connection,
// so we need to create NotificationConfiguration class to send emails etc.

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "notification")//and also this will allow spring to scan this
// class and map it with properties file
public class NotificationProperties {

    private boolean enabled;
    private boolean smtpAuth;
    private String smtpStarttlsEnabled;
    private String smtpHost;
    private Integer smtpPort;
    private String smtpSslTrust;
    private String username;
    private String password;
    private String from;

    private String cron;
    private Integer countRetry;
}
