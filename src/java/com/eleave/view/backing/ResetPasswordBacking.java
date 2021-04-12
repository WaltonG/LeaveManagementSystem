/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.Employee;
import com.eleave.model.entity.accessor.EmployeeAccessor;
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
@Named(value = "resetPasswordBacking")
@SessionScoped
public class ResetPasswordBacking implements Serializable {

    private String passwordOne;
    private String passwordTwo;
    private String secretQuestion;
    private String empid;
    @Inject
    private Employee employee;
    @Inject
    private EmployeeAccessor employeeAccessor;

    /**
     * Creates a new instance of ResetPasswordBacking
     */
    public ResetPasswordBacking() {
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
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
        FacesContext context = FacesContext.getCurrentInstance();
        employee = employeeAccessor.findEmployee(empid);
        if (employee == null) {
            context.addMessage("resetpassword:empid", new FacesMessage(" * invalid Id"));
        } else if (!secretQuestion.equals(employeeAccessor.findSecretQuestion(empid))) {
            FacesContext t = FacesContext.getCurrentInstance();
            t.addMessage("resetpassword:secretquestion", new FacesMessage(" * Wrong Answer"));
        } else if (!passwordOne.equals(passwordTwo)) {
            FacesContext x = FacesContext.getCurrentInstance();
            x.addMessage("resetpassword:password1", new FacesMessage(" * Passwords don't match"));
        } else {
            employeeAccessor.addNewPassword(empid, passwordOne);
            FacesContext x = FacesContext.getCurrentInstance();
            x.getExternalContext().redirect("index.xhtml");

        }
    }
}
