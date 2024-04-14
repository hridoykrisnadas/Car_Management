package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.model.Car;
import com.hridoykrisna.car_management.model.CarExpenses;
import com.hridoykrisna.car_management.repository.EmployeeRepo;
import com.hridoykrisna.car_management.service.CarExpenseService;
import com.hridoykrisna.car_management.service.CarService;
import com.hridoykrisna.car_management.model.Employee;
import com.hridoykrisna.car_management.service.EmployeeService;
import com.hridoykrisna.car_management.service.util.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping("")
public class CarExpenseController {
    private final EmployeeService employeeService;
    private final EmployeeRepo employeeRepo;
    private final CarService carService;
    private final FileService fileService;
    private final CarExpenseService carExpenseService;

    private Employee user;
    private final String path = CommonUtils.ImagePath;

    @GetMapping({"/car-expense", "/car-expense/"})
    public String getCarExpense(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            user = CommonUtils.getEmployeeByEmail(SecurityContextHolder.getContext().getAuthentication().getName(), employeeRepo);
            model.addAttribute("currentUserName", user.getName());
            List<Employee> driverList = employeeService.driverList();
            driverList.add(0, new Employee("Select Driver"));
            model.addAttribute("drivers", driverList);
            List<CarExpenses> carExpensesList = carExpenseService.getAllExpenseReport();
            model.addAttribute("expenseList", carExpensesList);
            List<Car> carList = carService.getAllCar();
            carList.add(0, new Car("Select Car"));
            model.addAttribute("cars", carList);
            return "car_expense.html";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/car-expense-form")
    public String saveCarExpense(@Valid  @ModelAttribute("carExpense") CarExpenses carExpenses, @RequestParam MultipartFile image, RedirectAttributes redirectAttributes) throws IOException {

        // Save Image & Set Image Path
        String imagePath = fileService.uploadImage(path, image, carExpenses.getInvoice_no());
        carExpenses.setMemo_image_path(imagePath);

        //Save to Database
        carExpenseService.save(carExpenses, user.getId());
        redirectAttributes.addFlashAttribute("success", "Car Expense Save Successfully.");
        return "redirect:/car-expense";
    }
}
