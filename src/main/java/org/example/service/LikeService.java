package org.example.service;

import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.exceptions.NoSuchContentException;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface LikeService {

    void addLike(Principal principal, UUID id) throws NoSuchContentException;

    List<MediaContent> findAllUserLikes(Principal principal);

    void removeLike(Principal principal, UUID id) throws NoSuchContentException;

    User getUserByPrincipal(Principal principal);
}
