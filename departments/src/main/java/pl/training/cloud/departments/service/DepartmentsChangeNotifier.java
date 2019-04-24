package pl.training.cloud.departments.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Log
@RequiredArgsConstructor
public class DepartmentsChangeNotifier {

    @NonNull
    private Source source;

    @AfterReturning("@annotation(Notify)")
    public void sendNotification() {
        log.info("Sending change notification...");
        source.output().send(MessageBuilder.withPayload("departments-update").build());
    }

}
