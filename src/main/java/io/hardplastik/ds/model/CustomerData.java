package io.hardplastik.ds.model;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "account")
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "customer_data")
public class CustomerData {
  
  @Id
  @UuidGenerator
  @JsonIgnore
  @Column(name = "customer_data_id")
  private UUID id;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", referencedColumnName = "account_id")
  private Account account;

  @Column(name = "weight")
  private Float weight;

  @Column(name = "height")
  private Float height;

  @Column(name = "day_of_birth")
  private LocalDate dayOfBirth;

  @Column(name = "phone_number")
  private String phoneNumber;

}
