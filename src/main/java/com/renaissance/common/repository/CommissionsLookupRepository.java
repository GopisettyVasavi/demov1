package com.renaissance.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.commission.model.CommissionsLookupEntity;

@Repository
public interface CommissionsLookupRepository extends CrudRepository<CommissionsLookupEntity, Integer>{

}
