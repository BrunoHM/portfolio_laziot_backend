package com.senai.laziot.parameters;

import com.senai.laziot.user.UserEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parameters")
public class ParametersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private String value;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public ParametersEntity(String description, String value, UserEntity user){
        this.description = description;
        this.value = value;
        this.active = true;
        this.user = user;
    }
}
