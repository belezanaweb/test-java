package br.com.blz.testjava.persistence.entity;

import com.google.common.base.Objects;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class ProductInventory {

    @Id
    @Column(name = "PRODUCT_UID")
    @GeneratedValue(strategy = SEQUENCE)
    private Long productId;

    @Column(name = "SKU")
    @Unique
    private Long sku;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WAREHOUSE_UID")
    private Warehouse warehouse;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInventory productInventory = (ProductInventory) o;
        return Objects.equal(productId, productInventory.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    @Override
    public String toString() {
        return "Product{" +
            "productId=" + productId +
            ", sku=" + sku +
            ", warehouse=" + warehouse +
            '}';
    }
}
