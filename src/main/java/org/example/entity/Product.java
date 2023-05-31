package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.converters.DateConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "products")
public class Product extends AbstractEntity{

    @Column(unique = true)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @OneToOne(mappedBy = "product", optional = false)
    private Image image;
    @Column
    private Integer price;
    @Column
    private Integer usdPrice;
    @Column
    private Integer eurPrice;
    @Column
    private String date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductComments> comments = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        DateConverter converter = new DateConverter();
        date = converter.convert(LocalDate.now());
    }
}
