package br.com.blz.testjava.domain;

public class Warehouse {

    private String locality;
    private Integer quantity;
    private String type;

    private Warehouse(Builder builder) {
        setLocality(builder.locality);
        setQuantity(builder.quantity);
        setType(builder.type);
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
            "locality='" + locality + '\'' +
            ", quantity=" + quantity +
            ", type='" + type + '\'' +
            '}';
    }


    public static final class Builder {
        private String locality;
        private Integer quantity;
        private String type;

        public Builder() {
        }

        public Builder withLocality(String val) {
            locality = val;
            return this;
        }

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
