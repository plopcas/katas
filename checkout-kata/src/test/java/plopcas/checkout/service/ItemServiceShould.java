package plopcas.checkout.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import plopcas.checkout.exception.ItemNotFoundException;
import plopcas.checkout.exception.PricingRulesNotValidException;
import plopcas.checkout.model.Item;
import plopcas.checkout.model.PricingRules;

public class ItemServiceShould {
  @InjectMocks
  private ItemService itemService;

  @Mock
  private PricingRules pricingRules;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findItemById() throws ItemNotFoundException {
    when(pricingRules.containsKey(eq("A"))).thenReturn(true);
    when(pricingRules.get(eq("A"))).thenReturn(itemA());

    Item item = itemService.find("A");

    assertThat(item).isEqualTo(itemA());
  }

  @Test(expected = ItemNotFoundException.class)
  public void failWhenItemNotFound() throws ItemNotFoundException {
    when(pricingRules.containsKey(eq("Z"))).thenReturn(false);

    itemService.find("Z");
  }

  @Test(expected = PricingRulesNotValidException.class)
  public void failWhenPricingRulesNotValid() {
    new ItemService(null);
  }

  private Item itemA() {
    return new Item("A", 50);
  }
}
