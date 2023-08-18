package com.senai.laziot.action;

import com.senai.laziot.action.DTO.ActionFiltersDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ActionRepository extends JpaRepository<ActionEntity, Long> {


    @Override
    <S extends ActionEntity> S save(S s);

    @Override
    Optional<ActionEntity> findById(Long Long);

    @Query(value = "SELECT * " +
            "FROM action " +
            "WHERE " +
            "description like %:description% "+
            "AND doubleAction in (:doubleAction) "+
            "AND active in (:active) "+
            "AND fkDeviceId in ( SELECT device_id " +
            "FROM userDevices " +
            "WHERE user_id = (SELECT id " +
            "FROM user " +
            "WHERE hashUniqueCode = :userToken ))",nativeQuery = true)
    List<ActionEntity> findAllActionUser(@Param("userToken") String userToken, @Param("description") String description,
                                         @Param("doubleAction") List<Integer> doubleAction, @Param("active") List<Integer> active, Pageable pageable);


    @Transactional
    @Modifying
    @Query(value = "UPDATE action " +
            "SET active = :newDeviceState " +
            "WHERE id = :idAction " +
            "AND fkDeviceId IN (select device_id " +
            "FROM userDevices " +
            "WHERE user_id = (SELECT id " +
            "FROM user " +
            "WHERE hashUniqueCode = :userToken ))", nativeQuery = true)
    int updateActionState(@Param("userToken") String userToken, @Param("idAction") int idAction, @Param("newDeviceState") boolean newDeviceState);
}
