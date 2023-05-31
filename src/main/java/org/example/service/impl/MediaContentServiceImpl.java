package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.*;
import org.example.exceptions.NoSuchContentException;
import org.example.repository.*;
import org.example.service.MediaContentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MediaContentServiceImpl implements MediaContentService {

    private final MediaContentRepository repository;
    private final UserRepository userRepository;
    private final MediaCommentsRepository commentsRepository;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MediaContent> findAllContent() {
        return repository.findAll().stream().filter(content -> content.getStatus() == MediaContent.Status.ACCEPTED).collect(Collectors.toList());
    }

    @Override
    public List<MediaComments> findAllComments(MediaContent content) {
        List<MediaComments> commentList = new ArrayList<>();
        commentsRepository.findAll().forEach(comments -> {
            if (comments.getContent().getUuid().equals(content.getUuid())) {
                commentList.add(comments);
            }
        });
        return commentList;
    }

    @Override
    public Integer getAllLikes(MediaContent content) {
        return content.getLikes().size();
    }

    @Override
    public boolean isLiked(MediaContent content, Principal principal) {
        User user = getUserByPrincipal(principal);
        if (content.getLikes().stream().filter(like -> like.getUser() == user).count() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public MediaContent findContentById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateMediaContent(Principal principal, UUID id, String name, String description, MultipartFile imageFile) throws IOException, NoSuchContentException {
        User user = getUserByPrincipal(principal);
        MediaContent content = repository.findById(id).orElseThrow(() -> new NoSuchContentException(id));
        ;

        if (imageFile.getSize() != 0) {
            imageRepository.delete(content.getImage());
            Image image = toImageEntity(imageFile);
            imageRepository.save(image);
            content.setImage(image);
            repository.save(content);
            image.setContent(content);
            imageRepository.save(image);
        }
        if (user.getRole() == User.Role.ADMIN) {
            content.setStatus(MediaContent.Status.ACCEPTED);
        } else {
            content.setStatus(MediaContent.Status.UNDER_REVIEW);
        }
        content.setName(name);
        content.setDescription(description);
        repository.save(content);

    }

    @Override
    public void addMediaContent(Principal principal, String name, String description, MultipartFile imageFile, MultipartFile videoFile) throws IOException {
        User user = getUserByPrincipal(principal);
        MediaContent content = MediaContent.builder()
                .user(user)
                .description(description)
                .name(name)
                .likes(new ArrayList<>())
                .video(null)
                .image(null)
                .build();
        if (user.getRole() == User.Role.ADMIN) {
            content.setStatus(MediaContent.Status.ACCEPTED);
        } else {
            content.setStatus(MediaContent.Status.UNDER_REVIEW);
        }
        if (imageFile.getSize() == 0) {
            throw new RuntimeException();
        }
        Video video = toVideoEntity(videoFile);
        videoRepository.save(video);
        Image image = toImageEntity(imageFile);
        imageRepository.save(image);
        content.setVideo(video);
        content.setImage(image);
        repository.save(content);
        video.setContent(content);
        image.setContent(content);
        videoRepository.save(video);
        imageRepository.save(image);
        repository.save(content);
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByEmail(principal.getName());
    }

    @Override
    public boolean deleteMediaContent(User user, UUID id) throws NoSuchContentException {
        MediaContent content = repository.findById(id).orElseThrow(() -> new NoSuchContentException(id));

        if (content.getUser().getUuid().equals(user.getUuid())) {
            imageRepository.delete(content.getImage());
            videoRepository.delete(content.getVideo());
            repository.delete(content);
            return true;
        }
        return false;
    }

    @Override
    public List<MediaContent> findMostLiked() {
        return repository.findMostLikedContent();
    }

    @Override
    public List<MediaContent> searchMediaContent(String searchTerm) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<MediaContent> cq = cb.createQuery(MediaContent.class);
        Root<MediaContent> mediaContent = cq.from(MediaContent.class);

        Predicate searchPredicate = cb.like(mediaContent.get("description"), "%" + searchTerm + "%");

        cq.where(searchPredicate);

        TypedQuery<MediaContent> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    private Video toVideoEntity(MultipartFile file) throws IOException {
        Video video = new Video();
        video.setContentType(file.getContentType());
        video.setSize(file.getSize());
        video.setBytes(file.getBytes());
        return video;
    }
}
