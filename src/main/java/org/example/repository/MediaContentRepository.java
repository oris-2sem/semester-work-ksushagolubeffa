package org.example.repository;

import org.example.entity.MediaContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface MediaContentRepository extends JpaRepository<MediaContent, UUID> {
    MediaContent findMediaContentByName(String name);

    @Query("SELECT m FROM MediaContent m WHERE SIZE(m.likes) > (SELECT AVG(SIZE(mc.likes)) FROM MediaContent mc)")
    List<MediaContent> findMostLikedContent();
}
