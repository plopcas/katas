package plopcas.checkout.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import plopcas.checkout.exception.ItemNotFoundException;
import plopcas.checkout.model.Cart;
import plopcas.checkout.model.Result;
import plopcas.checkout.service.CheckoutService;
import plopcas.checkout.service.ItemService;
import plopcas.checkout.service.ScannerService;

public class CheckoutControllerShould {

  @Mock
  private ItemService itemService;

  @Mock
  private ScannerService scannerService;

  @Mock
  private CheckoutService checkoutService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void handleCheckoutTransaction() throws FileNotFoundException, ItemNotFoundException {
  
    File userInputFile = new File("src/test/resources/user_input_ok.txt");
    Scanner sc = new Scanner(userInputFile);

    CheckoutController checkoutController = new CheckoutController(sc, itemService, scannerService, checkoutService);
        
    Cart cart = new Cart();
    Result expectedResult = new Result(50, 0, 50, cart);
    
    when(checkoutService.checkout(any())).thenReturn(expectedResult);

    Result result = checkoutController.handleCheckoutTransaction();
    
    assertThat(result).isEqualTo(expectedResult);
    
    verify(scannerService, times(1)).scan(any(), any());
    verify(itemService, times(1)).find(any());
    verify(checkoutService, times(1)).checkout(any());
    
  }
  
  @Test
  public void handleCheckoutTransaction_multipleItems()
      throws FileNotFoundException, ItemNotFoundException {
 
    File userInputFile = new File("src/test/resources/user_input_ok_multiple.txt");
    Scanner sc = new Scanner(userInputFile);

    CheckoutController checkoutController = new CheckoutController(sc, itemService, scannerService, checkoutService);
        
    Cart cart = new Cart();
    Result expectedResult = new Result(150, 0, 150, cart);
    
    when(checkoutService.checkout(any())).thenReturn(expectedResult);

    Result result = checkoutController.handleCheckoutTransaction();
    
    assertThat(result).isEqualTo(expectedResult);
    
    verify(scannerService, times(4)).scan(any(), any());
    verify(itemService, times(4)).find(any());
    verify(checkoutService, times(1)).checkout(any());
    
  }
  
  @Test
  public void handleCheckoutTransaction_wrongItem()
      throws FileNotFoundException, ItemNotFoundException {
  
    File userInputFile = new File("src/test/resources/user_input_wrong_item.txt");
    Scanner sc = new Scanner(userInputFile);

    CheckoutController checkoutController = new CheckoutController(sc, itemService, scannerService, checkoutService);
        
    Cart cart = new Cart();
    Result expectedResult = new Result(50, 0, 50, cart);
    
    when(itemService.find(eq("Z"))).thenThrow(ItemNotFoundException.class);
    when(checkoutService.checkout(any())).thenReturn(expectedResult);

    Result result = checkoutController.handleCheckoutTransaction();
    
    assertThat(result).isEqualTo(expectedResult);
    
    verify(scannerService, times(2)).scan(any(), any());
    verify(itemService, times(2)).find(any());
    verify(checkoutService, times(1)).checkout(any());
    
  }

}
