package com.example.exame.dao;

import com.example.exame.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeeDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EmployeeDAO() {
        // Initialize EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
        // Create EntityManager
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Employee> getAllEmployees() {
        // Use JPQL (Java Persistence Query Language) to fetch all employees
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    public void addEmployee(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee); // This will insert the employee into the database
        entityManager.getTransaction().commit();
    }

    public void deleteEmployee(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(employee); // This will delete the employee from the database
            entityManager.getTransaction().commit();
        }
    }

    // Ensure to close EntityManager and EntityManagerFactory when done
    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
