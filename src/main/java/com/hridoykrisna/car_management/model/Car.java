package com.hridoykrisna.car_management.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Car extends BaseModel{
    private String car_name;
    private String car_no;
    private String engine_no;
    private String chassis_no;
    private String owner_name;
    private String car_color;

    public Car(String selectDriver) {
        this.car_name = selectDriver;
        this.setCar_no("...");
    }

    public Car(int id) {
        this.setId(id);
    }
}
