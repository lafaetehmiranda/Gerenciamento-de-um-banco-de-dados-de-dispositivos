package com.device.manager.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private LocalDateTime createdAt;

    public Device() {
        this.createdAt = LocalDateTime.now();
    }

    public Device(String name, String brand) {
        this.name = name;
        this.brand = brand;
        this.createdAt = LocalDateTime.now();
    }

}
