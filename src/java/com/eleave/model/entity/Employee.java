/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.model.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "EMPLOYEE", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmpid", query = "SELECT e FROM Employee e WHERE e.empid = :empid"),
    @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
    @NamedQuery(name = "Employee.findByDepartment", query = "SELECT e FROM Employee e WHERE e.department = :department"),
    @NamedQuery(name = "Employee.findBySecretquestion", query = "SELECT e FROM Employee e WHERE e.secretquestion = :secretquestion"),
    @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Employee.findByLeavedays", query = "SELECT e FROM Employee e WHERE e.leavedays = :leavedays"),
    @NamedQuery(name = "Employee.findByAccesslevel", query = "SELECT e FROM Employee e WHERE e.accesslevel = :accesslevel")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMPID", nullable = false, length = 100)
    private String empid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DEPARTMENT", nullable = false, length = 100)
    private String department;
    @Size(max = 100)
    @Column(name = "SECRETQUESTION", length = 100)
    private String secretquestion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FIRSTNAME", nullable = false, length = 100)
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LASTNAME", nullable = false, length = 100)
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LEAVEDAYS", nullable = false)
    private int leavedays;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCESSLEVEL", nullable = false)
    private int accesslevel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Collection<LeaveApplication> leaveApplicationCollection;

    public Employee() {
    }

    public Employee(String empid) {
        this.empid = empid;
    }

    public Employee(String empid, String password, String department, String firstname, String lastname, int leavedays, int accesslevel) {
        this.empid = empid;
        this.password = password;
        this.department = department;
        this.firstname = firstname;
        this.lastname = lastname;
        this.leavedays = leavedays;
        this.accesslevel = accesslevel;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSecretquestion() {
        return secretquestion;
    }

    public void setSecretquestion(String secretquestion) {
        this.secretquestion = secretquestion;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getLeavedays() {
        return leavedays;
    }

    public void setLeavedays(int leavedays) {
        this.leavedays = leavedays;
    }

    public int getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(int accesslevel) {
        this.accesslevel = accesslevel;
    }

    @XmlTransient
    public Collection<LeaveApplication> getLeaveApplicationCollection() {
        return leaveApplicationCollection;
    }

    public void setLeaveApplicationCollection(Collection<LeaveApplication> leaveApplicationCollection) {
        this.leaveApplicationCollection = leaveApplicationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empid != null ? empid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empid == null && other.empid != null) || (this.empid != null && !this.empid.equals(other.empid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eleave.model.entity.Employee[ empid=" + empid + " ]";
    }
    
}
