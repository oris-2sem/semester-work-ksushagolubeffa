package org.example.repository;

import org.example.entity.Like;
import org.example.entity.MediaContent;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {

    @Query("SELECT l FROM Like l WHERE l.user = :user")
    List<MediaContent> findAllLikesByUser(User user);

    @Query("SELECT l FROM Like l WHERE l.user = :user AND l.content = :content")
    Like findByUserAndContent(@Param("user") User user, @Param("content") MediaContent content);
}
