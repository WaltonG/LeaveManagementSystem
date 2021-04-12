/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.Employee;
import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.model.entity.accessor.LeaveApplicationAccessor;
import com.eleave.view.backing.controller.Login;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named(value = "empIdReportBacking")
@RequestScoped
public class EmpIdReportBacking {
    
    private String empid;
    private DataModel<LeaveApplication> leaveApplications;
    private UIData leaveApps;
    @Inject
    private EmployeeAccessor employeeAccessor;
    private Employee employee;
    @Inject
    private Login login;
    @Inject
    private LeaveApplicationAccessor leaveApplicationAccessor;

    /**
     * Creates a new instance of EmpIdReportBacking
     */
    public EmpIdReportBacking() {
    }
    
    public DataModel<LeaveApplication> getLeaveApplications() {
        return leaveApplications;
    }
    
    public void setLeaveApplications(DataModel<LeaveApplication> leaveApplications) {
        this.leaveApplications = leaveApplications;
    }
    
    public String getEmpid() {
        return empid;
    }
    
    public void setEmpid(String empid) {
        this.empid = empid;
    }
    
    public UIData getLeaveApps() {
        return leaveApps;
    }
    
    public void setLeaveApps(UIData leaveApps) {
        this.leaveApps = leaveApps;
    }
    
    public void search() {
        FacesContext context = FacesContext.getCurrentInstance();
        employee = employeeAccessor.findEmployee(empid);
        if (employee == null) {
            context.addMessage("empsearch:empid", new FacesMessage(" * invalid Id"));
        } else {
            leaveApplications = new ListDataModel<LeaveApplication>(
                    leaveApplicationAccessor.getLeaveApplicationList(empid, employeeAccessor.findEmployee(login.getEmpid()).getDepartment()));
        }
    }
}
