package com.senai.laziot.userDevice;

import com.senai.laziot.device.DeviceEntity;
import com.senai.laziot.user.UserEntity;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Builder @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Table(name = "userDevices")
public class UserDeviceEntity implements Serializable {

    private static final long serialVersionUID = 4256830615486416347L;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity idUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private DeviceEntity idDevice;

}
