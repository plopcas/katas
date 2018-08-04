package plopcas.checkout.feature;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import plopcas.checkout.model.Cart;
import plopcas.checkout.model.Discount;
import plopcas.checkout.model.Item;
import plopcas.checkout.model.Result;
import plopcas.checkout.service.CheckoutService;
import plopcas.checkout.service.ScannerService;

/**
 * Feature: Complex checkout <br>
 * As a user <br>
 * I should be able to scan items one by one <br>
 * and get a total price when I'm done at the end <br>
 * and get applied any relevant discounts <br>
 * so that I know how much I have to pay
 */
public class CheckoutComplexFeature {
  private ScannerService scannerService;
  private CheckoutService checkoutService;

  @Before
  public void setUp() {
    scannerService = new ScannerService();
    checkoutService = new CheckoutService();
  }

  @Test
  public void checkoutItemsWithSpecialPrice() {
    Cart cart = new Cart();

    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemA(), cart);
    Result result = checkoutService.checkout(cart);

    assertThat(result.getToPay()).isEqualTo(130);
  }

  @Test
  public void checkoutItemsWithSpecialPriceMoreThanOnce() {
    Cart cart = new Cart();

    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemA(), cart);
    Result result = checkoutService.checkout(cart);

    assertThat(result.getToPay()).isEqualTo(260);
  }

  private Item itemA() {
    return new Item("A", 50, new Discount(3, 20));
  }
}
