/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.viewConsultation;

import entities.Consulta;
import entities.Diagnostico;
import entities.Paciente;
import entities.Patologia;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import managedBean.consultation.DiagnosesPathology;
import sessionbeans.DiagnosticoFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PatologiaFacadeLocal;
import sessionbeans.PersonaFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class ViewConsultation {
    @EJB
    private DiagnosticoFacadeLocal diagnosisFacade;
    @EJB
    private PersonaFacadeLocal personFacade;
    @EJB
    private PacienteFacadeLocal patientFacade;
    
    private String rut;
    private String nombre;
    private Diagnostico selectedDiagnosis;
    private String consultationReason;
    private String consultationNotes;
    private String physicalExamination;
    private String diagnosisHipothesis;
    private List<Diagnostico> diagnosis;
    private List<DiagnosesPathology> diagPathList = new ArrayList<DiagnosesPathology>();
    private boolean pertinence;
    
    private List<Paciente> searchPatient;
    
private Diagnostico diagnoses;
    private Consulta consultation;
    
    @PostConstruct
    public void init(){
        rut = "69727697";
        searchPatient = patientFacade.searchByPerson(personFacade.findByRut(rut));
        nombre = searchPatient.get(0).getPersona().getPersNombres() + " " + searchPatient.get(0).getPersona().getPersApepaterno()
                + " " + searchPatient.get(0).getPersona().getPersApematerno();
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void completeDataConsultation(){
        Consulta selectedConsultation = selectedDiagnosis.getConsultaid();
        consultationReason = selectedConsultation.getMotivoConsulta();
        consultationNotes = selectedConsultation.getNotas();
        physicalExamination = selectedConsultation.getExploracionFisica();
        diagnosisHipothesis = selectedConsultation.getHdiagnostica();
        //pertinence = selectedConsultation.getPertinence();
        diagnosis = diagnosisFacade.searchByConsultation(selectedConsultation);
        for(Diagnostico diag: diagnosis){
            diagPathList.add(new DiagnosesPathology(diag.getDiagnosticofecha(), diag.getDiagnosticoges(), diag.getDiagnosticoestado(), diag.getPatologiaid().getPatologiaid(), diag.getPatologiaid().getPatologianombre(), diag.getPatologiaid().getPatologiages()));
        }
    }
    
    public Diagnostico getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Diagnostico diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Consulta getConsultation() {
        return consultation;
    }

    public void setConsultation(Consulta consultation) {
        this.consultation = consultation;
    }

    public Diagnostico getSelectedDiagnosis() {
        return selectedDiagnosis;
    }

    public void setSelectedDiagnosis(Diagnostico selectedDiagnosis) {
        this.selectedDiagnosis = selectedDiagnosis;
    }

    public String getConsultationReason() {
        return consultationReason;
    }

    public void setConsultationReason(String consultationReason) {
        this.consultationReason = consultationReason;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    public String getDiagnosisHipothesis() {
        return diagnosisHipothesis;
    }

    public void setDiagnosisHipothesis(String diagnosisHipothesis) {
        this.diagnosisHipothesis = diagnosisHipothesis;
    }

    public List<DiagnosesPathology> getDiagPathList() {
        return diagPathList;
    }

    public void setDiagPathList(List<DiagnosesPathology> diagPathList) {
        this.diagPathList = diagPathList;
    }

    public boolean isPertinence() {
        return pertinence;
    }

    public void setPertinence(boolean pertinence) {
        this.pertinence = pertinence;
    }

    public List<Diagnostico> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<Diagnostico> diagnosis) {
        this.diagnosis = diagnosis;
    }

    
}
