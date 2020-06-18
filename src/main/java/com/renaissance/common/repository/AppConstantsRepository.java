package com.renaissance.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.common.model.AppConstantsEntity;

@Repository
public interface AppConstantsRepository extends CrudRepository<AppConstantsEntity, Integer>{

}
