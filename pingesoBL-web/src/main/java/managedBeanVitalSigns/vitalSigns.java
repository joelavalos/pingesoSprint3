/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanVitalSigns;

import entities.Consulta;
import entities.Episodios;
import entities.Muesta;
import entities.Paciente;
import entities.RegistroClinico;
import entities.SignosVitales;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.MuestaFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;
import sessionbeans.SignosVitalesFacadeLocal;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class vitalSigns {
    @EJB
    private MuestaFacadeLocal muestaFacade;
    @EJB
    private RegistroClinicoFacadeLocal registroClinicoFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private SignosVitalesFacadeLocal signosVitalesFacade;

    private List<SignosVitales> searchVitalSigns;
    private String vitalSignsName;
    private int vitalSignsMinRange;
    private int vitalSignsMaxRange;

    private List<SignosVitales> selectedVitalSign;
    private int vitalSignsId;
    private int vitalSignsValor;

    private List<Paciente> searchPaciente;
    private List<RegistroClinico> searchRegistroClinico;

    private Integer PersonId;
    private String PersonRut = "69727697";

    /**
     * Creates a new instance of vitalSigns
     */
    @PostConstruct
    public void init() {
        searchVitalSigns = signosVitalesFacade.findAll();
    }

    public vitalSigns() {
    }

    public void addVitalSigns() {
        SignosVitales newVitalSigns = new SignosVitales(null, vitalSignsName);
        newVitalSigns.setRangoMin(vitalSignsMinRange);
        newVitalSigns.setRangoMax(vitalSignsMaxRange);

        signosVitalesFacade.create(newVitalSigns);
        searchVitalSigns = signosVitalesFacade.findAll();
        System.out.println("Se ha creado el nuevo signo vital");
    }

    public void createVitalSignsPatients() {
        PersonId = personaFacade.findByRut(PersonRut);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
       
        selectedVitalSign = signosVitalesFacade.searchById(vitalSignsId);
        System.out.println("El id del signo vital es: " + selectedVitalSign.get(0).getNombreSvital());
        System.out.println("El id del registro clinico es: " + searchRegistroClinico.get(0).getRegistroclinicoid());
        
        Date fecha = new Date();
        
        Muesta newMuesta = new Muesta(null, fecha, vitalSignsValor);
        newMuesta.setIdPersona(searchPaciente.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        muestaFacade.create(newMuesta);
        System.out.println("Se ha creado la muestra");
        
    }

    public List<SignosVitales> getSearchVitalSigns() {
        return searchVitalSigns;
    }

    public void setSearchVitalSigns(List<SignosVitales> searchVitalSigns) {
        this.searchVitalSigns = searchVitalSigns;
    }

    public String getVitalSignsName() {
        return vitalSignsName;
    }

    public void setVitalSignsName(String vitalSignsName) {
        this.vitalSignsName = vitalSignsName;
    }

    public int getVitalSignsMinRange() {
        return vitalSignsMinRange;
    }

    public void setVitalSignsMinRange(int vitalSignsMinRange) {
        this.vitalSignsMinRange = vitalSignsMinRange;
    }

    public int getVitalSignsMaxRange() {
        return vitalSignsMaxRange;
    }

    public void setVitalSignsMaxRange(int vitalSignsMaxRange) {
        this.vitalSignsMaxRange = vitalSignsMaxRange;
    }

    public List<SignosVitales> getSelectedVitalSign() {
        return selectedVitalSign;
    }

    public void setSelectedVitalSign(List<SignosVitales> selectedVitalSign) {
        this.selectedVitalSign = selectedVitalSign;
    }

    public int getVitalSignsValor() {
        return vitalSignsValor;
    }

    public void setVitalSignsValor(int vitalSignsValor) {
        this.vitalSignsValor = vitalSignsValor;
    }

    public int getVitalSignsId() {
        return vitalSignsId;
    }

    public void setVitalSignsId(int vitalSignsId) {
        this.vitalSignsId = vitalSignsId;
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

}
