/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanConsultation;

import entities.Consulta;
import entities.Episodios;
import entities.Paciente;
import entities.RegistroClinico;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.ConsultaFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class showPatientConsultation {
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

    private Integer PersonId;
    private String PersonRut = "69727697";
    
    private String Hdiagnostica;
    private Date date;
    private boolean cancel;
    private String cancelReason;
    private boolean pause;
    private String consultationReason;
    private String notes;
    private String physicalExamination;
    

    /**
     * Creates a new instance of showPatientConsultation
     */
    public showPatientConsultation() {
    }

    public void showConsultation() {
        PersonId = personaFacade.findByRut(PersonRut);
        //System.out.println("Id del paciente: " + PersonId);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        //System.out.println("Paciente estado: " + searchPaciente.get(0).getPaciFallecido());
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
        //System.out.println("Id registro clinico: " + searchRegistroClinico.get(0).getRegistroclinicoid());
        searchEpisode = episodiosFacade.searchByClinicalRegister(searchRegistroClinico.get(0));
        //System.out.println("Id del episodio: " + searchEpisode.get(0).getEpisodioid());
        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        //System.out.println("Hay un total de :" + searchConsultas.size() + " consultas");      
    }
    
    public void createConsultation(){
        PersonId = personaFacade.findByRut(PersonRut);
        //System.out.println("Id del paciente: " + PersonId);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        //System.out.println("Paciente estado: " + searchPaciente.get(0).getPaciFallecido());
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
        //System.out.println("Id registro clinico: " + searchRegistroClinico.get(0).getRegistroclinicoid());
        searchEpisode = episodiosFacade.searchByClinicalRegister(searchRegistroClinico.get(0));
        //System.out.println("Id del episodio: " + searchEpisode.get(0).getEpisodioid());
        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        System.out.println("Hay un total de :" + searchConsultas.size() + " consultas");    
        
        date = new Date();
        cancel = false;
        pause = false;
        
        Consulta newConsultation = new Consulta(null);
        newConsultation.setEpisodioid(searchEpisode.get(0));
        newConsultation.setHdiagnostica(Hdiagnostica);
        newConsultation.setConsultafecha(date);
        newConsultation.setCancelada(cancel);
        newConsultation.setMotivocancel(cancelReason);
        newConsultation.setPausada(pause);
        newConsultation.setMotivoConsulta(consultationReason);
        newConsultation.setNotas(notes);
        newConsultation.setExploracionFisica(physicalExamination);
        
        consultaFacade.create(newConsultation);
        
        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        System.out.println("Se ha creado la consulta");
        
    }

    public RegistroClinicoFacadeLocal getRegistroClinicoFacade() {
        return registroClinicoFacade;
    }

    public void setRegistroClinicoFacade(RegistroClinicoFacadeLocal registroClinicoFacade) {
        this.registroClinicoFacade = registroClinicoFacade;
    }

    public List<Paciente> getSearchPaciente() {
        return searchPaciente;
    }

    public void setSearchPaciente(List<Paciente> searchPaciente) {
        this.searchPaciente = searchPaciente;
    }

    public List<RegistroClinico> getSearchRegistroClinico() {
        return searchRegistroClinico;
    }

    public void setSearchRegistroClinico(List<RegistroClinico> searchRegistroClinico) {
        this.searchRegistroClinico = searchRegistroClinico;
    }

    public List<Episodios> getSearchEpisode() {
        return searchEpisode;
    }

    public void setSearchEpisode(List<Episodios> searchEpisode) {
        this.searchEpisode = searchEpisode;
    }

    public List<Consulta> getSearchConsultas() {
        return searchConsultas;
    }

    public void setSearchConsultas(List<Consulta> searchConsultas) {
        this.searchConsultas = searchConsultas;
    }

    public Integer getPersonId() {
        return PersonId;
    }

    public void setPersonId(Integer PersonId) {
        this.PersonId = PersonId;
    }

    public String getPersonRut() {
        return PersonRut;
    }

    public void setPersonRut(String PersonRut) {
        this.PersonRut = PersonRut;
    }

    public String getHdiagnostica() {
        return Hdiagnostica;
    }

    public void setHdiagnostica(String Hdiagnostica) {
        this.Hdiagnostica = Hdiagnostica;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public String getConsultationReason() {
        return consultationReason;
    }

    public void setConsultationReason(String consultationReason) {
        this.consultationReason = consultationReason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

}
