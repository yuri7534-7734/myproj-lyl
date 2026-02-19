package com.study.Ex04Autowired;

import org.springframework.stereotype.Component;

@Component("samsungCard")
public class SamsungCard implements ICard{
  @Override
  public void buy(String itemName) {
    System.out.println("SamsungCard.buy");
    System.out.println("itemName = " + itemName);
  }
}