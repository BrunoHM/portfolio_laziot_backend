package com.senai.laziot.event;

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
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Override
    <S extends EventEntity> S save(S s);

    @Query(value = "SELECT * " +
            "FROM event " +
            "WHERE fkIdUser = (SELECT id FROM user WHERE hashUniqueCode = :tokenUser) " +
            "AND manual in (:manual) " +
            "AND executed in (:executed) " +
            "AND (date between :dateInit AND :dateEnd) " +
            "AND (time between :timeInit AND :timeEnd) order by id asc", nativeQuery = true)
    List<EventEntity> findAllEventsFiltered(@Param("tokenUser") String tokenUser,
                                            @Param("dateInit") String dateInit,
                                            @Param("dateEnd") String dateEnd,
                                            @Param("timeInit") String timeInit,
                                            @Param("timeEnd") String timeEnd,
                                            @Param("manual") List<Integer> manual,
                                            @Param("executed") List<Integer> executed);


}
