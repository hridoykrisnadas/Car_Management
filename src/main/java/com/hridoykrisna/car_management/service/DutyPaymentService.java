package com.hridoykrisna.car_management.service;

import com.hridoykrisna.car_management.model.DutyPayment;

import java.util.List;

public interface DutyPaymentService {
    void save(DutyPayment dutyPayment, int id);

    List<DutyPayment> getAll();

    List<DutyPayment> getDriverWiseDutyPaymentList(int id);
}
