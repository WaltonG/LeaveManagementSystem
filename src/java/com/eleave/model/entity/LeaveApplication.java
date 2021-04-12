/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.model.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "LEAVE_APPLICATION", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveApplication.findAll", query = "SELECT l FROM LeaveApplication l"),
    @NamedQuery(name = "LeaveApplication.findByEmpid", query = "SELECT l FROM LeaveApplication l WHERE l.leaveApplicationPK.empid = :empid"),
    @NamedQuery(name = "LeaveApplication.findByLeavename", query = "SELECT l FROM LeaveApplication l WHERE l.leaveApplicationPK.leavename = :leavename"),
    @NamedQuery(name = "LeaveApplication.findByStartdate", query = "SELECT l FROM LeaveApplication l WHERE l.leaveApplicationPK.startdate = :startdate"),
    @NamedQuery(name = "LeaveApplication.findByEnddate", query = "SELECT l FROM LeaveApplication l WHERE l.enddate = :enddate"),
    @NamedQuery(name = "LeaveApplication.findByDateapplied", query = "SELECT l FROM LeaveApplication l WHERE l.dateapplied = :dateapplied"),
    @NamedQuery(name = "LeaveApplication.findByDaysapplied", query = "SELECT l FROM LeaveApplication l WHERE l.daysapplied = :daysapplied"),
    @NamedQuery(name = "LeaveApplication.findByDepartment", query = "SELECT l FROM LeaveApplication l WHERE l.department = :department"),
    @NamedQuery(name = "LeaveApplication.findByReason", query = "SELECT l FROM LeaveApplication l WHERE l.reason = :reason"),
    @NamedQuery(name = "LeaveApplication.findByStatus", query = "SELECT l FROM LeaveApplication l WHERE l.status = :status"),
    @NamedQuery(name = "LeaveApplication.findByStatusDepartment", query = "SELECT l FROM LeaveApplication l WHERE l.department = :department AND l.status = :status"),
    @NamedQuery(name = "LeaveApplication.findByDateDepartment", query = "SELECT l FROM LeaveApplication l WHERE l.department = :department AND l.enddate BETWEEN :fromDate AND :toDate"),
    @NamedQuery(name = "LeaveApplication.findByStatusEmpidDepartment", query = "SELECT l FROM LeaveApplication l WHERE l.leaveApplicationPK.empid = :empid AND l.status = :status AND l.department = :department"),
    @NamedQuery(name = "LeaveApplication.findByEmpiddepartment", query = "SELECT l FROM LeaveApplication l WHERE l.leaveApplicationPK.empid = :empid AND l.department = :department"),
    @NamedQuery(name = "LeaveApplication.findByRecommendation", query = "SELECT l FROM LeaveApplication l WHERE l.recommendation = :recommendation"),
    @NamedQuery(name = "LeaveApplication.findByFirstname", query = "SELECT l FROM LeaveApplication l WHERE l.firstname = :firstname"),
    @NamedQuery(name = "LeaveApplication.findByDates", query = "SELECT l FROM LeaveApplication l WHERE l.firstname = :firstname"),
    @NamedQuery(name = "LeaveApplication.findByLastname", query = "SELECT l FROM LeaveApplication l WHERE l.lastname = :lastname")})
public class LeaveApplication implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LeaveApplicationPK leaveApplicationPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENDDATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEAPPLIED", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateapplied;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAYSAPPLIED", nullable = false)
    private int daysapplied;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DEPARTMENT", nullable = false, length = 100)
    private String department;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "REASON", nullable = false, length = 100)
    private String reason;
    @Size(max = 100)
    @Column(name = "STATUS", length = 100)
    private String status;
    @Size(max = 100)
    @Column(name = "RECOMMENDATION", length = 100)
    private String recommendation;
    @Size(max = 100)
    @Column(name = "FIRSTNAME", length = 100)
    private String firstname;
    @Size(max = 100)
    @Column(name = "LASTNAME", length = 100)
    private String lastname;
    @JoinColumn(name = "LEAVENAME", referencedColumnName = "LEAVENAME", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Leaves leaves;
    @JoinColumn(name = "EMPID", referencedColumnName = "EMPID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Employee employee;

    public LeaveApplication() {
    }

    public LeaveApplication(LeaveApplicationPK leaveApplicationPK) {
        this.leaveApplicationPK = leaveApplicationPK;
    }

    public LeaveApplication(LeaveApplicationPK leaveApplicationPK, Date enddate, Date dateapplied, int daysapplied, String department, String reason) {
        this.leaveApplicationPK = leaveApplicationPK;
        this.enddate = enddate;
        this.dateapplied = dateapplied;
        this.daysapplied = daysapplied;
        this.department = department;
        this.reason = reason;
    }

    public LeaveApplication(String empid, String leavename, Date startdate) {
        this.leaveApplicationPK = new LeaveApplicationPK(empid, leavename, startdate);
    }

    public LeaveApplicationPK getLeaveApplicationPK() {
        return leaveApplicationPK;
    }

    public void setLeaveApplicationPK(LeaveApplicationPK leaveApplicationPK) {
        this.leaveApplicationPK = leaveApplicationPK;
    }

    public Date getEnddate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dt = sdf.format(enddate);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, 1);
        dt = sdf.format(c.getTime());
        enddate = sdf.parse(dt);
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getDateapplied() {
        return dateapplied;
    }

    public void setDateapplied(Date dateapplied) {
        this.dateapplied = dateapplied;
    }

    public int getDaysapplied() {
        return daysapplied;
    }

    public void setDaysapplied(int daysapplied) {
        this.daysapplied = daysapplied;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
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

    public Leaves getLeaves() {
        return leaves;
    }

    public void setLeaves(Leaves leaves) {
        this.leaves = leaves;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveApplicationPK != null ? leaveApplicationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveApplication)) {
            return false;
        }
        LeaveApplication other = (LeaveApplication) object;
        if ((this.leaveApplicationPK == null && other.leaveApplicationPK != null) || (this.leaveApplicationPK != null && !this.leaveApplicationPK.equals(other.leaveApplicationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eleave.model.entity.LeaveApplication[ leaveApplicationPK=" + leaveApplicationPK + " ]";
    }
}
