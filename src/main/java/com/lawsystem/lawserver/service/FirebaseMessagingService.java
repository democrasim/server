package com.lawsystem.lawserver.service;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.lawsystem.lawserver.model.Law;
import com.lawsystem.lawserver.model.Member;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    private static final String LAW_NAME = "חוק מספר #$";

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    private static Map<String, String> lawToData(Law law) {
        HashMap<String, String> map = new HashMap<>();

        map.put("id", law.getId() + "");
        map.put("content", law.getContent().toString());

        return map;
    }

    public String sendNotification(Law law, Member member) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(LAW_NAME.replace("$", law.getId() + ""))
                .setBody(law.getContent().toString()).build();

        Message message = Message
                .builder()
                .setToken(member.getToken())
                .setNotification(notification)
                .putAllData(lawToData(law))

                .build();

        return firebaseMessaging.send(message);
    }
}
