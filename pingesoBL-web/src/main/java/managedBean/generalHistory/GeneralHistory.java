/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.generalHistory;

import entities.Antecedentes;
import entities.Antmedidos;
import entities.Episodios;
import entities.Paciente;
import entities.Persona;
import entities.RegistroClinico;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sessionbeans.AntecedentesFacadeLocal;
import sessionbeans.AntmedidosFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Martín
 */
@ManagedBean
@ViewScoped
public class GeneralHistory {
    @EJB
    private AntecedentesFacadeLocal antecedentesFacade;
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
    
    private Integer Rut = 6972769;
    private String antecedentes;
    private Antmedidos newAntmedido;
    private List<Antecedentes> searchAntecedente;
    private int personId;
    private List<Paciente> searchPatient;
    private Paciente patient;
    private List<RegistroClinico> searchClinicalRecord;
    private List<Episodios> searchEpisode;
    
    public GeneralHistory() {
        
    }
    
    public void save(){
        if(!antecedentes.isEmpty()){
            Date fecha = new Date();
            personId = personFacade.findByRut(Rut);
            searchPatient = patientFacade.searchByPerson(personId);
            patient = searchPatient.get(0);
            searchClinicalRecord = clinicalRecordFacade.searchByPaciente(searchPatient.get(0));
            searchEpisode = episodeFacade.searchByClinicalRegister(searchClinicalRecord.get(0));

            newAntmedido = new Antmedidos();
            searchAntecedente = antecedentesFacade.searchByName("generales");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(antecedentes);
            newAntmedido.setFecha(fecha);
            antmedidosFacade.create(newAntmedido);
            
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Antecedentes guardados.", "");
            FacesContext.getCurrentInstance().addMessage("", fm);
            
            RequestContext.getCurrentInstance().execute("dialogGeneralHistory.hide()");
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un antecedente", "");
            FacesContext.getCurrentInstance().addMessage("", fm);
        }
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }
    
}
