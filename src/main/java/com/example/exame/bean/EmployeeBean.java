package com.example.exame.bean;


import com.example.exame.dao.EmployeeDAO;
import com.example.exame.model.Employee;
import jakarta.annotation.ManagedBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean

public class EmployeeBean implements Serializable {
    private Integer id;
    private String name;
    private String email;

    private Employee employee;

    private List<Employee> employees;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Employee> getEmployees() {
        return employees;
    }



    public EmployeeBean() throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employees = employeeDAO.getAllEmployees();
    }




    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("Email Address is Not Valid");
            throw new ValidatorException(message);
        }
    }
    public void saveEmployee() {
        employee = new Employee(employee.getId(),employee.getName(), employee.getEmail(),employee.getSkills());
        EmployeeDAO employeeDAO = new EmployeeDAO();

        employeeDAO.addEmployee(employee);

        employees = employeeDAO.getAllEmployees();

    }




    public void deleteEmployee(Employee employee) throws SQLException {
        EmployeeDAO empl=new EmployeeDAO();
        empl.deleteEmployee(employee.getId());

        employees.remove(employee);
    }


}
