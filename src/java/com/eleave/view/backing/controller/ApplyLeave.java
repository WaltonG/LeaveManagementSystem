/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing.controller;

import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.LeaveApplicationPK;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.model.entity.accessor.LeaveApplicationAccessor;
import com.eleave.view.backing.LeaveDetails;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;
import org.joda.time.LocalDate;

/**
 *
 * @author Administrator
 */
@Named(value = "applyLeave")
@RequestScoped
public class ApplyLeave {

    @Inject
    private Login login;
    @Inject
    private EmployeeAccessor employeeAccessor;
    @Inject
    private LeaveDetails leaveDetails;
    @Inject
    private LeaveApplication leaveApplication;
    @Inject
    private LeaveApplicationPK leaveApplicationPK;
    @Inject
    private LeaveApplicationAccessor leaveApplicationAccessor;
    private FacesContext context = FacesContext.getCurrentInstance();
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates a new instance of ApplyLeave
     */
    public ApplyLeave() {
    }

    public String endDate(LocalDate start, int days) {
        DateCalculator<LocalDate> cal = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator(
                null, HolidayHandlerType.FORWARD);
        cal.setStartDate(start);
        LocalDate end = cal.moveByBusinessDays(days - 1).getCurrentBusinessDate();
        return end.toString();
    }

    public void apply() throws IOException, ParseException {
        LocalDate startdatejoda = new LocalDate(leaveDetails.getStartdate());
        leaveApplicationPK = new LeaveApplicationPK(
                login.getEmpid(), leaveDetails.getLeavename(), formatter.parse(leaveDetails.getStartdate()));
        leaveApplication = leaveApplicationAccessor.findLeaveApplication(leaveApplicationPK);
        if (leaveApplication != null) {
            context.addMessage("applyleave:leavenames", new FacesMessage("Leave Already Applied"));
        } else if (startdatejoda.isBefore(leaveDetails.getToday())) {
            context.addMessage("applyleave:startdate", new FacesMessage("The date has passed"));
        } else if (employeeAccessor.findEmployee(login.getEmpid()).getLeavedays() < leaveDetails.getDays() &&leaveDetails.getLeavename().equalsIgnoreCase("annual") ) {
            context.addMessage("applyleave:days", new FacesMessage("Your leave balance is: "
                    + employeeAccessor.findEmployee(login.getEmpid()).getLeavedays()));
        } else {
            leaveApplicationAccessor.addApplication(leaveApplicationPK, endDate(new LocalDate(leaveDetails.getStartdate()), leaveDetails.getDays()), leaveDetails.getDays(),
                    employeeAccessor.findEmployee(login.getEmpid()).getDepartment(), leaveDetails.getReason());
            if (employeeAccessor.findEmployee(login.getEmpid()).getAccesslevel()== 3) {
             context.getExternalContext().redirect("homePage.xhtml");   
            }
            else {
                context.getExternalContext().redirect("hodpage.xhtml"); 
            }
        }
    }
}
