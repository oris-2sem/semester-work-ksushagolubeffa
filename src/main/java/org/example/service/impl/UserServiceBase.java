package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.*;
import org.example.entity.form.UserForm;
import org.example.exceptions.UserAlreadyExistException;
import org.example.exceptions.UserNotFoundException;
import org.example.repository.ImageRepository;
import org.example.repository.MediaContentRepository;
import org.example.repository.UserRepository;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceBase implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private MediaContentRepository contentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void deleteUser(Principal principal) throws UserNotFoundException {
        User user = repository.findUserByEmail(principal.getName());
        if(user==null){
            throw new UserNotFoundException(principal.getName());
        }
        imageRepository.delete(user.getImage());
        repository.delete(user);
    }

    @Override
    public boolean arePasswordsEquals(Principal principal, String password) {
        User user = repository.findUserByEmail(principal.getName());
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User findUserByEmail(Principal principal) throws UserNotFoundException {
        User user = repository.findUserByEmail(principal.getName());
        if (user==null){
            throw new UserNotFoundException(principal.getName());
        }
        return user;
    }

    @Override
    public boolean saveUser(String username, String email, String password) throws IOException, UserAlreadyExistException {
        if (repository.findUserByEmail(email) != null) {
            throw new UserAlreadyExistException(email, username);
        }
        if (repository.findUserByUsername(username)!=null) {
            throw new UserAlreadyExistException(username);
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(User.Role.USER)
                .balance(0)
                .state(User.State.ACTIVE)
                .build();
        ClassPathResource resource = new ClassPathResource("static/images/default.png");
        Image image = Image.builder()
                .contentType(MimeTypeUtils.parseMimeType(resource.getURL().openConnection().getContentType()).toString())
                .bytes(StreamUtils.copyToByteArray(resource.getInputStream()))
                .size(resource.contentLength())
                .build();
        imageRepository.save(image);
        user.setImage(image);
        repository.save(user);
        image.setUser(user);
        imageRepository.save(image);
        repository.save(user);
        return true;
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        return Image.builder()
                        .size(file.getSize())
                        .contentType(file.getContentType())
                        .bytes(file.getBytes())
                        .build();
    }

    @Override
    public void updateUser(Principal principal, String username, String password, MultipartFile imageFile) throws IOException, UserNotFoundException {
        List<User> list = repository.findAll().stream().filter(user -> user.getEmail().equals(principal.getName())).toList();
        if(list.size() < 1){
            throw new UserNotFoundException(principal.getName());
        }
        User user = list.get(0);
        if (imageFile.getSize()!=0) {
            if(user.getImage()!=null) {
                imageRepository.delete(user.getImage());
            }
            Image image = toImageEntity(imageFile);
            imageRepository.save(image);
            user.setImage(image);
            repository.save(user);
            image.setUser(user);
            imageRepository.save(image);
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        repository.save(user);
    }

    @Override
    public User findUserById(UUID userId) throws UserNotFoundException {
        Optional<User> userFromDb = repository.findById(userId);
        return userFromDb.orElseThrow(() -> new UserNotFoundException(userId));
    }


    @Override
    public List<MediaContent> getContentForCheck() {
        return contentRepository.findAll().stream().filter(content -> content.getStatus()== MediaContent.Status.UNDER_REVIEW).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllOrders(Principal principal) {
        User user = repository.findUserByEmail(principal.getName());
        List<Order> orders = new ArrayList<>();
        orderService.getAll().forEach(order -> {
            if(order.getUser().getUuid().equals(user.getUuid()) && order.isExecuted()){
                orders.add(order);
            }
        });
        if(orders!=null){
            return orderService.findProductsById(orders);
        }
        return null;
    }

    @Override
    @Transactional
    public void signInUser(UserForm form){
        repository.signInUser(form.getEmail(), form.getPassword());
    }

    @Override
    public List<MediaContent> findAllLikes(Principal principal) {
        User user = repository.findUserByEmail(principal.getName());
        List <MediaContent> list = new ArrayList<>();
        user.getLikes().forEach(like->{
            list.add(like.getContent());
        });
        return list;
    }
}
