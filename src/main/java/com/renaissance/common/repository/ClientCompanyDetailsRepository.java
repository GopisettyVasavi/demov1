package com.renaissance.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.common.model.ClientCompanyDetailsEntity;

@Repository
public interface ClientCompanyDetailsRepository extends CrudRepository<ClientCompanyDetailsEntity, Integer> {

}
