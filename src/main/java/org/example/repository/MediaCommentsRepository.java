package org.example.repository;

import org.example.entity.MediaComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediaCommentsRepository extends JpaRepository<MediaComments, UUID> {
}
