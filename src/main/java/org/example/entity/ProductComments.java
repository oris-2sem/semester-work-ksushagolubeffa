package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.converters.DateConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments_product")
public class ProductComments extends AbstractEntity{

    @Column
    private String text;
    @Column
    private String date;
    @Column
    private String username;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private Product product;

    @PrePersist
    private void onCreate() {
        DateConverter converter = new DateConverter();
        date = converter.convert(LocalDate.now());
    }
}
