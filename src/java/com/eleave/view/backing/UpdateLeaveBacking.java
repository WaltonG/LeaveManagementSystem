/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.Employee;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Named(value = "updateLeaveBacking")
@SessionScoped
public class UpdateLeaveBacking implements Serializable {
 private ArrayList<SelectItem> empIds = new ArrayList<SelectItem>();
    @PersistenceContext(unitName = "WebApplication3PU")
    private EntityManager em;
    private String empid;
     @Inject
    private EmployeeAccessor employeeAccessor;
    /**
     * Creates a new instance of UpdateLeaveBacking
     */
    public UpdateLeaveBacking() {
    }
    
    @PostConstruct
    private void findEmpids() {
        ArrayList<String> lvenames = new ArrayList<String>();
        List<Employee> ids = em.createNamedQuery(
                "Employee.findAll").getResultList();
        for (Employee lve : ids) {
            lvenames.add(lve.getEmpid());
        }
        for (String name : lvenames) {
            empIds.add(new SelectItem(name));
        }
        
    }

    public ArrayList<SelectItem> getEmpIds() {
        return empIds;
    }

    public void setEmpIds(ArrayList<SelectItem> empIds) {
        this.empIds = empIds;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

     public void apply(){
     employeeAccessor.updateLeave(empid);
     }
}
