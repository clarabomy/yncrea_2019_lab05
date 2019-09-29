package yncrea.lab05.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yncrea.lab05.core.entity.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Long> {

}
