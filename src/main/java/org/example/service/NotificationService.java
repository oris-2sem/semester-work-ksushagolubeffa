package org.example.service;

import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.Notification;
import org.example.entity.User;
import org.example.exceptions.NoSuchContentException;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface NotificationService {

    void addNotification(User user, MediaContent content, MediaComments comment, Principal principal);

    void deleteNotificationByCommentId(UUID id);

    Notification findNotificationByCommentId(UUID id);

    void rejectContent(UUID id) throws NoSuchContentException;

    void acceptContent(UUID id) throws NoSuchContentException;

    List<Notification> getAll(Principal principal);

    void deleteNotification(UUID id);

    void deleteAll(Principal principal);

    User getUserByPrincipal(Principal principal);
}
