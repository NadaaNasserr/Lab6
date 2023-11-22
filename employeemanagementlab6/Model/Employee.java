package com.example.employeemanagementlab6.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Employee {

    @NotNull(message = "id cannot be null")
    @Size(min = 3 , message = "id length must be more than 2 characters.")
    private String id;

    @NotNull(message = "name cannot be null")
    @Size(min = 5 , message = "name length must be more than 5 characters.")


@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;

   @Email(message ="Must be a valid email format" )
    private String email;

    @Size(min = 10, max = 10 ,  message = "Must consists of exactly 10 digits.")
    @Pattern(regexp = "^05\\d{8}$" , message = "Must start with 05")
    private String phoneNumber;


@FutureOrPresent
    @NotNull(message = "age cannot be null")
    @Min(value = 26 , message = "age must be more than 25.")
    @Positive(message = " age must be a number.")

    private int age;



    @NotNull(message = "position cannot be null")
    @Pattern(regexp = "^(supervisor|coordinator)$" , message = "position Must be either supervisor or coordinator only.")
    private String position;


    @AssertFalse(message = "onLeave Must be initially set to false.")
    private boolean onLeave;


    @NotNull(message = "hireDate cannot be null")
    @PastOrPresent(message = "should be a date in the past or the present.")
    private Date hireDate;

@NotNull(message = "annualLeave cannot be null")
  @PositiveOrZero(message = "annualLeave Must be a positive number.")
    private int annualLeave;

}
