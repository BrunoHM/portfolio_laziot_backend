package com.senai.laziot.codeDevice;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "codeDevice")
public class CodeDeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "textCode")
    private String textCode;

    @Column(name = "device")
    private String device;

    @Column(name = "typeDevice")
    private String typeDevice;

    @Column(name = "linkedTo")
    private String linkedTo;

    @Column(name = "active")
    private boolean active;
}
