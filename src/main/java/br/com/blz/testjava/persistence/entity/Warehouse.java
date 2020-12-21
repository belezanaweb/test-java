package br.com.blz.testjava.persistence.entity;

import br.com.blz.testjava.enums.BrazilianStates;
import br.com.blz.testjava.enums.WarehouseType;
import br.com.blz.testjava.persistence.converter.BrazilianStatesConverter;
import br.com.blz.testjava.persistence.converter.WarehouseTypeConverter;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Warehouse {

    @Id
    @Column(name = "WAREHOUSE_UID")
    @GeneratedValue(strategy = SEQUENCE)
    private Long warehouseID;

    @Column(name = "LOCALITY")
    @Convert(converter = BrazilianStatesConverter.class)
    private BrazilianStates locality;

    @OneToMany(fetch = LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "WAREHOUSE_UID", referencedColumnName = "WAREHOUSE_UID")
    private List<ProductInventory> productInventories = new ArrayList<>();

    @Column(name = "TYPE")
    @Convert(converter = WarehouseTypeConverter.class)
    private WarehouseType type;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_TIME")
    private LocalDateTime updatedTime;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    public Long getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Long warehouseID) {
        this.warehouseID = warehouseID;
    }

    public BrazilianStates getLocality() {
        return locality;
    }

    public void setLocality(BrazilianStates locality) {
        this.locality = locality;
    }

    public List<ProductInventory> getProducts() {
        return productInventories;
    }

    public void setProducts(List<ProductInventory> productInventories) {
        this.productInventories = productInventories;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
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
        Warehouse warehouse = (Warehouse) o;
        return Objects.equal(warehouseID, warehouse.warehouseID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(warehouseID);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
            "warehouseID=" + warehouseID +
            ", locality=" + locality +
            ", products=" + productInventories +
            ", type=" + type +
            '}';
    }
}
