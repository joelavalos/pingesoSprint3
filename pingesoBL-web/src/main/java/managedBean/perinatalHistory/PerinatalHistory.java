/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.perinatalHistory;

import entities.Antecedentes;
import entities.Antmedidos;
import entities.Episodios;
import entities.Paciente;
import entities.RegistroClinico;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.AntecedentesFacadeLocal;
import sessionbeans.AntmedidosFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class PerinatalHistory {

    @EJB
    private AntmedidosFacadeLocal antmedidosFacade;
    @EJB
    private EpisodiosFacadeLocal episodeFacade;
    @EJB
    private RegistroClinicoFacadeLocal clinicalRecordFacade;
    @EJB
    private PacienteFacadeLocal patientFacade;
    @EJB
    private PersonaFacadeLocal personFacade;
    @EJB
    private AntecedentesFacadeLocal antecedentesFacade;

    private int personId;
    private Integer Rut = 6972769;
    private String name;
    private List<Paciente> searchPatient;
    private List<RegistroClinico> searchClinicalRecord;
    private List<Episodios> searchEpisode;
    private Paciente patient;

    private List<Antmedidos> listAntMedidos = new ArrayList<Antmedidos>();
    private List<Antecedentes> searchAntecedente;
    private Antmedidos newAntmedido;

    String[] familyHistory;
    String[] personalHistory;
    String reasonAbortion = "";
    String[] bornCheck;
    int deeds = -1;
    int abortions = -1;
    int births = -1;
    int born = -1;
    int stillbirths = -1;
    int living = -1;
    int deadFirstWeek = -1;
    int deadSecondWeek = -1;
    DateFormat dfDateInstance = DateFormat.getDateInstance();
    Date lastPregnancy = null;
    int RNHeavier = -1;

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
    int cantCigars;

    public void save() {
        Date fecha = new Date();
        listAntMedidos.clear();

        personId = personFacade.findByRut(Rut);
        searchPatient = patientFacade.searchByPerson(personId);
        patient = searchPatient.get(0);
        searchClinicalRecord = clinicalRecordFacade.searchByPaciente(searchPatient.get(0));
        searchEpisode = episodeFacade.searchByClinicalRegister(searchClinicalRecord.get(0));

        for (int i = 0; i < familyHistory.length; i++) {
            newAntmedido = new Antmedidos();
            System.out.println(familyHistory[i]);

            searchAntecedente = antecedentesFacade.searchByName(familyHistory[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);

        }

        for (int i = 0; i < personalHistory.length; i++) {
            newAntmedido = new Antmedidos();
            System.out.println(personalHistory[i]);

            searchAntecedente = antecedentesFacade.searchByName(personalHistory[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (deeds > -1) {
            newAntmedido = new Antmedidos();
            System.out.println(deeds);

            searchAntecedente = antecedentesFacade.searchByName("Gestas");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(deeds));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (abortions > -1) {
            newAntmedido = new Antmedidos();
            System.out.println(deeds);

            searchAntecedente = antecedentesFacade.searchByName("Abortos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(abortions));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (births > -1) {
            newAntmedido = new Antmedidos();
            System.out.println(deeds);

            searchAntecedente = antecedentesFacade.searchByName("Partos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(births));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (reasonAbortion.equals("")) {

        } else {
            newAntmedido = new Antmedidos();
            System.out.println(reasonAbortion);

            searchAntecedente = antecedentesFacade.searchByName("Abortos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(reasonAbortion);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        for (int i = 0; i < bornCheck.length; i++) {
            newAntmedido = new Antmedidos();

            System.out.println(bornCheck[i]);

            searchAntecedente = antecedentesFacade.searchByName(bornCheck[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (born > -1) {
            newAntmedido = new Antmedidos();
            System.out.println(deeds);

            searchAntecedente = antecedentesFacade.searchByName("Nacidos vivos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(born));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (stillbirths > -1) {
            newAntmedido = new Antmedidos();
            System.out.println(deeds);

            searchAntecedente = antecedentesFacade.searchByName("Nacidos Muertos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(stillbirths));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (living > -1) {
            newAntmedido = new Antmedidos();
            System.out.println(deeds);

            searchAntecedente = antecedentesFacade.searchByName("Vivos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(living));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (deadFirstWeek > -1) {
            newAntmedido = new Antmedidos();
            System.out.println(deeds);

            searchAntecedente = antecedentesFacade.searchByName("Muertos 1° semana");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(deadFirstWeek));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (deadSecondWeek > -1) {
            newAntmedido = new Antmedidos();
            System.out.println(deeds);

            searchAntecedente = antecedentesFacade.searchByName("Muertos 2° a 4° semana");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(deadSecondWeek));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (lastPregnancy != null) {
            String dateFOrmat = dfDateInstance.format(lastPregnancy);
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Término último embarazo");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(dateFOrmat);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (RNHeavier > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("RN con mayor peso");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(RNHeavier));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        for (int i = 0; i < estimated.length; i++) {
            newAntmedido = new Antmedidos();

            System.out.println(estimated[i]);

            searchAntecedente = antecedentesFacade.searchByName(estimated[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        for (int i = 0; i < HCTOCheck.length; i++) {
            newAntmedido = new Antmedidos();

            System.out.println(HCTOCheck[i]);

            searchAntecedente = antecedentesFacade.searchByName(HCTOCheck[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        System.out.println("Personales: " + listAntMedidos.size());

        for (int j = 0; j < listAntMedidos.size(); j++) {
            antmedidosFacade.create(listAntMedidos.get(j));
        }
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
