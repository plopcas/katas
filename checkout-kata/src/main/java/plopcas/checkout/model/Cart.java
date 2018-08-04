package plopcas.checkout.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for a shopping cart that holds all the items for the current checkout transaction.
 */
public class Cart {

  private List<Item> items;

  public Cart() {
    this.items = new ArrayList<>();
  }

  public void add(Item item) {
    this.items.add(item);
  }

  public List<Item> getItems() {
    return this.items;
  }

  @Override
  public String toString() {
    return "Cart [items=" + items + "]";
  }

}
