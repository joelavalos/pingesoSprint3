/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.viewConsultation;

import entities.Consulta;
import entities.Diagnostico;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class ViewConsultation {
    private String rut="19";
    private String nombre="juan";
    
private Diagnostico diagnoses;
    private Consulta consultation;
    
    @PostConstruct
    public void init(){
        System.out.println("inicio");
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
        System.out.println("holo holo");
        
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

    
}
