package br.com.blz.testjava.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import static java.util.Objects.isNull;

@Data
@Document
public class Product implements Persistable<Long> {

    @Id
    private Long id;

    private String name;

    @CreatedDate
    @NotNull
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private Inventory inventory;

    @SuppressWarnings("unused")
    @Transient
    public boolean isMarketable() {
        return !isNull(inventory) && inventory.getQuantity() > 0;
    }

    @Override
    public boolean isNew() {
        return isNull(createdDate);
    }

}
