package com.senai.laziot.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    <S extends UserEntity> S save(S entity);
    UserEntity getUserEntityById(Long id);
    UserEntity getUserEntityByHashPassword (String userAcessToken);
    UserEntity getUserEntityByHashUniqueCode (String hashUniqueTokenIOT);
    @Query(value = "select hashUniqueCode from user where email = :email", nativeQuery = true)
    String getUserUniqueCodeByEmail(@Param("email") String email);

}
