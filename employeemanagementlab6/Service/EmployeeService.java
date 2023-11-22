package com.example.employeemanagementlab6.Service;

import com.example.employeemanagementlab6.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {

    ArrayList<Employee> employees = new ArrayList<>();


    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public boolean updateEmployee(String id, Employee employee) {

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                employees.set(i, employee);
                return true;
            }

        }
        return false;
    }


    public boolean deleteEmployee(String id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                employees.remove(i);
                return true;
            }

        }
        return false;
    }


    public ArrayList<Employee> searchEmployee(String position) {
        ArrayList<Employee> employees1 = new ArrayList<>();

        if (position.equals("supervisor") || position.equals("coordinator")) {
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getPosition().equals(position)) {
                    employees1.add(employees.get(i));

                }

            }
            return employees1;
        }
        return null;
    }

    public ArrayList<Employee> getEmployeesByAgeRange(int age1, int age2) {
        ArrayList<Employee> employees1 = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getAge() >= age1 && employees.get(i).getAge() <= age2) {
                employees1.add(employees.get(i));
            }

        }
        return employees1;
    }

    public boolean applyAnnualLeave(String id) {

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(id)) {
                if (!employees.get(i).isOnLeave()) {
                    if (employees.get(i).getAnnualLeave() > 0) {
                        employees.get(i).setOnLeave(true);
                        employees.get(i).setAnnualLeave(employees.get(i).getAnnualLeave() - 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public ArrayList<Employee> getEmployeesNoAnnualLeave() {
        ArrayList<Employee> employees1 = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getAnnualLeave() == 0) {
                employees1.add(employees.get(i));
            }

        }
        return employees1;
    }


    //2 loop
    public boolean promoteEmployee(String supervisorId, String employeeId) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(supervisorId)) {

                for (int j = 0; j < employees.size(); j++) {
                    if (employees.get(j).getId().equals(employeeId)) {
                        if (employees.get(i).getPosition().equals("supervisor")) {
                            if (employees.get(j).getAge() > 30) {
                                if (!employees.get(j).isOnLeave()) {
                                    employees.get(j).setPosition("supervisor");
                                }
                            }

                        }


                    }


                }
            }
        }
        return true;
    }
}
















