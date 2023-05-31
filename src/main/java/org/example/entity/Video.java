package org.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "video")
public class Video extends AbstractEntity{
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @Lob
    private byte[] bytes;
    @OneToOne
    private MediaContent content;
}
