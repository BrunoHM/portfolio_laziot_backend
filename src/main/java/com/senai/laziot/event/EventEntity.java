package com.senai.laziot.event;

import com.senai.laziot.action.ActionEntity;
import com.senai.laziot.user.UserEntity;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Entity
@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "manual")
    private boolean manual;

    @Column(name = "executed")
    private boolean executed;

    @ManyToOne
    @JoinColumn(name = "fkIdUser", referencedColumnName = "id")
    private UserEntity fkIdUser;

    @ManyToOne
    @JoinColumn(name = "fkIdAction", referencedColumnName = "id")
    private ActionEntity fkIdAction;


    public EventEntity(Long idAction){
        LocalDate localDate = LocalDate.now();
        Date data = new Date();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //this.date = localDate.format(formatter);
        this.date = "2022-09-26";

        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.time = time.format(formatter);

        this.id = 0L;
        this.manual = false;
        this.executed = false;
        //this.fkIdUser = 1L;
        //this.fkIdAction = idAction;
    }
}
