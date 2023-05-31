package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.exceptions.NoSuchContentException;
import org.example.repository.MediaCommentsRepository;
import org.example.repository.MediaContentRepository;
import org.example.repository.NotificationRepository;
import org.example.repository.UserRepository;
import org.example.service.MediaCommentsService;
import org.example.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MediaCommentsServiceImpl implements MediaCommentsService {

    private final MediaCommentsRepository repository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final MediaContentRepository contentRepository;

    @Override
    public void save(Principal principal, UUID id, String comment) throws NoSuchContentException {
        User user = getUserByPrincipal(principal);
        MediaContent content = contentRepository.findById(id).orElseThrow(() -> new NoSuchContentException(id));
        MediaComments comments = MediaComments.builder()
                        .text(comment)
                        .username(user.getUsername())
                        .content(contentRepository.findById(id).orElseThrow(() -> new NoSuchContentException(id)))
                        .build();
        repository.save(comments);
        notificationService.addNotification(content.getUser(), content, comments, principal);
    }

    @Transactional
    @Override
    public boolean delete(UUID id, UUID idComment) {
        MediaComments comment = repository.findById(idComment).orElse(null);
        if(comment!=null){
            if(comment.getContent().getUuid().equals(id)){
                repository.delete(comment);
                if(notificationService.findNotificationByCommentId(idComment)!=null) {
                    notificationService.deleteNotificationByCommentId(idComment);
                }
                return true;
            }
            log.error("Comment with id = {} is not found", id);
        } else{
            log.error("not such comment in database");
        }
        return false;
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByEmail(principal.getName());
    }
}
