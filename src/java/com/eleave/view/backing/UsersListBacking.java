/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.Employee;
import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.LeaveApplicationPK;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.model.entity.accessor.LeaveApplicationAccessor;
import com.eleave.view.backing.controller.Login;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIData;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;


/**
 *
 * @author Administrator
 */
@Named(value = "usersListBacking")
@RequestScoped
public class UsersListBacking {
@Inject
    private EmployeeAccessor employeeAccessor;
    private DataModel<Employee> employees;
    @Inject
    private Login login;
    @Inject
    private LeaveApplicationAccessor leaveApplicationAccessor;
    private UIData employeeApps;
    private Employee currentEmployee;
    private ArrayList<Employee> employeeApp;

    /**
     * Creates a new instance of UsersListBacking
     */
    public UsersListBacking() {
    }
    
    public DataModel<Employee> getEmployees() {
        employees = new ListDataModel<Employee>(
                employeeAccessor.getEmployeeList());
        return employees;
    }

    public void setEmployees(DataModel<Employee> employees) {
        this.employees = employees;
    }

    public UIData getEmployeeApps() {
        return employeeApps;
    }

    public void setEmployeeApps(UIData employeeApps) {
        this.employeeApps = employeeApps;
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

  

    public ArrayList<Employee> getEmployeeApp () {
      employeeApp = new ArrayList<Employee>(employeeAccessor.getEmployeeList());
        return  employeeApp;
    }

    public void delete() {
        currentEmployee = (Employee) employeeApps.getRowData();
      
        employeeAccessor.removeEmployee(currentEmployee.getEmpid());
    }
}

