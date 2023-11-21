package com.hridoykrisna.car_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@EqualsAndHashCode(callSuper = true)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee extends BaseModel{

    private String name;
    private String imagePath;
    private String email;
    private String password;
    private String designation;
    private String mobile_no;
    private String salary;
    private String home_district;
    private String nid;
    private String passport_no;
    private String driving_license_no;
    private String validity_of_driving_license;;
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
//    @OneToMany(mappedBy = "employee")
//    private List<CarSchedule> carSchedules;

    public Employee(String selectCar) {
        this.name = selectCar;
        this.setId(0);
    }

    public Employee(int id){
        this.setId(id);
    }

    public void setPassword(String password) {
        String generatedPassword = null;
        try
        {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.password = generatedPassword;
    }
}
