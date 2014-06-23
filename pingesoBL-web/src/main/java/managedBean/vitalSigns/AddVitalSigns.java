/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.vitalSigns;

import entities.Muesta;
import entities.Paciente;
import entities.RegistroClinico;
import entities.SignosVitales;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sessionbeans.MuestaFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;
import sessionbeans.SignosVitalesFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class AddVitalSigns {

    @EJB
    private MuestaFacadeLocal sampleFacade;
    @EJB
    private RegistroClinicoFacadeLocal clinicalRecordFacade;
    @EJB
    private PacienteFacadeLocal patientFacade;
    @EJB
    private PersonaFacadeLocal personFacade;
    @EJB
    private SignosVitalesFacadeLocal vitalSignsFacade;

    private List<SignosVitales> searchVitalSigns;
    private String vitalSignsName;
    private int vitalSignsMinRange;
    private int vitalSignsMaxRange;

    private List<SignosVitales> selectedVitalSign;
    private int vitalSignsId;
    private int vitalSignsValue;

    private List<Paciente> searchPatient;
    private List<RegistroClinico> searchClinicalRecord;

    private Integer PersonId;
    private String PersonRut = "69727697";
    private Integer Rut = 6972769;

    private List<Muesta> createSamples = new ArrayList<Muesta>();
    private List<Muesta> createSamplesAlways = new ArrayList<Muesta>();
    int max = 0;

    private int peso;
    private String unitPeso;
    private int maxPeso;
    private int minPeso;

    private int altura;
    private String unitAltura;
    private int maxAltura;
    private int minAltura;

    private int temperatura;
    private String unitTemperatura;
    private int maxTemperatura;
    private int minTemperatura;

    private int saturacion;
    private String unitSaturacion;
    private int maxSaturacion;
    private int minSaturacion;

    private int presionSistolica;
    private String unitPresionSistolica;
    private int maxPresionSistolica;
    private int minPresionSistolica;

    private int presionDiastolica;
    private String unitPresionDiastolica;
    private int maxPresionDiastolica;
    private int minPresionDiastolica;

    private String GeneralUnit;
    private int maxGeneralUnit;
    private int minGeneralUnit;

    private List<SignosVitales> controllerVitalSigns = new ArrayList<SignosVitales>();

    public void returnUnit() {
        for (int i = 0; i < searchVitalSigns.size(); i++) {

            if (searchVitalSigns.get(i).getNombreSvital().equals("Peso")) {
                unitPeso = searchVitalSigns.get(i).getUnidad();
                minPeso = searchVitalSigns.get(i).getRangoMin();
                maxPeso = searchVitalSigns.get(i).getRangoMax();
            }
            if (searchVitalSigns.get(i).getNombreSvital().equals("Altura")) {
                unitAltura = searchVitalSigns.get(i).getUnidad();
                minAltura = searchVitalSigns.get(i).getRangoMin();
                maxAltura = searchVitalSigns.get(i).getRangoMax();
            }
            if (searchVitalSigns.get(i).getNombreSvital().equals("Temperatura")) {
                unitTemperatura = searchVitalSigns.get(i).getUnidad();
                minTemperatura = searchVitalSigns.get(i).getRangoMin();
                maxTemperatura = searchVitalSigns.get(i).getRangoMax();
            }
            if (searchVitalSigns.get(i).getNombreSvital().equals("Saturación O2")) {
                unitSaturacion = searchVitalSigns.get(i).getUnidad();
                minSaturacion = searchVitalSigns.get(i).getRangoMin();
                maxSaturacion = searchVitalSigns.get(i).getRangoMax();
            }
            if (searchVitalSigns.get(i).getNombreSvital().equals("Presión Sistólica")) {
                unitPresionSistolica = searchVitalSigns.get(i).getUnidad();
                minPresionSistolica = searchVitalSigns.get(i).getRangoMin();
                maxPresionSistolica = searchVitalSigns.get(i).getRangoMax();
            }
            if (searchVitalSigns.get(i).getNombreSvital().equals("Presión Diastólica")) {
                unitPresionDiastolica = searchVitalSigns.get(i).getUnidad();
                minPresionDiastolica = searchVitalSigns.get(i).getRangoMin();
                maxPresionDiastolica = searchVitalSigns.get(i).getRangoMax();
            }

        }
    }

    public void returnGeneralUnit() {
        if (vitalSignsId == 0) {
            GeneralUnit = "";
            maxGeneralUnit=0;
            minGeneralUnit=0;            
        } else {
            for (int i = 0; i < searchVitalSigns.size(); i++) {
                if (searchVitalSigns.get(i).getIdSvitales() == vitalSignsId) {
                    GeneralUnit = searchVitalSigns.get(i).getUnidad();
                    maxGeneralUnit = searchVitalSigns.get(i).getRangoMax();
                    minGeneralUnit = searchVitalSigns.get(i).getRangoMin();
                }
            }
        }

    }

    @PostConstruct
    public void init() {
        String[] vitalSignsO = {"Peso", "Altura", "Temperatura", "Saturación O2",
            "Presión Sistólica", "Presión Diastólica"};
        searchVitalSigns = vitalSignsFacade.findAll();
        returnUnit();
        for (int i = 0; i < searchVitalSigns.size(); i++) {
            for (int j = 0; j < vitalSignsO.length; j++) {
                if (searchVitalSigns.get(i).getNombreSvital().equals(vitalSignsO[j])) {
                    searchVitalSigns.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    public void addNewVitalSigns() {
        SignosVitales newVitalSigns = new SignosVitales(null, vitalSignsName);
        newVitalSigns.setRangoMin(vitalSignsMinRange);
        newVitalSigns.setRangoMax(vitalSignsMaxRange);

        vitalSignsFacade.create(newVitalSigns);
        searchVitalSigns = vitalSignsFacade.findAll();
    }

    public void createVitalSignsPatients() {
        PersonId = personFacade.findByRut(Rut);
        searchPatient = patientFacade.searchByPerson(PersonId);
        searchClinicalRecord = clinicalRecordFacade.searchByPaciente(searchPatient.get(0));

        List<Muesta> allSamples = sampleFacade.searchByPatient(searchPatient.get(0));
        max = 0;
        if (allSamples.isEmpty()) {
            max = 0;
        } else {
            for (Muesta allSample : allSamples) {
                if (allSample.getGrupo() > max) {
                    max = allSample.getGrupo();
                } else {
                }
            }
            max++;
        }

        Date fecha = new Date();

        selectedVitalSign = vitalSignsFacade.searchByName("Peso");
        Muesta newMuesta = new Muesta(null);
        newMuesta.setFecha(fecha);
        newMuesta.setValor(peso);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Altura");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(altura);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Temperatura");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(temperatura);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Saturación O2");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(saturacion);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Presión Sistólica");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(presionSistolica);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Presión Diastólica");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(presionDiastolica);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        for (int i = 0; i < createSamplesAlways.size(); i++) {
            if (createSamplesAlways.get(i).getValor() != 0) {
                sampleFacade.create(createSamplesAlways.get(i));
            }
        }

        createSamplesAlways.clear();

        for (Muesta createSample : createSamples) {
            sampleFacade.create(createSample);
        }

        resetVitalSigns();
    }
    
    public void deleteVitalSignal(Muesta deleteSample){
        for(int i = 0; i < createSamples.size(); i++){
            if(deleteSample.getIdSvitales().equals(createSamples.get(i).getIdSvitales())){
                createSamples.remove(i);
                return;
            }
        }
    }

    public void addVitalSignsPatients() {
        boolean exists = false;
        PersonId = personFacade.findByRut(Rut);
        searchPatient = patientFacade.searchByPerson(PersonId);
        selectedVitalSign = vitalSignsFacade.searchById(vitalSignsId);
        List<Muesta> allSamples = sampleFacade.searchByPatient(searchPatient.get(0));
        max = 0;
        if (allSamples.isEmpty()) {
            max = 0;
        } else {
            for (int i = 0; i < allSamples.size(); i++) {
                if (allSamples.get(i).getGrupo() > max) {
                    max = allSamples.get(i).getGrupo();
                } else {

                }
            }
            max++;
        }
        Date fecha = new Date();
        Muesta newMuesta = new Muesta(null);
        newMuesta.setValor(vitalSignsValue);
        newMuesta.setFecha(fecha);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        for (Muesta createSample : createSamples) {
            if (newMuesta.getIdSvitales().equals(createSample.getIdSvitales())) {
                exists = true;
            }
        }
        if(!exists){
            createSamples.add(newMuesta);
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "El signo vital ya está ingresado.", "");
            FacesContext.getCurrentInstance().addMessage("", fm);
        }
        
    }

    public void resetVitalSigns() {
        createSamples.clear();
        max = 0;
        peso = 0;
        altura = 0;
        temperatura = 0;
        saturacion = 0;
        presionSistolica = 0;
        presionDiastolica = 0;
        max = 0;
        vitalSignsId = 0;
        vitalSignsValue = 0;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(int saturacion) {
        this.saturacion = saturacion;
    }

    public int getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(int presionSistolica) {
        this.presionSistolica = presionSistolica;
    }

    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(int presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public List<SignosVitales> getSearchVitalSigns() {
        return searchVitalSigns;
    }

    public void setSearchVitalSigns(List<SignosVitales> searchVitalSigns) {
        this.searchVitalSigns = searchVitalSigns;
    }

    public int getVitalSignsId() {
        return vitalSignsId;
    }

    public void setVitalSignsId(int vitalSignsId) {
        this.vitalSignsId = vitalSignsId;
    }

    public int getVitalSignsValue() {
        return vitalSignsValue;
    }

    public void setVitalSignsValue(int vitalSignsValue) {
        this.vitalSignsValue = vitalSignsValue;
    }

    public List<Muesta> getCreateSamples() {
        return createSamples;
    }

    public void setCreateSamples(List<Muesta> createSamples) {
        this.createSamples = createSamples;
    }

    public String getUnitPeso() {
        return unitPeso;
    }

    public void setUnitPeso(String unitPeso) {
        this.unitPeso = unitPeso;
    }

    public String getUnitAltura() {
        return unitAltura;
    }

    public void setUnitAltura(String unitAltura) {
        this.unitAltura = unitAltura;
    }

    public String getUnitTemperatura() {
        return unitTemperatura;
    }

    public void setUnitTemperatura(String unitTemperatura) {
        this.unitTemperatura = unitTemperatura;
    }

    public String getUnitSaturacion() {
        return unitSaturacion;
    }

    public void setUnitSaturacion(String unitSaturacion) {
        this.unitSaturacion = unitSaturacion;
    }

    public String getUnitPresionSistolica() {
        return unitPresionSistolica;
    }

    public void setUnitPresionSistolica(String unitPresionSistolica) {
        this.unitPresionSistolica = unitPresionSistolica;
    }

    public String getUnitPresionDiastolica() {
        return unitPresionDiastolica;
    }

    public void setUnitPresionDiastolica(String unitPresionDiastolica) {
        this.unitPresionDiastolica = unitPresionDiastolica;
    }

    public String getGeneralUnit() {
        return GeneralUnit;
    }

    public void setGeneralUnit(String GeneralUnit) {
        this.GeneralUnit = GeneralUnit;
    }

    public int getMaxPeso() {
        return maxPeso;
    }

    public void setMaxPeso(int maxPeso) {
        this.maxPeso = maxPeso;
    }

    public int getMinPeso() {
        return minPeso;
    }

    public void setMinPeso(int minPeso) {
        this.minPeso = minPeso;
    }

    public int getMaxAltura() {
        return maxAltura;
    }

    public void setMaxAltura(int maxAltura) {
        this.maxAltura = maxAltura;
    }

    public int getMinAltura() {
        return minAltura;
    }

    public void setMinAltura(int minAltura) {
        this.minAltura = minAltura;
    }

    public int getMaxTemperatura() {
        return maxTemperatura;
    }

    public void setMaxTemperatura(int maxTemperatura) {
        this.maxTemperatura = maxTemperatura;
    }

    public int getMinTemperatura() {
        return minTemperatura;
    }

    public void setMinTemperatura(int minTemperatura) {
        this.minTemperatura = minTemperatura;
    }

    public int getMaxSaturacion() {
        return maxSaturacion;
    }

    public void setMaxSaturacion(int maxSaturacion) {
        this.maxSaturacion = maxSaturacion;
    }

    public int getMinSaturacion() {
        return minSaturacion;
    }

    public void setMinSaturacion(int minSaturacion) {
        this.minSaturacion = minSaturacion;
    }

    public int getMaxPresionSistolica() {
        return maxPresionSistolica;
    }

    public void setMaxPresionSistolica(int maxPresionSistolica) {
        this.maxPresionSistolica = maxPresionSistolica;
    }

    public int getMinPresionSistolica() {
        return minPresionSistolica;
    }

    public void setMinPresionSistolica(int minPresionSistolica) {
        this.minPresionSistolica = minPresionSistolica;
    }

    public int getMaxPresionDiastolica() {
        return maxPresionDiastolica;
    }

    public void setMaxPresionDiastolica(int maxPresionDiastolica) {
        this.maxPresionDiastolica = maxPresionDiastolica;
    }

    public int getMinPresionDiastolica() {
        return minPresionDiastolica;
    }

    public void setMinPresionDiastolica(int minPresionDiastolica) {
        this.minPresionDiastolica = minPresionDiastolica;
    }

    public int getMaxGeneralUnit() {
        return maxGeneralUnit;
    }

    public void setMaxGeneralUnit(int maxGeneralUnit) {
        this.maxGeneralUnit = maxGeneralUnit;
    }

    public int getMinGeneralUnit() {
        return minGeneralUnit;
    }

    public void setMinGeneralUnit(int minGeneralUnit) {
        this.minGeneralUnit = minGeneralUnit;
    }

}
