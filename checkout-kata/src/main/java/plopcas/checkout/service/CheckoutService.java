package plopcas.checkout.service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import plopcas.checkout.exception.CartNotValidException;
import plopcas.checkout.model.Cart;
import plopcas.checkout.model.Discount;
import plopcas.checkout.model.Item;
import plopcas.checkout.model.Result;

/**
 * Service that contains the business logic to calculate the checkout for a given cart. It takes
 * into account possible discounts for the items in the cart.
 */
public class CheckoutService {

  /**
   * Performs a checkout for a given cart and returns the result.
   * 
   * @param cart containing the items, see {@link plopcas.checkout.model.Cart}
   * @return result of the checkout, see {@link plopcas.checkout.model.Result}
   */
  public Result checkout(Cart cart) {
    if (cart == null) {
      throw new CartNotValidException("Cart is null");
    }

    Integer total = calculateTotal(cart);
    Integer discount = calculateDiscount(cart);
    Integer toPay = total - discount;

    return new Result(total, discount, toPay, cart);
  }

  private Integer calculateTotal(Cart cart) {
    return cart.getItems().stream()
        .filter(Objects::nonNull)
        .map(Item::getPrice)
        .collect(Collectors.summingInt(Integer::intValue));
  }

  private Integer calculateDiscount(Cart cart) {
    Map<Item, Long> countMap = cart.getItems().stream()
        .filter(Objects::nonNull)
        .collect(Collectors.groupingBy(item -> item, Collectors.counting()));

    return countMap.entrySet().stream().map(entry -> {

      Item item = entry.getKey();
      Long count = entry.getValue();
      Optional<Discount> discount = item.getDiscount();

      if (!discount.isPresent()) {
        return 0;
      } else {
        Long times = count / discount.get().getCount();
        return discount.get().getValue() * times.intValue();
      }

    }).collect(Collectors.summingInt(Integer::intValue));
  }

}
