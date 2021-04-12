/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.model.entity.accessor;

import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.LeaveApplicationPK;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class LeaveApplicationAccessor {

    @PersistenceContext(unitName = "WebApplication3PU")
    private EntityManager em;
    @Inject
    private LeaveApplication leaveApplication;
    @Inject
    private EmployeeAccessor employeeAccessor;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public LeaveApplication findLeaveApplication(
            LeaveApplicationPK leaveApplicationPK) {
        leaveApplication = em.find(
                LeaveApplication.class, leaveApplicationPK);
        return leaveApplication;
    }

    public void addApplication(LeaveApplicationPK leaveApplicationPK, String enddate,
            int daysapplied, String department, String reason) throws ParseException {
        Date dateapplied = new Date();
        leaveApplication = new LeaveApplication(leaveApplicationPK, formatter.parse(enddate), dateapplied, daysapplied, department, reason);
        leaveApplication.setStatus("pending");
        leaveApplication.setFirstname(employeeAccessor.findEmployee(leaveApplicationPK.getEmpid()).getFirstname());
        leaveApplication.setLastname(employeeAccessor.findEmployee(leaveApplicationPK.getEmpid()).getLastname());
        em.persist(leaveApplication);
    }

    public List<LeaveApplication> getLeaveApplicationList(String empID, String department) {
        List<LeaveApplication> result = Collections.emptyList();
        result = em.createNamedQuery(
                "LeaveApplication.findByEmpiddepartment").setParameter(
                "empid", empID).setParameter("department", department).getResultList();
        return result;
    }

    public List<LeaveApplication> getApplicationList(String department) {
        List<LeaveApplication> result = Collections.emptyList();
        result = em.createNamedQuery(
                "LeaveApplication.findByDepartment").setParameter(
                "department", department).getResultList();
        return result;
    }

    public List<LeaveApplication> getApplicationListDepartmentPending(String department) {
        List<LeaveApplication> result = Collections.emptyList();
        result = em.createNamedQuery(
                "LeaveApplication.findByStatusDepartment").setParameter(
                "department", department).setParameter("status", "pending").getResultList();
        return result;
    }

    public void removeApplication(LeaveApplicationPK leaveApplicationPK) {
        leaveApplication = em.find(LeaveApplication.class, leaveApplicationPK);
        em.remove(leaveApplication);
    }

    public void updateLeaveApplication(LeaveApplicationPK leaveApplicationPK, String recommendation, String approved) {
        leaveApplication = em.find(LeaveApplication.class, leaveApplicationPK);
        leaveApplication.setStatus(approved);
        leaveApplication.setRecommendation(recommendation);
        em.merge(leaveApplication);
    }

    public void updateLeaveApp(LeaveApplicationPK leaveApplicationPK, String recommendation, String rejected) {
        leaveApplication = em.find(LeaveApplication.class, leaveApplicationPK);
        leaveApplication.setStatus(rejected);
        leaveApplication.setRecommendation(recommendation);
        em.merge(leaveApplication);
    }

    public List<LeaveApplication> getStatusPending(String empID, String department) {
        List<LeaveApplication> result = Collections.emptyList();
        result = em.createNamedQuery(
                "LeaveApplication.findByStatusEmpidDepartment").setParameter("empid", empID).setParameter("status", "pending").setParameter("department", department).getResultList();
        return result;
    }

    public List<LeaveApplication> getApplicationListDate(Date fromDate, Date toDate, String department) {
        List<LeaveApplication> result = Collections.emptyList();
        result = em.createNamedQuery(
                "LeaveApplication.findByDateDepartment").setParameter(
                "department", department).setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
        return result;
    }
}
