package com.hridoykrisna.car_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarExpenses extends BaseModel {
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
