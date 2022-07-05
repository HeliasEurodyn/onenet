package com.ed.onenet.repository;

import com.ed.onenet.model.DataOffering;
import com.ed.onenet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataOfferingRepository extends JpaRepository<DataOffering, String> {
}
