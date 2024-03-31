package com.hridoykrisna.car_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = true)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Roles extends BaseModel{
    private String name;
}
