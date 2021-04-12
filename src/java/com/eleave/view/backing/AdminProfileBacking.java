/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.accessor.EmployeeAccessor;
import com.eleave.view.backing.controller.Login;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Administrator
 */
@Named(value = "adminProfileBacking")
@SessionScoped
public class AdminProfileBacking implements Serializable {

    private String passwordOne;
    private String passwordTwo;
    private String secretQuestion;
    @Inject
    private Login login;
    @Inject
    private EmployeeAccessor employeeAccessor;

    /**
     * Creates a new instance of AdminProfileBacking
     */
    public AdminProfileBacking() {
    }

    public String getPasswordOne() {
        return passwordOne;
    }

    public void setPasswordOne(String passwordOne) {
        this.passwordOne = passwordOne;
    }

    public String getPasswordTwo() {
        return passwordTwo;
    }

    public void setPasswordTwo(String passwordTwo) {
        this.passwordTwo = passwordTwo;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public void submit() throws Exception {
        if (!passwordOne.equals(passwordTwo)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("adminprofile:password1", new FacesMessage(" * Passwords don't match"));
        } else {
            employeeAccessor.addSecretQuestion(login.getEmpid(), secretQuestion, passwordOne);
            if (employeeAccessor.findEmployee(login.getEmpid()).getAccesslevel() == 1) {
                FacesContext ct = FacesContext.getCurrentInstance();
                ct.getExternalContext().redirect("adminpage.xhtml");
            }
            if (employeeAccessor.findEmployee(login.getEmpid()).getAccesslevel() == 2) {
                 FacesContext ctt = FacesContext.getCurrentInstance();
                    ctt.getExternalContext().redirect("hodpage.xhtml");
            }
            if (employeeAccessor.findEmployee(login.getEmpid()).getAccesslevel() == 3) {
                 FacesContext ctxx = FacesContext.getCurrentInstance();
                    ctxx.getExternalContext().redirect("homePage.xhtml");
            }
        }
    }
}
