package com.example.employeemanagementlab6.Controller;

import com.example.employeemanagementlab6.Model.Employee;
import com.example.employeemanagementlab6.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor

public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/get")
    public ResponseEntity getEmployee() {

        return ResponseEntity.status(200).body(employeeService.getEmployees());
    }


    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body("employee added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable String id, @Valid @RequestBody Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated = employeeService.updateEmployee(id, employee);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("employee updated");

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id) {


        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }



    @GetMapping("/getEmployeesByAgeRange/{age1}/{age2}")
public ResponseEntity getEmployeesByAgeRange(@PathVariable int age1,@PathVariable int age2){
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeesByAgeRange(age1,age2));

}

    @GetMapping("/search/{position}")
    public ResponseEntity searchEmployee(@Valid @PathVariable String position){

        ArrayList a = employeeService.searchEmployee(position);

        if(a == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("either supervisor or coordinator");
        }

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.searchEmployee(position));
    }




@GetMapping("/NoAnnualLeave")
    public ResponseEntity getEmployeesNoAnnualLeave(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeesNoAnnualLeave());

    }

@PutMapping("/applyAnnualLeave/{id}")
public ResponseEntity applyAnnualLeave(@PathVariable String id){
boolean isapplyAnnualLeave = employeeService.applyAnnualLeave(id);

if(isapplyAnnualLeave){
    return ResponseEntity.status(HttpStatus.OK).body("apply Annual Leave");
}
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");

}


    @PutMapping("/promoteEmployee/{supervisorId}/{employeeId}")
    public ResponseEntity promoteEmployee(@PathVariable String supervisorId, @PathVariable String employeeId) {
        boolean isPromoteEmployee = employeeService.promoteEmployee(supervisorId,employeeId);
        if(isPromoteEmployee){

            employeeService.promoteEmployee(supervisorId , employeeId);
            return ResponseEntity.status(HttpStatus.OK).body("promote Employee");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");

    }



}
