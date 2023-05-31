package org.example.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.entity.MediaContent;
import org.example.entity.Product;
import org.example.entity.response.MediaContentResponse;
import org.example.service.MediaContentService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MediaContentRestController {

    private final MediaContentService service;

    //all works correct
    @PermitAll
    @GetMapping("/media/rest")
    public List<MediaContentResponse> getAllMediaContents(){
        List<MediaContent> contentList = service.findAllContent();
        List<MediaContentResponse> list = new ArrayList<>();
        contentList.forEach(content -> {
            list.add(new MediaContentResponse(
                    content.getUuid(), content.getName(), content.getDescription(), content.getUser().getUsername()
            ));
        });
        return list;
    }

    @GetMapping("/media/{id}/image")
    public ResponseEntity<?> showImage(@PathVariable("id") UUID id) throws IOException {
        MediaContent content = service.findContentById(id);
        return ResponseEntity.ok()
                .header("fileName", content.getName())
                .contentType(MediaType.valueOf(content.getImage().getContentType()))
                .contentLength(content.getImage().getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(content.getImage().getBytes())));
    }

    @GetMapping("/media/{id}/video")
    public ResponseEntity<?> showVideo(@PathVariable("id") UUID id) throws IOException {
        MediaContent content = service.findContentById(id);
        return ResponseEntity.ok()
                .header("fileName", content.getName())
                .contentType(MediaType.valueOf(content.getVideo().getContentType()))
                .contentLength(content.getVideo().getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(content.getVideo().getBytes())));
    }
}
