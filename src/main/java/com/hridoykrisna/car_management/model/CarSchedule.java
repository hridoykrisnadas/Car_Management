package com.hridoykrisna.car_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarSchedule extends BaseModel{
    private int employee_id;
    private String employee_name;
    private int car_id;
    private String car_name;
    private int driver_id;
    private String driver_name;
    private String pickup_point;
    private String drop_point;
    private String schedule_date;
    private String schedule_time;
    private String start_time;
    private String stop_time;
    private String total_duty_time;
    private String total_bill;
    @Comment("0==Pending, 1== Approve, 2==Cancel")
    private int status;

}
