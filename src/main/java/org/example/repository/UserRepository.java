package org.example.repository;

import org.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

//    Page<User> findAllUsers();

    User findUserByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.balance = :newBalance WHERE u.uuid = :uuid")
    void setBalance(@Param("newBalance") int newBalance, @Param("uuid") UUID uuid);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User signInUser(@Param("email") String email, @Param("password") String password);

    User findUserByEmail(String email);

//    @Query("update User user SET user.username = :username WHERE user.uuid = :id")
//    void updateUsername(@Param("username") String username, @Param("id") UUID uuid);
//
//    @Modifying
//    @Query("update User user SET user.password = :password WHERE user.uuid = :id")
//    void updatePassword(@Param("password") String password, @Param("id") UUID uuid);
}
