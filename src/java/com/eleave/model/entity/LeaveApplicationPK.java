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

/**
 *
 * @author Administrator
 */
@Embeddable
public class LeaveApplicationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMPID", nullable = false, length = 100)
    private String empid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LEAVENAME", nullable = false, length = 100)
    private String leavename;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STARTDATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startdate;

    public LeaveApplicationPK() {
    }

    public LeaveApplicationPK(String empid, String leavename, Date startdate) {
        this.empid = empid;
        this.leavename = leavename;
        this.startdate = startdate;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getLeavename() {
        return leavename;
    }

    public void setLeavename(String leavename) {
        this.leavename = leavename;
    }

    public Date getStartdate() throws ParseException {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dt = sdf.format(startdate);
      Calendar c = Calendar.getInstance();
      c.setTime(sdf.parse(dt));
      c.add(Calendar.DATE, 1);
      dt = sdf.format(c.getTime());
      startdate= sdf.parse(dt);
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empid != null ? empid.hashCode() : 0);
        hash += (leavename != null ? leavename.hashCode() : 0);
        hash += (startdate != null ? startdate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveApplicationPK)) {
            return false;
        }
        LeaveApplicationPK other = (LeaveApplicationPK) object;
        if ((this.empid == null && other.empid != null) || (this.empid != null && !this.empid.equals(other.empid))) {
            return false;
        }
        if ((this.leavename == null && other.leavename != null) || (this.leavename != null && !this.leavename.equals(other.leavename))) {
            return false;
        }
        if ((this.startdate == null && other.startdate != null) || (this.startdate != null && !this.startdate.equals(other.startdate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eleave.model.entity.LeaveApplicationPK[ empid=" + empid + ", leavename=" + leavename + ", startdate=" + startdate + " ]";
    }
    
}
