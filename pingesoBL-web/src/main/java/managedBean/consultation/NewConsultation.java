/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.consultation;

import entities.Diagnostico;
import entities.Patologia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.PatologiaFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class NewConsultation {
    @EJB
    private PatologiaFacadeLocal pathologyFacade;
    private List<Patologia> pathology;
    
    private Date diagnosticDate;
    private boolean diagnosticGes;
    private String diagnosticState;
    private String pathologyId;
    private String pathologyName;
    private boolean pathologyGes;

    private DiagnosesPathology diagPath;
    private List<DiagnosesPathology> diagPathList = new ArrayList<DiagnosesPathology>();
    private Patologia selectedPathology;

    public void pathologyToAdd() {
        if (selectedPathology != null) {
            pathologyId = selectedPathology.getPatologiaid();
            pathologyName = selectedPathology.getPatologianombre();
        } else {
            System.out.println("Debe seleccionar una patologia");
        }
    }

    public void addDiagnoses() {
        diagnosticDate = new Date();
        diagPath = new DiagnosesPathology(diagnosticDate, diagnosticGes, diagnosticState, pathologyId, pathologyName, pathologyGes);
        diagPathList.add(diagPath);
        diagnosticState = "";
        pathologyId = "";
        pathologyName = "";
        diagnosticGes = false;
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

}
