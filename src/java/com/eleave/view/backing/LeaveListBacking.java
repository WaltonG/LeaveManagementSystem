/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.Leaves;
import com.eleave.model.entity.accessor.LeaveAccessor;
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
@Named(value = "leaveListBacking")
@RequestScoped
public class LeaveListBacking {

    private DataModel<Leaves> leaves;
    @Inject
    private LeaveAccessor leaveAccessor;
    private UIData leaveApps;
    private ArrayList<Leaves> leaveApp;
     private Leaves currentLeave;

    /**
     * Creates a new instance of LeaveListBacking
     */
    public LeaveListBacking() {
    }

    public DataModel<Leaves> getLeaves() {
        leaves = new ListDataModel<Leaves>(
                leaveAccessor.getLeaveList());
        return leaves;
    }

    public void setLeaves(DataModel<Leaves> leaves) {
        this.leaves = leaves;
    }

    public UIData getLeaveApps() {
        return leaveApps;
    }

    public void setLeaveApps(UIData leaveApps) {
        this.leaveApps = leaveApps;
    }

    public ArrayList<Leaves> getLeaveApp() {
        leaveApp = new ArrayList<Leaves>(leaveAccessor.getLeaveList());
        return leaveApp;
    }

    public Leaves getCurrentLeave() {
        return currentLeave;
    }

    public void setCurrentLeave(Leaves currentLeave) {
        this.currentLeave = currentLeave;
    }
    
    
     public void delete() {
        currentLeave= (Leaves) leaveApps.getRowData();
        leaveAccessor.removeApplication(currentLeave.getLeavename());
    }
}
