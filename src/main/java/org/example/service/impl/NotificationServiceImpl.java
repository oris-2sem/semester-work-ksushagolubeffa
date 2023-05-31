package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.Notification;
import org.example.entity.User;
import org.example.exceptions.NoSuchContentException;
import org.example.repository.MediaContentRepository;
import org.example.repository.NotificationRepository;
import org.example.repository.UserRepository;
import org.example.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final UserRepository userRepository;
    private final MediaContentRepository contentRepository;


    @Override
    public void addNotification(User user, MediaContent content, MediaComments comment, Principal principal) {
        User author = getUserByPrincipal(principal);
        Notification notification = new Notification(user, content, comment, author);
        repository.save(notification);
    }

    @Override
    public void deleteNotificationByCommentId(UUID id) {
        repository.deleteNotificationByCommentId(id);

    }

    @Override
    public Notification findNotificationByCommentId(UUID id) {
        return repository.findNotificationByCommentId(id);
    }

    @Override
    public void acceptContent(UUID id) throws NoSuchContentException {
        MediaContent content = contentRepository.findById(id).orElseThrow(() -> new NoSuchContentException(id));

        content.setStatus(MediaContent.Status.ACCEPTED);

        Notification notification = new Notification(content.getUser(), content, Notification.Message.ACCEPTED);
        repository.save(notification);
    }


    @Override
    public void rejectContent(UUID id) throws NoSuchContentException {
        MediaContent content = contentRepository.findById(id).orElseThrow(() -> new NoSuchContentException(id));
        content.setStatus(MediaContent.Status.REJECTED);
        Notification notification = new Notification(content.getUser(), content, Notification.Message.REJECTED);
        repository.save(notification);
    }

    @Override
    public List<Notification> getAll(Principal principal) {
        User user = getUserByPrincipal(principal);
        return repository.getAllByUser(user);
    }

    @Transactional
    @Override
    public void deleteNotification(UUID id) {
        repository.deleteNotificationByUuid(id);
    }

    @Transactional
    @Override
    public void deleteAll(Principal principal) {
        User user = getUserByPrincipal(principal);
        repository.deleteNotificationsByUser(user);
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByEmail(principal.getName());
    }
}
