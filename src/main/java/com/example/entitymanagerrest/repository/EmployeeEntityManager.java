package com.example.entitymanagerrest.repository;

import com.example.entitymanagerrest.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmployeeEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT t FROM Employee t", Employee.class); // Java Persistence Query Language
        return query.getResultList();
    }

    public Optional<Employee> findByNickname(String nickname) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT t FROM Employee t WHERE t.nickname = :nickname",
                Employee.class);
        query.setParameter("nickname", nickname);
        List<Employee> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return Optional.of(resultList.get(0));
        } else {
            return Optional.empty();
        }
    }

//    public Employee findByNickname(String nickname) {
//        TypedQuery<Employee> query = entityManager.createQuery(
//                "SELECT t FROM Employee t WHERE t.nickname = :nickname",
//                Employee.class);
//        query.setParameter("nickname", nickname);
//        return query.getSingleResult();
//    }

    @Transactional
    public Employee save(Employee employee) {
        entityManager.merge(employee);
        return employee;
    }

    public Optional<Employee> findById(UUID id) {
        Employee employee = entityManager.find(Employee.class, id);
        return Optional.ofNullable(employee);
    }

//    @Transactional
//    public Employee update(Employee employee) {
//        entityManager.merge(employee);
//        return employee;
//    }

    @Transactional
    public void deleteByNickname(UUID id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }

}
