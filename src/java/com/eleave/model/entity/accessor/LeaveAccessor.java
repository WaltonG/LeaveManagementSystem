/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.model.entity.accessor;

import com.eleave.model.entity.LeaveApplication;
import com.eleave.model.entity.Leaves;
import java.util.Collections;
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
public class LeaveAccessor {

    @PersistenceContext(unitName = "WebApplication3PU")
    private EntityManager em;
    @Inject
    private Leaves leave;

    public boolean createLeave(String leavename) {
        Leaves leve = em.find(Leaves.class, leavename);
        if (leve != null) {
            return false;
        } else {
            leave = new Leaves(leavename);
            em.persist(leave);
        }
        return true;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Leaves> getLeaveList() {
        List<Leaves> result = Collections.emptyList();
        result = em.createNamedQuery("Leaves.findAll").getResultList();
        return result;
    }

    public void removeApplication(String leavename) {
        leave = em.find(Leaves.class, leavename);
        em.remove(leave);
    }
}
