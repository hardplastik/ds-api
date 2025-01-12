package io.hardplastik.ds.controller.command;

import java.time.LocalDate;

import io.hardplastik.ds.model.CustomerData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDataCommand {
  
  private Float weight;

  private Float height;

  private LocalDate dayOfBirth;

  private String phoneNumber;

  public CustomerData toEntity() {
    
    CustomerData customer = new CustomerData();
    customer.setWeight(weight);
    customer.setHeight(height);
    customer.setDayOfBirth(dayOfBirth);
    customer.setPhoneNumber(phoneNumber);
    return customer;
  }

  public CustomerData merge(CustomerData customer) {
    customer.setWeight(weight);
    customer.setHeight(height);
    customer.setDayOfBirth(dayOfBirth);
    customer.setPhoneNumber(phoneNumber);
    return customer;
  }

}
