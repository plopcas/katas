package plopcas.checkout.model;

/**
 * Model for the result of the checkout transaction. It includes:
 * <ul>
 * <li>total: amount for the items in the cart</li>
 * <li>discount: amount to be deducted from the total</li>
 * <li>toPay: total - discount</li>
 * <li>cart: the list of items</li>
 * </ul>
 */
public class Result {

  private Integer total;
  private Integer discount;
  private Integer toPay;
  private Cart cart;

  public Result(Integer total, Integer discount, Integer toPay, Cart cart) {
    this.total = total;
    this.discount = discount;
    this.toPay = toPay;
    this.cart = cart;
  }

  public Integer getTotal() {
    return total;
  }

  public Integer getDiscount() {
    return discount;
  }

  public Integer getToPay() {
    return toPay;
  }

  public Cart getCart() {
    return cart;
  }

}
