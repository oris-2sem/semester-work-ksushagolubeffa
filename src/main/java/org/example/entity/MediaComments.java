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
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments_media")
public class MediaComments extends AbstractEntity {

    @Column
    private String text;
    @Column
    private String username;
    @Column
    private String date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private MediaContent content;

    @PrePersist
    private void onCreate() {
        DateConverter converter = new DateConverter();
        date = converter.convert(LocalDate.now());
    }

}
