package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Like;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.example.exceptions.NoSuchContentException;
import org.example.repository.LikeRepository;
import org.example.repository.MediaContentRepository;
import org.example.repository.UserRepository;
import org.example.service.LikeService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;
    private final UserRepository userRepository;
    private final MediaContentRepository contentRepository;

    @Override
    public void addLike(Principal principal, UUID id) throws NoSuchContentException {
        User user = getUserByPrincipal(principal);
        MediaContent content = contentRepository.findById(id).orElseThrow(() -> new NoSuchContentException(id));;
        Like like = new Like(user, content);
        user.getLikes().add(like);
        if(content!=null){
            content.getLikes().add(like);
        }else {
            log.error("LikeService - add like", "content==null");
        }
        repository.save(like);
    }

    @Override
    public List<MediaContent> findAllUserLikes(Principal principal) {
        User user = getUserByPrincipal(principal);
        repository.findAllLikesByUser(user);
        return null;
    }


    @Override
    public void removeLike(Principal principal, UUID id) throws NoSuchContentException {
        MediaContent content = contentRepository.findById(id).orElseThrow(() -> new NoSuchContentException(id));;
        Like like = repository.findByUserAndContent(getUserByPrincipal(principal), content);
        if(like!=null){
            repository.delete(like);
        }else {
            log.error("LikeService - remove like", "like does not exist");
        }
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByEmail(principal.getName());
    }
}
