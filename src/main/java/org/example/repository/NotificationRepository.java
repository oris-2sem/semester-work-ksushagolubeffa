package org.example.repository;

import org.example.entity.Notification;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    void deleteNotificationsByUser(User user);

    List<Notification> getAllByUser(User user);

    void deleteNotificationByUuid(UUID id);

    @Query("select n from Notification n where n.comment.uuid = :id")
    Notification findNotificationByCommentId(@Param("id") UUID id);

    @Modifying
    @Query("delete from Notification n where n.comment.uuid = :id")
    void deleteNotificationByCommentId(@Param("id") UUID id);
}
