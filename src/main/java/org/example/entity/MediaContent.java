package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.converters.DateConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "media_content")
public class MediaContent extends AbstractEntity{

    @Column(unique = true)
    private String name;
    @OneToOne(mappedBy = "content", optional = false)
    private Video video;
    @OneToOne(mappedBy = "content", optional = false)
    private Image image;
    @Column
    private String description;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column
    private String date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "content")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "content")
    private List<MediaComments> comments = new ArrayList<>();

    public enum Status{
        ACCEPTED, REJECTED, UNDER_REVIEW
        }

    @PrePersist
    private void onCreate() {
        DateConverter converter = new DateConverter();
        date = converter.convert(LocalDate.now());
    }

}
