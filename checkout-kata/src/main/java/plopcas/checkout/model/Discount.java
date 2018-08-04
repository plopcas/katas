package plopcas.checkout.model;

/**
 * Model for the discount that can be optionally applied to an item. Typically you get a discount
 * when you buy many units of a given item.
 */
public class Discount {

  // Number of items
  private Integer count;

  // Amount to be deducted from the total
  private Integer value;

  public Discount(Integer count, Integer value) {
    this.count = count;
    this.value = value;
  }

  public Integer getCount() {
    return count;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((count == null) ? 0 : count.hashCode());
    result = prime * result + ((value == null) ? 0 : value.hashCode());
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
    Discount other = (Discount) obj;
    if (count == null) {
      if (other.count != null)
        return false;
    } else if (!count.equals(other.count))
      return false;
    if (value == null) {
      if (other.value != null)
        return false;
    } else if (!value.equals(other.value))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Discount [count=" + count + ", value=" + value + "]";
  }

}
