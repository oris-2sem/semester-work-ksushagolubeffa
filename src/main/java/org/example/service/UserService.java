package org.example.service;

import org.example.entity.*;
import org.example.entity.form.UserForm;
import org.example.exceptions.UserAlreadyExistException;
import org.example.exceptions.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface UserService {

    void deleteUser(Principal principal) throws UserNotFoundException;

    boolean arePasswordsEquals(Principal principal, String password);

    boolean saveUser(String username, String email, String password) throws IOException, UserAlreadyExistException;

    void updateUser(Principal principal, String username, String password, MultipartFile imageFile) throws IOException, UserNotFoundException;

    User findUserById(UUID userId) throws UserNotFoundException;

    List <MediaContent> getContentForCheck();

    void signInUser(UserForm form);

    List<MediaContent> findAllLikes(Principal principal);

    List<Product> findAllOrders(Principal principal);

    List<User> getAllUsers();

    User findUserByEmail(Principal principal) throws UserNotFoundException;

}
