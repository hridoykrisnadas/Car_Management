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
public class DutyPayment extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "fk_driver_id")
    private Employee driver;
    private int driver_id;
    private float total_duty_time;
    private float total_bill;
    private float total_payment;
    private float total_due_amount;
    private String last_payment_date;

}
