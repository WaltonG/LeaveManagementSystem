/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named(value = "logOut")
@RequestScoped
public class LogOut {

    /**
     * Creates a new instance of LogOut
     */
    public LogOut() {
    }

    public String logout() {
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
}
