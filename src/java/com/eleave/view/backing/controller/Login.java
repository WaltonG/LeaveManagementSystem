/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing.controller;

import com.eleave.model.entity.Employee;
import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.view.backing.UserCredentials;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Administrator
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    @Inject
    private Employee employee;
    @Inject
    private EmployeeAccessor employeeAccessor;
    @Inject
    private UserCredentials userCredentials;
    private String empid;

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }

    public String getEmpid() {
        return empid;
    }

    /**
     * Authenticates employee id and password
     */
    public void login() throws IOException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        employee = employeeAccessor.findEmployee(
                userCredentials.getEmployeeID());
        if (employee == null) {
            context.addMessage("emplogin:empid", new FacesMessage(" * invalid Id"));
        } else if (userCredentials.getPassword().equals(employeeAccessor.decrypt(employee.getPassword()))) {
            if (employee.getAccesslevel() == 1 && employee.getSecretquestion()== null) {
                FacesContext ctxt = FacesContext.getCurrentInstance();
                ctxt.getExternalContext().redirect("profilepage.xhtml");
            }
            if (employee.getAccesslevel() == 1 && !(employee.getSecretquestion()== null)) {
                FacesContext ct = FacesContext.getCurrentInstance();
                ct.getExternalContext().redirect("adminpage.xhtml");
            }

            if (employee.getAccesslevel() == 2 && employee.getSecretquestion() == null) {
                FacesContext c = FacesContext.getCurrentInstance();
                c.getExternalContext().redirect("profilepage.xhtml");
            }
            if (employee.getAccesslevel() == 2 && !(employee.getSecretquestion() == null)) {
                FacesContext ctt = FacesContext.getCurrentInstance();
                ctt.getExternalContext().redirect("hodpage.xhtml");
            }

            if (employee.getAccesslevel() == 3 && employee.getSecretquestion() == null) {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.getExternalContext().redirect("profilepage.xhtml");
            }
            if (employee.getAccesslevel() == 3 && !(employee.getSecretquestion() == null)) {
                FacesContext ctxx = FacesContext.getCurrentInstance();
                ctxx.getExternalContext().redirect("homePage.xhtml");
            }

        } else {
            context.addMessage("emplogin:password", new FacesMessage(" * invalid password"));
        }
        empid = userCredentials.getEmployeeID();
    }
}
