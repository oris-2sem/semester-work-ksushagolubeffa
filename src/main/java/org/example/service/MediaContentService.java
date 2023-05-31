package org.example.service;

import org.example.entity.MediaComments;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.exceptions.NoSuchContentException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface MediaContentService {

    List<MediaContent> findAllContent();

    List<MediaComments> findAllComments(MediaContent content);

    Integer getAllLikes(MediaContent content);

    boolean isLiked(MediaContent content, Principal principal);

    User getUserByPrincipal(Principal principal);

    MediaContent findContentById(UUID id);

    void updateMediaContent(Principal principal, UUID id, String name, String description, MultipartFile imageFile) throws IOException, NoSuchContentException;

    void addMediaContent(Principal principal,  String name, String description, MultipartFile imageFile, MultipartFile videoFile) throws IOException;

    boolean deleteMediaContent(User user, UUID id) throws NoSuchContentException;

    List<MediaContent> findMostLiked();

    List<MediaContent> searchMediaContent(String searchTerm);
}
