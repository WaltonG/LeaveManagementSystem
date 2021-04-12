/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eleave.model.entity.accessor;

import com.eleave.model.entity.Employee;
import com.eleave.model.entity.Leaves;
import java.security.Key;
import java.util.Collections;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class EmployeeAccessor {

    @PersistenceContext(unitName = "WebApplication3PU")
    private EntityManager em;
    @Inject
    private Employee employee;
    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't',
        'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private String pass;

    public String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public boolean createEmployee(String empid, String password, String department,
            String firstname, String lastname, int leavedays, int accesslevel) throws Exception {
        Employee emp = em.find(Employee.class, empid);
        if (emp != null) {
            return false;
        } else {
            pass = encrypt(password);
            employee = new Employee(empid, pass, department, firstname, lastname,
                    leavedays, accesslevel);
            em.persist(employee);
        }
        return true;
    }

    /**
     *
     * @Find employee with given id
     */
    public Employee findEmployee(String empid) {
        Employee result = em.find(Employee.class, empid);
        return result;
    }

    public void addSecretQuestion(String empid, String secretQuestion, String password) throws Exception {
        employee = em.find(Employee.class, empid);
        employee.setPassword(encrypt(password));
        employee.setSecretquestion(secretQuestion);
        em.persist(employee);
    }
    
    public void addNewPassword(String empid, String password) throws Exception {
        employee = em.find(Employee.class, empid);
        employee.setPassword(encrypt(password));
        em.persist(employee);
    }

    public String findSecretQuestion(String empid) {
        return em.find(Employee.class, empid).getSecretquestion();
    }

    public void updateLeaveBalance(String empid, int daysApplied) {
        employee = em.find(Employee.class, empid);
        int balance = employee.getLeavedays() - daysApplied;
        employee.setLeavedays(balance);
        em.persist(employee);
    }

    public List<Employee> getEmployeeList() {
        List<Employee> result = Collections.emptyList();
        result = em.createNamedQuery("Employee.findAll").getResultList();
        return result;
    }

    public void removeEmployee(String empid) {
         employee  = em.find(Employee.class, empid);
        em.remove(employee);
    }

    public void updateLeave(String empid) {
        employee = em.find(Employee.class, empid);
        int balance = employee.getLeavedays() + 30;
        employee.setLeavedays(balance);
        em.persist(employee);
    }
}
