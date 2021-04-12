/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.LeaveApplicationPK;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.model.entity.accessor.LeaveApplicationAccessor;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Administrator
 */
@Named(value = "approveBacking")
@RequestScoped
public class ApproveBacking {

    private String recommendation;
    @Inject
    private ProcessLeaveBacking processLeaveBacking;
    @Inject
    private LeaveApplicationAccessor leaveApplicationAccessor;
    @Inject
    private EmployeeAccessor employeeAccessor;
    private LeaveApplication leaveApplication;
    private LeaveApplicationPK leaveApplicationPK;

    /**
     * Creates a new instance of ApproveBacking
     */
    public ApproveBacking() {
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public void updateLeave() throws IOException {
        leaveApplication = processLeaveBacking.getCurrentLeaveApplication();
        leaveApplicationPK = leaveApplication.getLeaveApplicationPK();
        leaveApplicationAccessor.updateLeaveApplication(
                leaveApplicationPK, recommendation, "Approved");
        int daysApplied = leaveApplication.getDaysapplied();
        employeeAccessor.updateLeaveBalance(leaveApplicationPK.getEmpid(), daysApplied );
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("processleave.xhtml");
    }
}
