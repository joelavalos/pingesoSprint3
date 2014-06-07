/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.ViewEpisodes;

import entities.Consulta;
import entities.Episodios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.ConsultaFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class ViewEpisodes {
    @EJB
    private ConsultaFacadeLocal consultationFacade;
    
    private Episodios episode;
    private List<Consulta> consultations;
    private List<Consulta> filterConsultations;
    private Consulta consultation;
    
   @PostConstruct
    public void init(){
        consultations = consultationFacade.searchByEpisodio(episode);
//        Date a= new Date();
//        Consulta aux1 = new Consulta(1, "paciente grave",a , false, false, "le dolia un pelo");
//        Consulta aux2 = new Consulta(1, "paciente grave2",a , false, false, "le dolia un pelo2");
//        
//        consultations.add(aux1);
//        consultations.add(aux2);
    }

    public Episodios getEpisode() {
        return episode;
    }

    public void setEpisode(Episodios episode) {
        this.episode = episode;
    }   
    
    public List<Consulta> getFilterConsultations() {
        return filterConsultations;
    }

    public void setFilterConsultations(List<Consulta> filterConsultations) {
        this.filterConsultations = filterConsultations;
    }

    
    public List<Consulta> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consulta> consultations) {
        this.consultations = consultations;
    }

    public Consulta getConsultation() {
        return consultation;
    }

    public void setConsultation(Consulta consultation) {
        this.consultation = consultation;
    }
    
    
}
