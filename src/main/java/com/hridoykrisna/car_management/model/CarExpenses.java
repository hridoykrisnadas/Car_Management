package com.hridoykrisna.car_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarExpenses extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "fk_car_id")
    private Car car;
    private int car_id;
    @ManyToOne
    @JoinColumn(name = "fk_driver_id")
    private Employee driver;
    private int driver_id;
    private String date;
    private String purpose;
    private float amount;
    private String invoice_no;
    private String memo_image_path;
}
