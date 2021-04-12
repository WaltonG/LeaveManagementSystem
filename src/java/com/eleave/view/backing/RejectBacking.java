/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.LeaveApplicationPK;
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
@Named(value = "rejectBacking")
@RequestScoped
public class RejectBacking {
 private String recommendation;
    @Inject
    private ProcessLeaveBacking processLeaveBacking;
    @Inject
    private LeaveApplicationAccessor leaveApplicationAccessor;
    private LeaveApplication leaveApplication;
    private LeaveApplicationPK leaveApplicationPK;
    /**
     * Creates a new instance of RejectBacking
     */
    public RejectBacking() {
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
        leaveApplicationAccessor.updateLeaveApp(
                leaveApplicationPK, recommendation, "Rejected");
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("processleave.xhtml");
    }
}
