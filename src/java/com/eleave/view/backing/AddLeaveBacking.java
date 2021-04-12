/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.accessor.LeaveAccessor;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Administrator
 */
@Named(value = "addLeaveBacking")
@RequestScoped
public class AddLeaveBacking {
private String leavename;
 @Inject
    private LeaveAccessor leaveaccessor;
    /**
     * Creates a new instance of AddLeaveBacking
     */
    public AddLeaveBacking() {
    }

    public String getLeavename() {
        return leavename;
    }

    public void setLeavename(String leavename) {
        this.leavename = leavename;
    }
    
      public void addLeave() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean added = leaveaccessor.createLeave(leavename);
        if (added) {
            context.getExternalContext().redirect("adminpage.xhtml");
        } else {
            context.addMessage("addleave:leavename", new FacesMessage("Leave Exists"));
        }
    }
}
