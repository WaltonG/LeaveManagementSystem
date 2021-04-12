/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.model.entity.accessor.LeaveApplicationAccessor;
import com.eleave.view.backing.controller.Login;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Named(value = "dateReportBacking")
@RequestScoped
public class DateReportBacking {

    @Inject
    private EmployeeAccessor employeeAccessor;
    @Inject
    private Login login;
    private String fromDate;
    private String toDate;
    private DataModel<LeaveApplication> leaveApplications;
    private UIData leaveApps;
    @Inject
    private LeaveApplicationAccessor leaveApplicationAccessor;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates a new instance of DateReportBacking
     */
    public DateReportBacking() {
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public DataModel<LeaveApplication> getLeaveApplications(){
       
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

    public void search() throws ParseException {
         leaveApplications = new ListDataModel<LeaveApplication>(
                leaveApplicationAccessor.getApplicationListDate(formatter.parse(fromDate), formatter.parse(toDate), employeeAccessor.findEmployee(login.getEmpid()).getDepartment()));
    }
}
