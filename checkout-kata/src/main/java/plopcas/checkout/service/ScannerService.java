package plopcas.checkout.service;

import plopcas.checkout.exception.CartNotValidException;
import plopcas.checkout.model.Cart;
import plopcas.checkout.model.Item;

public class ScannerService {

  /**
   * Scans an item and adds it to the cart.
   * 
   * @param item being scanned, see {@link plopcas.checkout.model.Item}
   * @param cart that contains the items, see {@link plopcas.checkout.model.Cart}
   * @return cart updated with the new item
   */
  public Cart scan(Item item, Cart cart) {
    if (cart == null) {
      throw new CartNotValidException("Cart is null");
    }
    
    if (item != null) {
      cart.getItems().add(item);
    }
    
    return cart;
  }

}
