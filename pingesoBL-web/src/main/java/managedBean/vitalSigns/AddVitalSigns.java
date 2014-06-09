/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.vitalSigns;

import entities.Persona;
import entities.SignosVitales;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.SignosVitalesFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class AddVitalSigns {

    @EJB
    private SignosVitalesFacadeLocal vitalSignsFacade;
    private List<SignosVitales> searchVitalSigns;
    
    private int idVitalSings;
    private int otherSigns;

    private Persona selectedPerson;
    private String name;
    private String rut;
    private int temperature;
    private int pulse;
    private int height;
    private int diastolicPressure;
    private int systolicPressure;
    private int weight;
    private int satO2;
    

    @PostConstruct
    public void init(){
        searchVitalSigns = vitalSignsFacade.findAll();
    }
    
    public void addVS() {
        System.out.println(selectedPerson.getIdPersona());
        name = selectedPerson.getPersNombres() + " " + selectedPerson.getPersApepaterno() + " " + selectedPerson.getPersApematerno();
        rut = selectedPerson.getPersRut();
    }

    public int getOtherSigns() {
        return otherSigns;
    }

    public void setOtherSigns(int otherSigns) {
        this.otherSigns = otherSigns;
    }

    
    public List<SignosVitales> getSearchVitalSigns() {
        return searchVitalSigns;
    }

    public void setSearchVitalSigns(List<SignosVitales> searchVitalSigns) {
        this.searchVitalSigns = searchVitalSigns;
    }

    
    public int getIdVitalSings() {
        return idVitalSings;
    }

    public void setIdVitalSings(int idVitalSings) {
        this.idVitalSings = idVitalSings;
    }

    
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSatO2() {
        return satO2;
    }

    public void setSatO2(int satO2) {
        this.satO2 = satO2;
    }

    public Persona getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Persona selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

}
