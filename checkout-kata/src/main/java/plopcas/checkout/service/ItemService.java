package plopcas.checkout.service;

import plopcas.checkout.exception.ItemNotFoundException;
import plopcas.checkout.exception.PricingRulesNotValidException;
import plopcas.checkout.model.Item;
import plopcas.checkout.model.PricingRules;

/**
 * Service that contains the business logic to fetch prices for given items.
 */
public class ItemService {

  private PricingRules pricingRules;

  public ItemService(PricingRules pricingRules) {
    if (pricingRules == null) {
      throw new PricingRulesNotValidException("Pricing rules are null");
    }
    this.pricingRules = pricingRules;
  }

  /**
   * Fetches an item from the pricing rules by ID.
   * 
   * @param itemId id of the item e.g. A
   * @return item, see {@link plopcas.checkout.model.Item}
   * @throws ItemNotFoundException if the item is not found
   */
  public Item find(String itemId) throws ItemNotFoundException {
    if (!pricingRules.containsKey(itemId)) {
      throw new ItemNotFoundException(String.format("Item %s not found", itemId));
    }
    return pricingRules.get(itemId);
  }

}
