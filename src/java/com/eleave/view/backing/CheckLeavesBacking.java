/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.LeaveApplicationPK;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.model.entity.accessor.LeaveApplicationAccessor;
import com.eleave.view.backing.controller.Login;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIData;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named(value = "checkLeavesBacking")
@RequestScoped
public class CheckLeavesBacking {

    @Inject
    private EmployeeAccessor employeeAccessor;
    private DataModel<LeaveApplication> leaveApplications;
    @Inject
    private Login login;
    @Inject
    private LeaveApplicationAccessor leaveApplicationAccessor;
    private UIData leaveApps;
    private LeaveApplication currentLeaveApplication;
    private LeaveApplicationPK leaveApplicationPK;
    private ArrayList<LeaveApplication> leaveApp;

    /**
     * Creates a new instance of CheckLeavesBacking
     */
    public CheckLeavesBacking() {
    }

    public DataModel<LeaveApplication> getLeaveApplications() {
        leaveApplications = new ListDataModel<LeaveApplication>(
                leaveApplicationAccessor.getLeaveApplicationList(login.getEmpid(), employeeAccessor.findEmployee(login.getEmpid()).getDepartment()));
        return leaveApplications;
    }

    public void setLeaveApplications(DataModel<LeaveApplication> leaveApplications) {
        this.leaveApplications = leaveApplications;
    }

    public UIData getLeaveApps() {
        return leaveApps;
    }

    public void setLeaveApps(UIData leaveApps) {
        this.leaveApps = leaveApps;
    }

    public LeaveApplication getCurrentLeaveApplication() {
        return currentLeaveApplication;
    }

    public ArrayList<LeaveApplication> getLeaveApp() {
        leaveApp = new ArrayList<LeaveApplication>(leaveApplicationAccessor.getLeaveApplicationList(login.getEmpid(), employeeAccessor.findEmployee(login.getEmpid()).getDepartment()));
        return leaveApp;
    }

    public void cancelApplication() {
        currentLeaveApplication = (LeaveApplication) leaveApps.getRowData();
        leaveApplicationPK = currentLeaveApplication.getLeaveApplicationPK();
        leaveApplicationAccessor.removeApplication(leaveApplicationPK);
    }
}
