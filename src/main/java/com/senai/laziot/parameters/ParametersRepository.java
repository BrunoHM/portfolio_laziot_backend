package com.senai.laziot.parameters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametersRepository extends JpaRepository<ParametersEntity, Long> {

    ParametersEntity getParametersEntityByDescriptionContains(String description);
    ParametersEntity getParametersEntityByIdAndDescription(Long id, String description);
    List<ParametersEntity> getParametersEntityByUserId(Long userId);
    List<ParametersEntity> getParametersEntityByDescriptionContainsAndUserHashUniqueCode(String description, String hashUniqueCode);
    List<ParametersEntity> getParametersEntitiesByUserHashUniqueCode(String hashUniqueCode);

}
