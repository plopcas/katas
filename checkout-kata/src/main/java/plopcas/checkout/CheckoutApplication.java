package plopcas.checkout;

import java.io.File;
import java.util.Scanner;
import plopcas.checkout.controller.CheckoutController;
import plopcas.checkout.exception.PricingRulesNotFoundException;
import plopcas.checkout.model.PricingRules;
import plopcas.checkout.service.CheckoutService;
import plopcas.checkout.service.ItemService;
import plopcas.checkout.service.PricingService;
import plopcas.checkout.service.ScannerService;

/**
 * Main class for the checkout application.
 */
public class CheckoutApplication {

  public static void main(String[] args) throws PricingRulesNotFoundException {
    PricingService pricingService = initPricingService(args);
    PricingRules pricingRules = pricingService.getPricingRules();
    ItemService itemService = new ItemService(pricingRules);
    ScannerService scannerService = new ScannerService();
    CheckoutService checkoutService = new CheckoutService();

    Scanner sc = new Scanner(System.in);

    CheckoutController checkoutController =
        new CheckoutController(sc, itemService, scannerService, checkoutService);

    checkoutController.handleCheckoutTransaction();

    sc.close();
  }

  private static PricingService initPricingService(String[] args)
      throws PricingRulesNotFoundException {
    String pricingRulesFilePath = "src/main/resources/pricing_rules.csv";

    if (args.length > 0) {
      System.out.println(String.format("Pricing rules location: %s", args[0]));
      pricingRulesFilePath = args[0];
    }

    File pricingRulesFile = new File(pricingRulesFilePath);
    return new PricingService(pricingRulesFile);
  }

}
