/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.issuetracker.controllers;

import hu.elte.issuetracker.entities.Employees;
import hu.elte.issuetracker.repositories.EmployeesRepository;
import hu.elte.issuetracker.security.AuthenticatedUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Solti
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesRepository employeesRepository;
    
    @Autowired 
    private AuthenticatedUser authenticatedUser;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Employees>> getAll() {
        return ResponseEntity.ok(employeesRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Employees> get(@PathVariable Integer id) {
        Optional<Employees> employees = employeesRepository.findById(id);
        if (employees.isPresent()) {
            return ResponseEntity.ok(employees.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Employees> post(@RequestBody Employees employees) {
        Employees savedEmployees = employeesRepository.save(employees);
        return ResponseEntity.ok(savedEmployees);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employees> put(@RequestBody Employees employees, @PathVariable Integer id) {
        Optional<Employees> oEmployees = employeesRepository.findById(id);
        if (oEmployees.isPresent()) {
            employees.setId(id);
            return ResponseEntity.ok(employeesRepository.save(employees));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Employees> oEmployees = employeesRepository.findById(id);
        if (oEmployees.isPresent()) {
            employeesRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}