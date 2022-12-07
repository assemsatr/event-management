package com.SimpleEventMaster.awesomeAPP.services;


import com.SimpleEventMaster.awesomeAPP.config.NotificationProperties;
import com.SimpleEventMaster.awesomeAPP.modelEntity.Event;
import com.SimpleEventMaster.awesomeAPP.modelEntity.EventStatus;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
//add to below @SpringBootApplication this @EnableScheduling, so you can schedule the emails
//we will send emails to the emails which is in event table(event entity.)And will send it with schedule. we will
//send emails as notifications with scheduler periodically.

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class NotificationService {

    private final EventRepository eventRepository;

    private final NotificationProperties properties;
    private final JavaMailSender javaMailSender;

//    @Scheduled(cron = "0 */1 * * * *")//you can write it like this or like below
    @Scheduled(cron = "${notification.cron}")//cron is special notation for periodically tasks i can say.
    // the timing, always set it in properties file
    public void sendNotification() {
        final var events = eventRepository
                .findByStartDateAfterAndStatus(LocalDate.now().minusDays(1), EventStatus.NEW);// we need gto add EventStatus.New because we want to send the events which has the status new
        if (events.isEmpty()) {
            return;
        }
        events.forEach(this::send);
        log.debug("Messages sending finished");
    }

    private void send(Event eventEntity) {
        try {
            final var mimeMessage = javaMailSender.createMimeMessage();
            final var helper = new MimeMessageHelper(mimeMessage, "utf-8");//"utf-8 is for languages etc, utf-8 is for correct encoding, for latin is 16, but for java 8"

            helper.setText("Some text", false);// if there was html to present, assign true. so false gonna send as plain text
            helper.setFrom(properties.getFrom());
            helper.setTo(eventEntity.getCreator().getEmail());
            helper.setSubject("Test subject");

            log.debug("Build invite email message is ()", helper);
            javaMailSender.send(mimeMessage);
            eventEntity.setStatus(EventStatus.SENT);
            log.debug("invite message sent for email{}", eventEntity.getCreator().getEmail());


        } catch (Exception e) {
            eventEntity.setStatus(EventStatus.ERROR);
            log.error("Exception occurred while sent message to {}", eventEntity.getCreator().getEmail(), e);

        }
        eventRepository.save(eventEntity);
    }
}
