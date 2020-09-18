package com.renaissance.invoice.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.invoice.model.InvoiceDetailsEntity;

@Repository
public interface InvoiceDetailsRepository extends CrudRepository<InvoiceDetailsEntity, BigInteger>, InvoiceDetailsCustomRepository{

}
