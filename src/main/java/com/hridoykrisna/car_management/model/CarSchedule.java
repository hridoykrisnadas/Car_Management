package com.hridoykrisna.car_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.text.DecimalFormat;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarSchedule extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;
    private int employee_id;
    @ManyToOne
    @JoinColumn(name = "fk_car_id")
    private Car car;
    private int car_id;
    @ManyToOne
    @JoinColumn(name = "fk_driver_id")
    private Employee driver;
    private int driver_id;
    private String pickup_point;
    private String drop_point;
    private String schedule_date;
    private String schedule_time;
    private String start_time;
    private String stop_time;
    private String total_duty_time;
    private double total_bill;
    @Comment("0==Pending, 1== Approve, 2==Cancel")
    private int status;

    public double getTotal_bill() {
        DecimalFormat dec = new DecimalFormat("#0.00");
        return Double.parseDouble(dec.format(total_bill));
    }

    public void setTotal_bill(double total_bill) {
        DecimalFormat dec = new DecimalFormat("#0.00");
        this.total_bill = Double.parseDouble(dec.format(total_bill));
    }
}
