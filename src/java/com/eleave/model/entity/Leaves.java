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
@Table(name = "LEAVES", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leaves.findAll", query = "SELECT l FROM Leaves l"),
    @NamedQuery(name = "Leaves.findByLeavename", query = "SELECT l FROM Leaves l WHERE l.leavename = :leavename")})
public class Leaves implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LEAVENAME", nullable = false, length = 100)
    private String leavename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaves")
    private Collection<LeaveApplication> leaveApplicationCollection;

    public Leaves() {
    }

    public Leaves(String leavename) {
        this.leavename = leavename;
    }

    public String getLeavename() {
        return leavename;
    }

    public void setLeavename(String leavename) {
        this.leavename = leavename;
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
        hash += (leavename != null ? leavename.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leaves)) {
            return false;
        }
        Leaves other = (Leaves) object;
        if ((this.leavename == null && other.leavename != null) || (this.leavename != null && !this.leavename.equals(other.leavename))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eleave.model.entity.Leaves[ leavename=" + leavename + " ]";
    }
    
}
