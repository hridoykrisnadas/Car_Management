package com.hridoykrisna.car_management.controller.Rest;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.service.EmployeeService;
import com.hridoykrisna.car_management.service.util.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee/")
public class EmployeeRestController {
    private final EmployeeService employeeService;
    private final FileService fileService;

    private final String path = CommonUtils.ImagePath;

    @GetMapping("{id}")
    public Employee getEmpDetails(@Valid @PathVariable("id") int id, Model model) {
        //        System.out.println(employee);
//        model.addAttribute("employee", employee);
        return employeeService.getEmployeeById(id);
    }

}
