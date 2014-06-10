/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.consultation;

import entities.Consulta;
import entities.Diagnostico;
import entities.Episodios;
import entities.Muesta;
import entities.Paciente;
import entities.Patologia;
import entities.RegistroClinico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sessionbeans.ConsultaFacadeLocal;
import sessionbeans.DiagnosticoFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.MuestaFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PatologiaFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class NewConsultation {

    @EJB
    private PatologiaFacadeLocal pathologyFacade;
    @EJB
    private PacienteFacadeLocal patientFacade;
    @EJB
    private PersonaFacadeLocal personFacade;
    @EJB
    private ConsultaFacadeLocal consultationFacade;
    @EJB
    private DiagnosticoFacadeLocal diagnosticFacade;
    @EJB
    private EpisodiosFacadeLocal episodeFacade;
    @EJB
    private RegistroClinicoFacadeLocal clinicalRecordFacade;

    //<--aqui-->
    @EJB
    private MuestaFacadeLocal sampleFacade;
    private List<Diagnostico> allDiagnosesConsultation = new ArrayList<Diagnostico>();
    private List<Diagnostico> allDiagnoses;
    private List<Consulta> searchConsultation;
    private List<Diagnostico> filteredDiagnosesConsultation;
    private List<Diagnostico> selectedDiagnosis;

    private List<Muesta> searchSamples;
    private List<Integer> groups = new ArrayList<Integer>();
    //<!--aqui-->    
    private List<Patologia> pathology;

    private Date diagnosticDate;
    private boolean diagnosticGes;
    private String diagnosticState;
    private String pathologyId;
    private String pathologyName;
    private boolean pathologyGes;

    private int personId;
    private String rut;
    private String name;
    private List<Paciente> searchPatient;
    private List<RegistroClinico> searchClinicalRecord;
    private List<Episodios> searchEpisode;

    private DiagnosesPathology diagPath;
    private List<DiagnosesPathology> diagPathList = new ArrayList<DiagnosesPathology>();
    private Patologia selectedPathology;

    private String diagnosticHipothesis;
    private String consultationReason;
    private boolean consultationCanceled = false;
    private String canceledReason;
    private boolean consultationPaused = false;
    private String consultationNotes;
    private String physicalExamination;
    private boolean pertinence;

    @PostConstruct
    public void init() {
        rut = "69727697";
        personId = personFacade.findByRut(rut);
        searchPatient = patientFacade.searchByPerson(personId);
        name = searchPatient.get(0).getPersona().getPersNombres() + " " + searchPatient.get(0).getPersona().getPersApepaterno()
                + " " + searchPatient.get(0).getPersona().getPersApematerno();
        searchClinicalRecord = clinicalRecordFacade.searchByPaciente(searchPatient.get(0));
        searchEpisode = episodeFacade.searchByClinicalRegister(searchClinicalRecord.get(0));

        //<!--aqui-->
        boolean exist = false;
        int maxGroup = 0;
        searchSamples = sampleFacade.searchByPatient(searchPatient.get(0));
        for (int i = 0; i < searchSamples.size(); i++) {
            for (int j = 0; j < groups.size(); j++) {
                if (groups.get(j) == searchSamples.get(i).getGrupo()) {
                    exist = true;
                }
            }
            if (exist == false) {
                groups.add(searchSamples.get(i).getGrupo());
            }
            exist = false;
            maxGroup = searchSamples.get(i).getGrupo();
        }
        searchSamples = sampleFacade.searchByPatientGroup(searchPatient.get(0), maxGroup);
        //<!--aqui-->
    }

    public void pathologyToAdd() {
        if (selectedPathology != null) {
            pathologyName = selectedPathology.getPatologianombre();
            pathologyGes = selectedPathology.getPatologiages();
            diagnosticGes = selectedPathology.getPatologiages();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Diagnóstico seleccionado", "");
            FacesContext.getCurrentInstance().addMessage("", fm);
            RequestContext.getCurrentInstance().execute("pathologyListDialog.hide()");
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "No ha seleccionado patología", "");
            FacesContext.getCurrentInstance().addMessage("", fm);
        }
    }

    //<!--aqui-->
    public void addAllDiagnostic() {
        allDiagnosesConsultation.clear();
        searchConsultation = consultationFacade.searchByEpisodio(searchEpisode.get(0));
        for (int i = 0; i < searchConsultation.size(); i++) {
            allDiagnoses = diagnosticFacade.searchByConsultation(searchConsultation.get(i));
            for (int j = 0; j < allDiagnoses.size(); j++) {
                allDiagnosesConsultation.add(allDiagnoses.get(j));
            }
        }
    }
    //<!--aqui-->

    public void addDiagnoses() {
        if (pathologyNotEmpty() && pathologyExists() && selectOneState()) {
            diagnosticDate = new Date();
            Patologia diagnosticPathology = pathologyFacade.searchByNombre(pathologyName).get(0);
            pathologyId = diagnosticPathology.getPatologiaid();
            pathologyGes = diagnosticPathology.getPatologiages();
            diagPath = new DiagnosesPathology(diagnosticDate, diagnosticGes, diagnosticState, pathologyId, pathologyName, pathologyGes);
            diagPathList.add(diagPath);
            resetDiagnostic();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Diagnóstico agregado", "");
            FacesContext.getCurrentInstance().addMessage("", fm);
        } else {
            if (!pathologyNotEmpty()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una patología", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            } else if (!pathologyExists()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La patología no existe", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
            if (!selectOneState()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un estado", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
        }
    }

    /////////////////////////*Validación nuevo diagnostico*////////////////////////////
    public boolean pathologyNotEmpty() {
        return !pathologyName.isEmpty();
    }

    public boolean pathologyExists() {
        return !pathologyFacade.searchByNombre(pathologyName).isEmpty();
    }

    public boolean selectOneState() {
        return !diagnosticState.equals("0");
    }
    /////////////////////////*Fin validación nuevo diagnostico*////////////////////////////

    public void addConsultation(boolean canceled, boolean paused) {
        consultationCanceled = canceled;
        consultationPaused = paused;
        if (consultationCanceled) {
            if (!notEmptyHipothesis()) {
                diagnosticHipothesis = "no ingresada.";
            }
            if (!notEmptyReason()) {
                consultationReason = "no ingresado.";
            }
            Date date = new Date();

            Consulta newConsultation = new Consulta(null);

            newConsultation.setEpisodioid(searchEpisode.get(0));
            newConsultation.setHdiagnostica(diagnosticHipothesis);
            newConsultation.setConsultafecha(date);
            newConsultation.setCancelada(consultationCanceled);
            newConsultation.setMotivocancel(canceledReason);
            newConsultation.setPausada(consultationPaused);
            newConsultation.setMotivoConsulta(consultationReason);
            newConsultation.setNotas(consultationNotes);
            newConsultation.setExploracionFisica(physicalExamination);

            consultationFacade.create(newConsultation);

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta cancelada", "");
            FacesContext.getCurrentInstance().addMessage("", fm);

            resetConsultation();
        } else if ((notEmptyHipothesis() && notEmptyReason() && notEmptyDiagnoses())) {
            //System.out.println("Id del episodio: " + searchEpisode.get(0).getEpisodioid());
            //searchConsulta = consultaFacade.searchByEpisodio(searchEpisode.get(0));
            //System.out.println("Hay un total de :" + searchConsultas.size() + " consultas");

            Date date = new Date();

            Consulta newConsultation = new Consulta(null);

            newConsultation.setEpisodioid(searchEpisode.get(0));
            newConsultation.setHdiagnostica(diagnosticHipothesis);
            newConsultation.setConsultafecha(date);
            newConsultation.setCancelada(consultationCanceled);
            newConsultation.setMotivocancel(canceledReason);
            newConsultation.setPausada(consultationPaused);
            newConsultation.setMotivoConsulta(consultationReason);
            newConsultation.setNotas(consultationNotes);
            newConsultation.setExploracionFisica(physicalExamination);

            consultationFacade.create(newConsultation);

            for (DiagnosesPathology diagnostic : diagPathList) {
                Patologia newPatologia = pathologyFacade.searchById(diagnostic.getPathologyId()).get(0);
                Diagnostico newDiagnostico = new Diagnostico(null);
                newDiagnostico.setPatologiaid(newPatologia);
                newDiagnostico.setConsultaid(newConsultation);
                newDiagnostico.setDiagnosticofecha(diagnostic.getDiagnosticDate());
                newDiagnostico.setDiagnosticoges(diagnostic.isDiagnosticGes());
                newDiagnostico.setDiagnosticoestado(diagnostic.getDiagnosticState());
                diagnosticFacade.create(newDiagnostico);
            }

            if (consultationPaused) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta pausada", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consulta guardada exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }

            resetConsultation();
        } else {
            if (!notEmptyHipothesis()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una hipótesis", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
            if (!notEmptyReason()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un motivo para la consulta", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
            if (!notEmptyDiagnoses()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un diagnóstico", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
        }
    }

    /////////////////////////*Validación nueva consulta*////////////////////////////
    public boolean notEmptyHipothesis() {
        return !diagnosticHipothesis.isEmpty();
    }

    public boolean notEmptyReason() {
        return !consultationReason.isEmpty();
    }

    public boolean notEmptyDiagnoses() {
        return !diagPathList.isEmpty();
    }
    /////////////////////////*Fin validación nueva consulta*////////////////////////////

    public void resetConsultation() {
        pathology.clear();
        diagnosticDate = null;
        diagnosticGes = false;
        diagnosticState = "";
        pathologyId = "";
        pathologyName = "";
        pathologyGes = false;
        //searchPatient.clear();
        //searchClinicalRecord.clear();
        //searchEpisode.clear();
        diagPath = null;
        diagPathList = new ArrayList<DiagnosesPathology>();
        selectedPathology = null;
        diagnosticHipothesis = "";
        consultationReason = "";
        consultationCanceled = false;
        canceledReason = "";
        consultationPaused = false;
        consultationNotes = "";
        physicalExamination = "";
        pertinence = false;
        resetDiagnostic();
    }

    public void resetDiagnostic() {
        pathologyName = "";
        diagnosticState = "0";
        diagnosticGes = false;
    }

    public void resetCancel() {
        canceledReason = "";
    }

    public List<String> completeTextPathology(String query) {
        pathology = pathologyFacade.findAll();
        List<String> results = new ArrayList<String>();

        for (Patologia pathology : pathology) {
            if (pathology.getPatologianombre().startsWith(query)) {
                results.add(pathology.getPatologianombre());
                pathologyId = pathology.getPatologiaid();
            }
        }
        return results;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DiagnosesPathology> getDiagPathList() {
        return diagPathList;
    }

    public void setDiagPathList(List<DiagnosesPathology> diagPathList) {
        this.diagPathList = diagPathList;
    }

    public Patologia getSelectedPathology() {
        return selectedPathology;
    }

    public void setSelectedPathology(Patologia selectedPathology) {
        this.selectedPathology = selectedPathology;
    }

    public Date getDiagnosticDate() {
        return diagnosticDate;
    }

    public void setDiagnosticDate(Date diagnosticDate) {
        this.diagnosticDate = diagnosticDate;
    }

    public boolean isDiagnosticGes() {
        return diagnosticGes;
    }

    public void setDiagnosticGes(boolean diagnosticGes) {
        this.diagnosticGes = diagnosticGes;
    }

    public String getDiagnosticState() {
        return diagnosticState;
    }

    public void setDiagnosticState(String diagnosticState) {
        this.diagnosticState = diagnosticState;
    }

    public String getPathologyId() {
        return pathologyId;
    }

    public void setPathologyId(String pathologyId) {
        this.pathologyId = pathologyId;
    }

    public String getPathologyName() {
        return pathologyName;
    }

    public void setPathologyName(String pathologyName) {
        this.pathologyName = pathologyName;
    }

    public boolean isPathologyGes() {
        return pathologyGes;
    }

    public void setPathologyGes(boolean pathologyGes) {
        this.pathologyGes = pathologyGes;
    }

    public String getDiagnosticHipothesis() {
        return diagnosticHipothesis;
    }

    public void setDiagnosticHipothesis(String diagnosticHipothesis) {
        this.diagnosticHipothesis = diagnosticHipothesis;
    }

    public String getConsultationReason() {
        return consultationReason;
    }

    public void setConsultationReason(String consultationReason) {
        this.consultationReason = consultationReason;
    }

    public boolean isConsultationCanceled() {
        return consultationCanceled;
    }

    public void setConsultationCanceled(boolean consultationCanceled) {
        this.consultationCanceled = consultationCanceled;
    }

    public String getCanceledReason() {
        return canceledReason;
    }

    public void setCanceledReason(String canceledReason) {
        this.canceledReason = canceledReason;
    }

    public boolean isConsultationPaused() {
        return consultationPaused;
    }

    public void setConsultationPaused(boolean consultationPaused) {
        this.consultationPaused = consultationPaused;
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

    public boolean isPertinence() {
        return pertinence;
    }

    public void setPertinence(boolean pertinence) {
        this.pertinence = pertinence;
    }

    //<!--aqui-->
    
    public List<Muesta> getSearchSamples() {
        return searchSamples;
    }

    public void setSearchSamples(List<Muesta> searchSamples) {
        this.searchSamples = searchSamples;
    }

    public List<Diagnostico> getSelectedDiagnosis() {
        return selectedDiagnosis;
    }

    public void setSelectedDiagnosis(List<Diagnostico> selectedDiagnosis) {
        this.selectedDiagnosis = selectedDiagnosis;
    }

    public List<Diagnostico> getFilteredDiagnosesConsultation() {
        return filteredDiagnosesConsultation;
    }

    public void setFilteredDiagnosesConsultation(List<Diagnostico> filteredDiagnosesConsultation) {
        this.filteredDiagnosesConsultation = filteredDiagnosesConsultation;
    }

    public List<Diagnostico> getAllDiagnosesConsultation() {
        return allDiagnosesConsultation;
    }

    public void setAllDiagnosesConsultation(List<Diagnostico> allDiagnosesConsultation) {
        this.allDiagnosesConsultation = allDiagnosesConsultation;
    }
    //<!--aqui-->

}
