package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.converters.DateConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order extends AbstractEntity{

    @OneToOne
    private Product product;
    @Column
    private boolean isExecuted;
    @Column
    private String date;
    @Column
    private boolean selected;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    public Order(UUID userID, UUID productID, boolean isExecuted){
        user = new User();
        user.setUuid(userID);
        product = new Product();
        product.setUuid(productID);
        this.isExecuted = isExecuted;
    }


    @PrePersist
    private void onCreate() {
        DateConverter converter = new DateConverter();
        date = converter.convert(LocalDate.now());
    }
}
