package plopcas.checkout.feature;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;
import plopcas.checkout.controller.CheckoutController;
import plopcas.checkout.exception.PricingRulesNotFoundException;
import plopcas.checkout.model.PricingRules;
import plopcas.checkout.model.Result;
import plopcas.checkout.service.CheckoutService;
import plopcas.checkout.service.ItemService;
import plopcas.checkout.service.PricingService;
import plopcas.checkout.service.ScannerService;

public class UiFeature {

  @Test
  public void handleCompleteTransaction()
      throws FileNotFoundException, PricingRulesNotFoundException {
    File pricingRulesFile = new File("src/acceptance-test/resources/pricing_rules_1.csv");
    PricingService pricingService = new PricingService(pricingRulesFile);
    PricingRules pricingRules = pricingService.getPricingRules();
    ItemService itemService = new ItemService(pricingRules);
    ScannerService scannerService = new ScannerService();
    CheckoutService checkoutService = new CheckoutService();

    File userInputFile = new File("src/acceptance-test/resources/user_input.txt");
    Scanner sc = new Scanner(userInputFile);
    
    CheckoutController checkoutController =
        new CheckoutController(sc, itemService, scannerService, checkoutService);

    Result result = checkoutController.handleCheckoutTransaction();
    
    assertThat(result.getCart().getItems()).hasSize(8);
    assertThat(result.getTotal()).isEqualTo(295);
    assertThat(result.getDiscount()).isEqualTo(35);
    assertThat(result.getToPay()).isEqualTo(260);
  }

}
