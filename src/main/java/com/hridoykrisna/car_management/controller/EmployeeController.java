package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.EmployeeService;
import com.hridoykrisna.car_management.service.util.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final FileService fileService;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    private Employee user;

    private final String path = CommonUtils.ImagePath;
    @GetMapping({"/employee", "/employee/"})
    public String getEmployee(Model map){
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            user = CommonUtils.getEmployeeByEmail(SecurityContextHolder.getContext().getAuthentication().getName(), employeeRepo);
            map.addAttribute("currentUserName", user.getName());

            List<Employee> employeeList = null;
            employeeList = employeeService.employeeList();
            map.addAttribute("employees", employeeList);
            map.addAttribute("currentUserName", user.getName());

            if (Objects.equals(user.getUser_type(), "ADMIN")){
                map.addAttribute("user_type", "ADMIN");
            }
            return "employee.html";
        }else {
            return "redirect:/login";
        }
    }
    @PostMapping("/employee-registration-form")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, @RequestParam MultipartFile image, RedirectAttributes redirectAttributes) throws IOException {


        // Save Image & Set Image Path
        String imagePath = fileService.uploadImage(path, image, employee.getName());
        employee.setImagePath(imagePath);
        employee.setCreatedBy(user.getId());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));


        //Save to Database
        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("success", "Employee Registration Successfully Done.");
        return "redirect:/employee";
    }

}
