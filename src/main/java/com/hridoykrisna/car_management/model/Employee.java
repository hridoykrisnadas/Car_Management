package com.hridoykrisna.car_management.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Employee extends BaseModel {
    private String name;
    private String imagePath;
    @Column(unique = true)
    private String email;
    private String password;
    private String designation;
    private String mobile_no;
    private String salary;
    private String home_district;
    //    @Column(unique=true)
    private String nid;
    private String passport_no;
    private String driving_license_no;
    private String validity_of_driving_license;
    ;
    private String nationality;
    private String gender;
    private String date_of_birth;
    private String joining_date;
    private String user_type;
    //    For Driver
    private float balance;

    private float total_bill;
    private float total_payment;
    private float total_due_amount;
    private String last_payment_date;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "id")

    )
    private Set<Roles> roles;
//    @OneToMany(mappedBy = "employee")
//    private List<CarSchedule> carSchedules;

    public Employee(String selectCar) {
        this.name = selectCar;
        this.setId(0);
    }

    public Employee(int id) {
        this.setId(id);
    }

    public void setPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
    }
}
