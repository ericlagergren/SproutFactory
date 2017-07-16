package models;

import utils.UUID;

import java.math.BigDecimal;

public class Product {
    public UUID id;
    public String name;
    public BigDecimal price;
    public boolean isNew;

    public Product(String name, BigDecimal price, boolean productIsNew) {
        this(null, name, price, productIsNew);
    }

    public Product(UUID id, String name, BigDecimal price, boolean productIsNew) {
        this.id = id;
        this.name = name;
        this.price = price == null ? new BigDecimal(0) : price;
        this.isNew = productIsNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (isNew != product.isNew) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return price != null ? price.equals(product.price) : product.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isNew ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isNew=" + isNew +
                '}';
    }
}