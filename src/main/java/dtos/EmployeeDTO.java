/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;


public class EmployeeDTO {
    private long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee emp) {
    }

    public static List<EmployeeDTO> getDtos(List<Employee> emp){
        List<EmployeeDTO> empdtos = new ArrayList();
        emp.forEach(em->empdtos.add(new EmployeeDTO(em)));
        return empdtos;
    }

}
