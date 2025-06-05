package com.kevinpernia.inventory_system.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterProductRequest {
  private String name;
  private Float unitPrice;
}
