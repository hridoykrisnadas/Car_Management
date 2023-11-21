package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.DutyPayment;
import java.util.*;

public interface DutyPaymentService {
    void save(DutyPayment dutyPayment);

    List<DutyPayment> getAll();
}
