/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.accessor.EmployeeAccessor;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named(value = "addEmployeeBacking")
@RequestScoped
public class AddEmployeeBacking {

    private String empid;
    private String password;
    private String department;
    private String lastName;
    private String firstName;
    private int accessLevel;
    private int leavedays;
    @Inject
    private EmployeeAccessor employeeaccessor;

    /**
     * Creates a new instance of AddEmployeeBacking
     */
    public AddEmployeeBacking() {
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getLeavedays() {
        return leavedays;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void addEmployee() throws IOException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean added = employeeaccessor.createEmployee(empid, password, department,
                firstName, lastName, leavedays, accessLevel);
        if (added) {
            context.getExternalContext().redirect("adminpage.xhtml");
        } else {
            context.addMessage("addemployee:empid", new FacesMessage("Employee Exists"));
        }
    }
}
