package com.ed.onenet.repository;

import com.ed.onenet.model.IdsResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdsResourceRepository extends JpaRepository<IdsResource, Long> {
}
