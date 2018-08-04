package plopcas.checkout.service;

import static java.lang.Integer.parseInt;
import static plopcas.checkout.utils.StringUtils.isNotBlank;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import plopcas.checkout.exception.CsvFormatNotValidException;
import plopcas.checkout.exception.PricingRulesNotFoundException;
import plopcas.checkout.exception.PricingRulesNotValidException;
import plopcas.checkout.model.Discount;
import plopcas.checkout.model.Item;
import plopcas.checkout.model.PricingRules;
import plopcas.checkout.utils.StringUtils;

/**
 * Service that contains the business logic to find and fetch the pricing rules. As for now, it
 * assumes the pricing rules are in a CSV file with the following format:
 * 
 * <pre>
 * ID,PRICE,UNITS_FOR_DISCOUNT,DISCOUNT
 * A,50,3,20
 * B,30,2,15
 * C,20,,
 * D,15,,
 * </pre>
 */
public class PricingService {

  private static final String CSV_SPLIT_BY = ",";

  private File pricingRulesFile;

  public PricingService(File pricingRulesFile) throws PricingRulesNotFoundException {
    if (!pricingRulesFile.exists()) {
      throw new PricingRulesNotFoundException();
    }
    this.pricingRulesFile = pricingRulesFile;
  }

  /**
   * Fetches the pricing rules.
   * @return pricing rules, see {@link plopcas.checkout.model.PricingRules}
   */
  public PricingRules getPricingRules() {

    PricingRules pricingRules = new PricingRules();

    String line = StringUtils.EMPTY;

    try (BufferedReader br = new BufferedReader(new FileReader(pricingRulesFile))) {

      // Check header
      validateCsvHeader(br.readLine());

      while ((line = br.readLine()) != null) {
        String[] itemTokens = line.split(CSV_SPLIT_BY);

        Discount discount = getDiscount(itemTokens);
        Item item = new Item(itemTokens[0], parseInt(itemTokens[1]), discount);

        pricingRules.put(itemTokens[0], item);
      }

    } catch (Exception e) {
      throw new PricingRulesNotValidException(
          String.format("Error loading pricing rules: %s", e.getMessage()));
    }

    return pricingRules;
  }

  private Discount getDiscount(String[] itemTokens) {
    Discount discount = null;

    if (itemTokens.length == 4 && isNotBlank(itemTokens[2]) && isNotBlank(itemTokens[3])) {
      discount = new Discount(parseInt(itemTokens[2]), parseInt(itemTokens[3]));
    }

    return discount;
  }

  private void validateCsvHeader(String header) {
    String[] headerTokens = header.split(CSV_SPLIT_BY);
    if (headerTokens.length != 4
        || !"ID".equals(headerTokens[0])
        || !"PRICE".equals(headerTokens[1])
        || !"UNITS_FOR_DISCOUNT".equals(headerTokens[2])
        || !"DISCOUNT".equals(headerTokens[3])) {
      throw new CsvFormatNotValidException("CSV does not match ID,PRICE,UNITS_FOR_DISCOUNT,DISCOUNT");
    }
  }

}
