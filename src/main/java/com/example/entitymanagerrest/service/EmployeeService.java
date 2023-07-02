package com.example.entitymanagerrest.service;

import com.example.entitymanagerrest.dto.EmployeeDto;
import com.example.entitymanagerrest.entity.Employee;
import com.example.entitymanagerrest.repository.EmployeeEntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final ModelMapper employeeMapper;
    private final EmployeeEntityManager employeeEntityManager;

    @Autowired
    public EmployeeService(ModelMapper employeeMapper, EmployeeEntityManager employeeEntityManager) {
        this.employeeMapper = employeeMapper;
        this.employeeEntityManager = employeeEntityManager;
    }

    public List<EmployeeDto> findAll() {
        List<Employee> employeeList = employeeEntityManager.findAll();
        return employeeList.stream().map(employee -> employeeMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    public EmployeeDto findById(UUID id) {
        Employee employeeById = employeeEntityManager.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return employeeMapper.map(employeeById, EmployeeDto.class);
    }


    public EmployeeDto findByNickname(String nickname) {
        Employee employeeByNickname = employeeEntityManager.findByNickname(nickname)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return employeeMapper.map(employeeByNickname, EmployeeDto.class);
    }

    @Transactional
    public Employee save(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.map(employeeDto, Employee.class);
        return employeeEntityManager.save(employee);
    }

    @Transactional
    public void update(EmployeeDto employeeDto) {
        Employee updatedEmployee = employeeEntityManager.findByNickname(employeeDto.getNickname())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        updatedEmployee.setName(employeeDto.getName());
        updatedEmployee.setAge(employeeDto.getAge());
        updatedEmployee.setEmail(employeeDto.getEmail());
        updatedEmployee.setNickname(employeeDto.getNickname());
        employeeEntityManager.save(updatedEmployee);
    }

    @Transactional
    public void delete(UUID id) {
        employeeEntityManager.deleteByNickname(id);
    }

}
