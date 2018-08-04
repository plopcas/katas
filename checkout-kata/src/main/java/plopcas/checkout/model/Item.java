package plopcas.checkout.model;

import java.util.Optional;

/**
 * Model for an item (SKU). It includes:
 * <ul>
 * <li>id: typically A, B, C, D</li>
 * <li>price: value in cents</li>
 * <li>discount: optional</li>
 * </ul>
 */
public class Item {

  private String id;
  private Integer price;
  private Optional<Discount> discount;

  public Item(String id, Integer price) {
    this.id = id;
    this.price = price;
    this.discount = Optional.empty();
  }

  public Item(String id, Integer price, Discount discount) {
    this.id = id;
    this.price = price;
    if (discount != null) {
      this.discount = Optional.of(discount);
    } else {
      this.discount = Optional.empty();
    }
  }

  public String getId() {
    return id;
  }

  public Integer getPrice() {
    return price;
  }

  public Optional<Discount> getDiscount() {
    return discount;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((discount == null) ? 0 : discount.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Item other = (Item) obj;
    if (discount == null) {
      if (other.discount != null)
        return false;
    } else if (!discount.equals(other.discount))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (price == null) {
      if (other.price != null)
        return false;
    } else if (!price.equals(other.price))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", price=" + price + ", discount=" + discount + "]";
  }

}
