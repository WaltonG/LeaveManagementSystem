/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.model.entity.accessor.LeaveApplicationAccessor;
import com.eleave.view.backing.controller.Login;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
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
@Named(value = "processLeaveBacking")
@SessionScoped
public class ProcessLeaveBacking implements Serializable {

    private DataModel<LeaveApplication> leaveApplications;
    @Inject
    private Login login;
    @Inject
    private LeaveApplicationAccessor leaveApplicationAccessor;
    @Inject
    private EmployeeAccessor employeeAccessor;
    private boolean pending;
    private UIData leaveApps;
    private LeaveApplication currentLeaveApplication;

    /**
     * Creates a new instance of ProcessLeaveBacking
     */
    public ProcessLeaveBacking() {
    }

    public DataModel<LeaveApplication> getLeaveApplications() {
        leaveApplications = new ListDataModel<LeaveApplication>(
                leaveApplicationAccessor.getApplicationListDepartmentPending(employeeAccessor.findEmployee(login.getEmpid()).getDepartment()));
        return leaveApplications;
    }

    public void setLeaveApplications(DataModel<LeaveApplication> leaveApplications) {
        this.leaveApplications = leaveApplications;
    }

    public boolean isPending() {
        currentLeaveApplication = (LeaveApplication) leaveApps.getRowData();
        pending = currentLeaveApplication.getStatus().equals("pending");
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public LeaveApplication getCurrentLeaveApplication() {
        return currentLeaveApplication;
    }

    public void setCurrentLeaveApplication(LeaveApplication currentLeaveApplication) {
        this.currentLeaveApplication = currentLeaveApplication;
    }

    public UIData getLeaveApps() {
        return leaveApps;
    }

    public void setLeaveApps(UIData leaveApps) {
        this.leaveApps = leaveApps;
    }

    public void approveApplication() throws IOException {
        currentLeaveApplication = (LeaveApplication) leaveApps.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("approve.xhtml");
    }

    public void rejectApplication() throws IOException {
        currentLeaveApplication = (LeaveApplication) leaveApps.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("reject.xhtml");
    }
}