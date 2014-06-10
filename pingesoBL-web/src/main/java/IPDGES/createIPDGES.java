/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IPDGES;

import entities.Consulta;
import entities.Episodios;
import entities.IpdGes;
import entities.Paciente;
import entities.Patologia;
import entities.RegistroClinico;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.ConsultaFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.IpdGesFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class createIPDGES {
    @EJB
    private IpdGesFacadeLocal ipdGesFacade;
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private EpisodiosFacadeLocal episodiosFacade;
    @EJB
    private RegistroClinicoFacadeLocal registroClinicoFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;

    private List<Paciente> searchPaciente;
    private List<RegistroClinico> searchRegistroClinico;
    private List<Episodios> searchEpisode;
    private List<Consulta> searchConsultas;
    private List<Patologia> searchPathology;

    private Integer PersonId;
    private String PersonRut = "69727697";
    
    
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
        PersonId = personaFacade.findByRut(PersonRut);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
        searchEpisode = episodiosFacade.searchByClinicalRegister(searchRegistroClinico.get(0));

        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        
        IpdGes newIPDGES = new IpdGes(null);
        newIPDGES.setConsultaid(searchConsultas.get(0));
        newIPDGES.setProblemaauge(augeProblem);
        newIPDGES.setSubproblemaauge(augeSubProblem);
        newIPDGES.setDiagnostico(diagnosis);
        newIPDGES.setFundamentodiagnostico(fundamentDiagnosis);
        newIPDGES.setTratamientoind(treatment);
        newIPDGES.setConfirmages(confirmsGES);
        newIPDGES.setFechalimite(deadline);
        
        ipdGesFacade.create(newIPDGES);
        
        augeProblem = "";
        augeSubProblem = "";
        diagnosis = "";
        fundamentDiagnosis = "";
        treatment = "";
        confirmsGES = false;
        deadline = null;
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
