package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.EmployeeService;
import com.hridoykrisna.car_management.service.util.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final FileService fileService;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    private final String path = CommonUtils.ImagePath;
    private Employee user;

    @GetMapping({"/employee", "/employee/"})
    public String getEmployee(Model map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            user = CommonUtils.getEmployeeByEmail(authentication.getName(), employeeRepo);
            map.addAttribute("currentUserName", user.getName());
            map.addAttribute("currentUserLogo", user.getImagePath());

            List<Employee> employeeList = null;
            employeeList = employeeService.employeeList();
            employeeList.removeIf(employee -> Objects.equals(employee.getDesignation(), "Super Admin"));
            map.addAttribute("employees", employeeList);
            map.addAttribute("currentUserName", user.getName());
            map.addAttribute("user_type", user.getUser_type());

            return "employee.html";
        } else {
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
