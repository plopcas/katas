package plopcas.checkout.controller;

import static java.lang.String.format;
import java.util.Scanner;
import plopcas.checkout.exception.ItemNotFoundException;
import plopcas.checkout.model.Cart;
import plopcas.checkout.model.Item;
import plopcas.checkout.model.Result;
import plopcas.checkout.service.CheckoutService;
import plopcas.checkout.service.ItemService;
import plopcas.checkout.service.ScannerService;

/**
 * Handles the interaction with the UI to perform a checkout transaction.
 */
public class CheckoutController {

  private static final String END = "end";

  private Scanner sc;
  private ItemService itemService;
  private ScannerService scannerService;
  private CheckoutService checkoutService;

  public CheckoutController(Scanner sc, ItemService itemService, ScannerService scannerService,
      CheckoutService checkoutService) {
    this.sc = sc;
    this.itemService = itemService;
    this.scannerService = scannerService;
    this.checkoutService = checkoutService;
  }

  /**
   * Handles a checkout transaction.
   * 
   * @return result
   */
  public Result handleCheckoutTransaction() {
    Cart cart = new Cart();

    String itemId = first(cart);

    scannerService.scan(findItem(itemId), cart);

    itemId = next(cart);
    while (!END.equalsIgnoreCase(itemId.trim())) {
      scannerService.scan(findItem(itemId), cart);
      itemId = next(cart);
    }

    Result result = checkoutService.checkout(cart);
    end(result);

    return result;
  }

  private String first(Cart cart) {
    System.out.println("== Checkout application ==");
    System.out.println("Scan first item to begin: ");
    return sc.nextLine();
  }

  private String next(Cart cart) {
    System.out.println(cart);
    System.out.println("More items? (Type END to proceed to checkout)");
    return sc.nextLine();
  }

  private void end(Result result) {
    System.out.println("Total:\n " + result.getTotal());
    if (result.getDiscount() != 0) {
      System.out.println(" - " + result.getDiscount() + " (discounts)");
    }
    System.out.println("To pay: " + result.getToPay());
  }

  private Item findItem(String itemId) {
    Item item = null;
    try {
      item = itemService.find(itemId);
    } catch (ItemNotFoundException e) {
      System.err.println(format("Item %s not found", itemId));
    }
    return item;
  }
}

