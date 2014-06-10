/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IPDGES;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class createIPDGES {

    
    private String augeProblem;
    private String augeSubProblem;
    private String diagnosis;
    private String fundamentDiagnosis;
    private String treatment;
    private boolean confirmsGES;
    private Date deadline;
    
    /**
     * Creates a new instance of createIPDGES
     */
    public createIPDGES() {
    }
    
    public void createIPDGES(){
        
        
        
    }

    public String getAugeProblem() {
        return augeProblem;
    }

    public void setAugeProblem(String augeProblem) {
        this.augeProblem = augeProblem;
    }

    public String getAugeSubProblem() {
        return augeSubProblem;
    }

    public void setAugeSubProblem(String augeSubProblem) {
        this.augeSubProblem = augeSubProblem;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getFundamentDiagnosis() {
        return fundamentDiagnosis;
    }

    public void setFundamentDiagnosis(String fundamentDiagnosis) {
        this.fundamentDiagnosis = fundamentDiagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public boolean isConfirmsGES() {
        return confirmsGES;
    }

    public void setConfirmsGES(boolean confirmsGES) {
        this.confirmsGES = confirmsGES;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    
    
}
