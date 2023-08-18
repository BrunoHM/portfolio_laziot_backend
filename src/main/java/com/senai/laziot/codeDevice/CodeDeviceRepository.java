package com.senai.laziot.codeDevice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDeviceRepository extends JpaRepository<CodeDeviceEntity, Long> {

    CodeDeviceEntity getCodeDeviceEntityByTypeDeviceEquals(String typeDevice);



}
