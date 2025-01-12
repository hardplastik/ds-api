package io.hardplastik.ds.data;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.hardplastik.ds.model.CustomerData;

public interface CustomerDataRepository extends JpaRepository<CustomerData, UUID> {
  
  Optional<CustomerData> findByAccountId(UUID accountId);

}
