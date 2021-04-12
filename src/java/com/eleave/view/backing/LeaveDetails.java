/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.view.backing;

import com.eleave.model.entity.Leaves;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.LocalDate;

/**
 *
 * @author Administrator
 */
@Named(value = "leaveDetails")
@RequestScoped
public class LeaveDetails {

    @PersistenceContext(unitName = "WebApplication3PU")
    private EntityManager em;
    private String startdate;
    private int days;
    private String leavename;
    private String reason;
    private ArrayList<SelectItem> leavenames = new ArrayList<SelectItem>();
    private LocalDate today;

    /**
     * Creates a new instance of LeaveDetails
     */
    public LeaveDetails() {
    }

    @PostConstruct
    private void findLeaveDetails() {
        ArrayList<String> lvenames = new ArrayList<String>();
        List<Leaves> leaves = em.createNamedQuery(
                "Leaves.findAll").getResultList();
        for (Leaves lve : leaves) {
            lvenames.add(lve.getLeavename());
        }
        for (String name : lvenames) {
            leavenames.add(new SelectItem(name));
        }
        
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getLeavename() {
        return leavename;
    }

    public void setLeavename(String leavename) {
        this.leavename = leavename;
    }

    public ArrayList<SelectItem> getLeavenames() {
        return leavenames;
    }

    public void setLeavenames(ArrayList<SelectItem> leavenames) {
        this.leavenames = leavenames;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public LocalDate getToday() {
        today = new LocalDate();
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }
}
