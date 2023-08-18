package com.senai.laziot.device;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE device " +
            "SET active = :newDeviceState " +
            "WHERE id = :idDevice " +
            "AND id IN (select device_id " +
            "FROM userDevices " +
            "WHERE user_id = (SELECT id " +
            "FROM user " +
            "WHERE hashUniqueCode = :tokenUser ))", nativeQuery = true)
    int updateDeviceState(@Param("tokenUser") String tokenUser, @Param("idDevice") int idDevice, @Param("newDeviceState") boolean newDeviceState);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO equivalentDevice VALUES (:idEmitter, :idReceptor)", nativeQuery = true)
    int setLinkDevices(@Param("idEmitter") int idEmitter, @Param("idReceptor") int idReceptor);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM equivalentDevice WHERE idDispositivoMae = :idEmitter AND idDispositivoFilho = :idReceptor", nativeQuery = true)
    int setUnlinkDevices(@Param("idEmitter") int idEmitter, @Param("idReceptor") int idReceptor);

    DeviceEntity getDeviceEntityById(Long idDevice);

    @Query(value = "SELECT * FROM device " +
            "WHERE id in (select device_id " +
            "from userDevices " +
            "where description like %:description% " +
            "AND place like %:place% " +
            "AND type like %:type% " +
            "AND active in (:active) " +
            "AND user_id = (select id " +
            "from user where hashUniqueCode = :tokenUser)) order by id asc", nativeQuery = true)
    Page<DeviceEntity> findAllByDeviceFiltersDTO(@Param("tokenUser") String tokenUser,
                                                 @Param("description") String description,
                                                 @Param("place") String place,
                                                 @Param("type") String type,
                                                 @Param("active") List<Integer> active,
                                                 Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM device " +
            "WHERE type = 'emissor' " +
            "AND id in( SELECT device_id " +
            "FROM userDevices " +
            "WHERE user_id = " +
            "(SELECT id " +
            "FROM user " +
            "WHERE hashUniqueCode = :tokenUser ))", nativeQuery = true)
    Page<DeviceEntity> findAllMainDevices(@Param("tokenUser") String tokenUser, Pageable pageable);

    @Query(value = "SELECT * FROM device " +
            "WHERE type = 'receptor' " +
            "AND active = 1 " +
            "AND id in ( SELECT device_id " +
            "            FROM userDevices " +
            "            WHERE user_id = (select id " +
            "from user where hashUniqueCode = :tokenUser)) order by id asc", nativeQuery = true)
    List<DeviceEntity> findAllSimplifiedDevices(@Param("tokenUser") String tokenUser);

    @Query(value = "SELECT * " +
            "FROM device " +
            "WHERE " +
            "(id IN (SELECT idDispositivoFilho FROM equivalentDevice WHERE idDispositivoMae = :idDevice) " +
            "OR id IN (SELECT idDispositivoMae FROM equivalentDevice WHERE idDispositivoFilho = :idDevice)) " +
            "AND id IN (SELECT device_id " +
            "FROM userDevices " +
            "WHERE user_id = " +
            "(SELECT id " +
            "FROM user " +
            "WHERE hashUniqueCode = :tokenUser))", nativeQuery = true)
    List<DeviceEntity> findAllLinkedDevices(@Param("tokenUser") String tokenUser, @Param("idDevice") int idDevice);

    @Query(value = "SELECT * " +
            "FROM device " +
            "WHERE " +
            "active = 1 " +
            "AND type != :typeDevice " +
            "AND id NOT IN (SELECT idDispositivoMae FROM equivalentDevice WHERE idDispositivoFilho = :idDevice) " +
            "AND id NOT IN (SELECT idDispositivoFilho FROM equivalentDevice WHERE idDispositivoMae = :idDevice) " +
            "AND id IN (SELECT device_id " +
            "FROM userDevices " +
            "WHERE user_id = " +
            "(SELECT id " +
            "FROM user " +
            "WHERE hashUniqueCode = :tokenUser))", nativeQuery = true)
    List<DeviceEntity> findAllAvailableDevicesToLink(@Param("tokenUser") String tokenUser, @Param("idDevice") int idDevice, @Param("typeDevice") String typeDevice);

}
