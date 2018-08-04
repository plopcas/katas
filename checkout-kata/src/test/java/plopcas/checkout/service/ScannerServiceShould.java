package plopcas.checkout.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import plopcas.checkout.exception.CartNotValidException;
import plopcas.checkout.model.Cart;
import plopcas.checkout.model.Item;

public class ScannerServiceShould {

  @InjectMocks
  private ScannerService scannerService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void scan() {
    Cart cart = new Cart();
    cart = scannerService.scan(itemA(), cart);

    assertThat(cart.getItems()).containsExactly(itemA());
  }

  @Test
  public void scanMultipleDifferentItems() {
    Cart cart = new Cart();
    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemB(), cart);

    assertThat(cart.getItems()).containsExactly(itemA(), itemB());
  }

  @Test
  public void scanDuplicateItems() {
    Cart cart = new Cart();
    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(itemA(), cart);

    assertThat(cart.getItems()).containsExactly(itemA(), itemA());
  }

  @Test
  public void scanFilterNulls() {
    Cart cart = new Cart();
    cart = scannerService.scan(itemA(), cart);
    cart = scannerService.scan(null, cart);

    assertThat(cart.getItems()).containsExactly(itemA());
  }

  @Test(expected = CartNotValidException.class)
  public void failWhenCartIsNull() {
    scannerService.scan(itemA(), null);
  }

  private Item itemA() {
    return new Item("A", 50);
  }

  private Item itemB() {
    return new Item("B", 30);
  }

}
