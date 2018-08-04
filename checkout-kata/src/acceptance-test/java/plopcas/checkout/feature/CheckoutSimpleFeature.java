package plopcas.checkout.feature;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import plopcas.checkout.model.Cart;
import plopcas.checkout.model.Item;
import plopcas.checkout.model.Result;
import plopcas.checkout.service.CheckoutService;
import plopcas.checkout.service.ScannerService;

/**
 * Feature: Simple checkout <br>
 * As a user <br>
 * I should be able to scan items one by one <br>
 * and get a total price when I'm done at the end <br>
 * so that I know how much I have to pay
 */
public class CheckoutSimpleFeature {

  private ScannerService scannerService;
  private CheckoutService checkoutService;

  @Before
  public void setUp() {
    scannerService = new ScannerService();
    checkoutService = new CheckoutService();
  }

  @Test
  public void scanSingleItem() {
    Cart cart = new Cart();

    cart = scannerService.scan(itemA(), cart);
    Result result = checkoutService.checkout(cart);

    assertThat(result.getToPay()).isEqualTo(50);
  }

  @Test
  public void scanMultipleDifferentItems() {
    Cart cart = new Cart();

    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemB(), cart);
    Result result = checkoutService.checkout(cart);

    assertThat(result.getToPay()).isEqualTo(80);
  }

  @Test
  public void scanMultipleSameItems() {
    Cart cart = new Cart();

    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemC(), cart);
    cart = scannerService.scan(itemC(), cart);
    Result result = checkoutService.checkout(cart);

    assertThat(result.getToPay()).isEqualTo(90);
  }

  private Item itemA() {
    return new Item("A", 50);
  }

  private Item itemB() {
    return new Item("B", 30);
  }

  private Item itemC() {
    return new Item("C", 20);
  }

}
