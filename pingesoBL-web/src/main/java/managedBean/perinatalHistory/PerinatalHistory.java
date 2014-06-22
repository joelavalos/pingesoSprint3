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

    String[] familyHistory;    
    String[] personalHistory;
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
    int size;
    Date FUR;
    Date FURO;
    String[] estimated;
    String doubts;
    Date FPBirth;
    int gestationalAge;
    int numberDays;
    String doubtsHEA;
    String reason;
    String blood;
    String bloodType;
    String sensitized;
    
    
    String examinationCN;
    String examinationMN;
    String examinationON;
    String normalPelvis;
    String normalPapanic;
    String normalCervix;
    String VIH;
    
    Date VDRL;
    String VDRLOption;
    
    String[] HCTOCheck;
    double HTCTOFloat;
    Date HCTODate;
    
    String smoker;
    int cantCigars ;
    
    public void save(){
        System.out.println(HCTOCheck[0]);
        System.out.println(familyHistory[0]);
    }

    public String[] getHCTOCheck() {
        return HCTOCheck;
    }

    public void setHCTOCheck(String[] HCTOCheck) {
        this.HCTOCheck = HCTOCheck;
    }
    


    public double getHTCTOFloat() {
        return HTCTOFloat;
    }

    public void setHTCTOFloat(double HTCTOFloat) {
        this.HTCTOFloat = HTCTOFloat;
    }

    public Date getHCTODate() {
        return HCTODate;
    }

    public void setHCTODate(Date HCTODate) {
        this.HCTODate = HCTODate;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public int getCantCigars() {
        return cantCigars;
    }

    public void setCantCigars(int cantCigars) {
        this.cantCigars = cantCigars;
    }
   
    public String getExaminationCN() {
        return examinationCN;
    }

    public void setExaminationCN(String examinationCN) {
        this.examinationCN = examinationCN;
    }

    public String getExaminationMN() {
        return examinationMN;
    }

    public void setExaminationMN(String examinationMN) {
        this.examinationMN = examinationMN;
    }

    public String getExaminationON() {
        return examinationON;
    }

    public void setExaminationON(String examinationON) {
        this.examinationON = examinationON;
    }

    public String getNormalPelvis() {
        return normalPelvis;
    }

    public void setNormalPelvis(String normalPelvis) {
        this.normalPelvis = normalPelvis;
    }

    public String getNormalPapanic() {
        return normalPapanic;
    }

    public void setNormalPapanic(String normalPapanic) {
        this.normalPapanic = normalPapanic;
    }

    public String getNormalCervix() {
        return normalCervix;
    }

    public void setNormalCervix(String normalCervix) {
        this.normalCervix = normalCervix;
    }

    public String getVIH() {
        return VIH;
    }

    public void setVIH(String VIH) {
        this.VIH = VIH;
    }

    public Date getVDRL() {
        return VDRL;
    }

    public void setVDRL(Date VDRL) {
        this.VDRL = VDRL;
    }

    public String getVDRLOption() {
        return VDRLOption;
    }

    public void setVDRLOption(String VDRLOption) {
        this.VDRLOption = VDRLOption;
    }  
    

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getSensitized() {
        return sensitized;
    }

    public void setSensitized(String sensitized) {
        this.sensitized = sensitized;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
   
    
    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getUsualWeight() {
        return usualWeight;
    }

    public void setUsualWeight(int usualWeight) {
        this.usualWeight = usualWeight;
    }

    public Date getFUR() {
        return FUR;
    }

    public void setFUR(Date FUR) {
        this.FUR = FUR;
    }

    public Date getFURO() {
        return FURO;
    }

    public void setFURO(Date FURO) {
        this.FURO = FURO;
    }

    public String[] getEstimated() {
        return estimated;
    }

    public void setEstimated(String[] estimated) {
        this.estimated = estimated;
    }

    public String getDoubts() {
        return doubts;
    }

    public void setDoubts(String doubts) {
        this.doubts = doubts;
    }

    public Date getFPBirth() {
        return FPBirth;
    }

    public void setFPBirth(Date FPBirth) {
        this.FPBirth = FPBirth;
    }

    public int getGestationalAge() {
        return gestationalAge;
    }

    public void setGestationalAge(int gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public String getDoubtsHEA() {
        return doubtsHEA;
    }

    public void setDoubtsHEA(String doubtsHEA) {
        this.doubtsHEA = doubtsHEA;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
