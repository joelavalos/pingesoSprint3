/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.vitalSigns;

import entities.Muesta;
import entities.Paciente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessionbeans.MuestaFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@SessionScoped
public class viewVitalSigns {

    @EJB
    private PacienteFacadeLocal patientFacade;
    @EJB
    private PersonaFacadeLocal personFacade;
    @EJB
    private MuestaFacadeLocal muestaFacade;

    private List<Paciente> searchPaciente;
    private List<Muesta> searchSamples;
    private Integer PersonId;
    private String PersonRut= "69727697";
    private Integer personID;
    private int samplesId;
    private List<Integer> groups = new ArrayList<Integer>();

    @PostConstruct
    public void init() {
        PersonId = personFacade.findByRut(PersonRut);
        searchPaciente = patientFacade.searchByPerson(PersonId);
        //Date fecha = new Date(1990, 17, 9);
        searchSamples = muestaFacade.searchByPatient(searchPaciente.get(0));
        boolean exists = false;
        int maxGroup = 0;

        for (int i = 0; i < searchSamples.size(); i++) {
            for (int j = 0; j < groups.size(); j++) {
                if (groups.get(j) == searchSamples.get(i).getGrupo()) {
                    exists = true;
                }
            }
            if (exists == false) {
                groups.add(searchSamples.get(i).getGrupo());
            }
            exists = false;
            maxGroup = searchSamples.get(i).getGrupo();
        }
        searchSamples = muestaFacade.searchByPatientGroup(searchPaciente.get(0), maxGroup);
    }

    public void showSamples() {
        PersonId = personFacade.findByRut(PersonRut);
        searchPaciente = patientFacade.searchByPerson(PersonId);
        searchSamples = muestaFacade.searchByPatientGroup(searchPaciente.get(0), samplesId);
        System.out.println("Fecha seleccionada: " + samplesId);

    }
}
