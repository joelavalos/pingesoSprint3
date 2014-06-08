/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.Episodes;

import entities.Episodios;
import entities.Paciente;
import entities.RegistroClinico;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
public class Episodes {
    @EJB
    private EpisodiosFacadeLocal episodesFacade;
    @EJB
    private RegistroClinicoFacadeLocal clinicalRecordFacade;
    @EJB
    private PacienteFacadeLocal patientFacade;
    @EJB
    private PersonaFacadeLocal personFacade;

    
    private Integer personId;
    private List<Paciente> searchPatient;
    private List<RegistroClinico> searchClinicalRecord;
    private List<Episodios> searchEpisode;
    private Episodios selectedEpisode;
    
    private String rut;
    private String name;  
   
    
    @PostConstruct
    public void init(){
        rut = "69727697";
        personId = personFacade.findByRut(rut);
        searchPatient = patientFacade.searchByPerson(personId);
        searchClinicalRecord = clinicalRecordFacade.searchByPaciente(searchPatient.get(0));
        searchEpisode = episodesFacade.searchByClinicalRegister(searchClinicalRecord.get(0));
        name = searchPatient.get(0).getPersona().getPersNombres() +" "+searchPatient.get(0).getPersona().getPersApepaterno() 
                +" "+searchPatient.get(0).getPersona().getPersApematerno();
        
       
    }

    
    
//    
//    public Map<String, String> getEpisodes() {
//        return episodes;
//    }
//    public void setEpisodes(Map<String, String> episodes) {
//        this.episodes = episodes;
//    }
    
    
    //    public void selectEpisode(){
////        for(int i=0; i<searchEpisode.size();i++){
////            if(searchEpisode.get(i).getEpisodioid() == episode){
////                episodeSelected = searchEpisode.get(i);
////                break;
////            }
////        }
//    }
//    public Episodios getEpisodeSelected() {
//        return episodeSelected;
//    }
//
//    public void setEpisodeSelected(Episodios episodeSelected) {
//        this.episodeSelected = episodeSelected;
//    }
//    public Map<String, String> getEpisodes() {
//        return episodes;
//    }
//    public void setEpisodes(Map<String, String> episodes) {
//        this.episodes = episodes;
//    }
    
    
    public List<Episodios> getSearchEpisode() {
        return searchEpisode;
    }
    public void setSearchEpisode(List<Episodios> searchEpisode) {
        this.searchEpisode = searchEpisode;
    }
    public Episodios getSelectedEpisode() {
        return selectedEpisode;
    }
    public void setSelectedEpisode(Episodios selectedEpisode) {
        this.selectedEpisode = selectedEpisode;
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
//    public int getEpisode() {
//        return episode;
//    }
//    public void setEpisode(int episode) {
//        this.episode = episode;
//    }
}