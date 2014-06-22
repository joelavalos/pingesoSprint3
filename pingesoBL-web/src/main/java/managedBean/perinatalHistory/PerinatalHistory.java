/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.perinatalHistory;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class PerinatalHistory {

    String[] familyHistory = new String[5];
    String[] personalHistory = new String[5];
    String reasonAbortion;
    String[] bornCheck;
    int deeds;
    int abortions;
    int births;           
    int born;
    int stillbirths;
    int living;
    int deadFirstWeek;
    int deadSecondWeek;
    Date lastPregnancy;
    int RNHeavier;
    
    //embarazo actual
    int currentWeight;
    int usualWeight;
    Date FUR;
    Date FURO;
    
    
    public void test(){
        for(int i = 0 ; i< 5; i++){
            System.out.println(familyHistory[i]);
            System.out.println(personalHistory[i]);
        }
    }

    public int getDeeds() {
        return deeds;
    }

    public void setDeeds(int deeds) {
        this.deeds = deeds;
    }

    public int getAbortions() {
        return abortions;
    }

    public void setAbortions(int abortions) {
        this.abortions = abortions;
    }

    public int getBirths() {
        return births;
    }

    public void setBirths(int births) {
        this.births = births;
    }

    public String[] getBornCheck() {
        return bornCheck;
    }

    public void setBornCheck(String[] bornCheck) {
        this.bornCheck = bornCheck;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public int getStillbirths() {
        return stillbirths;
    }

    public void setStillbirths(int stillbirths) {
        this.stillbirths = stillbirths;
    }

    public int getLiving() {
        return living;
    }

    public void setLiving(int living) {
        this.living = living;
    }

    public int getDeadFirstWeek() {
        return deadFirstWeek;
    }

    public void setDeadFirstWeek(int deadFirstWeek) {
        this.deadFirstWeek = deadFirstWeek;
    }

    public int getDeadSecondWeek() {
        return deadSecondWeek;
    }

    public void setDeadSecondWeek(int deadSecondWeek) {
        this.deadSecondWeek = deadSecondWeek;
    }

    public Date getLastPregnancy() {
        return lastPregnancy;
    }

    public void setLastPregnancy(Date lastPregnancy) {
        this.lastPregnancy = lastPregnancy;
    }

    public int getRNHeavier() {
        return RNHeavier;
    }

    public void setRNHeavier(int RNHeavier) {
        this.RNHeavier = RNHeavier;
    }

    
    public String getReasonAbortion() {
        return reasonAbortion;
    }

    public void setReasonAbortion(String reasonAbortion) {
        this.reasonAbortion = reasonAbortion;
    }

    public String[] getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String[] familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String[] getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String[] personalHistory) {
        this.personalHistory = personalHistory;
    }
    
    
    
}
