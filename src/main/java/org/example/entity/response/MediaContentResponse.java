package org.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.User;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaContentResponse {
    private UUID id;
    private String name;
    private String description;
    private String username;
    private MediaContent.Status status;

    public MediaContentResponse(UUID id, String name, String description, String username) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.username = username;
    }

    public MediaContentResponse(UUID id, String name, String description, MediaContent.Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public MediaContentResponse(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
