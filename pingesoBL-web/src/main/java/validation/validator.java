/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import sessionbeans.PatologiaFacadeLocal;

/**
 *
 * @author Martín
 */
@ManagedBean
@RequestScoped
public class validator {

    private PatologiaFacadeLocal patologiaFacade;
        
    /**
     * Creates a new instance of validator
     */
    public validator() {
    }
    
    public void selectOneEpisode(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        FacesMessage fm = new FacesMessage("Debe seleccionar un episodio");
        String valor = value.toString();
        if(valor.equals("0")){            
            System.out.println("Error de validacion");
            throw new ValidatorException(fm);
        }
    }
    
    
    //Consultation
    public void existentPathology(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        System.out.println("were");
        String valor = value.toString();
        try{
            patologiaFacade.searchByNombre(valor);
        }catch(Exception e){
            FacesMessage fm = new FacesMessage("La patología no existe");
            System.out.println("Error de validacion");
            throw new ValidatorException(fm);
        }
    }
    
    public void selectOneState(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        FacesMessage fm = new FacesMessage("Debe seleccionar un estado");
        String valor = value.toString();
        if(valor.equals("0")){            
            System.out.println("Error de validacion");
            throw new ValidatorException(fm);
        }
    }
    
    public void insertHipothesis(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        FacesMessage fm = new FacesMessage("Debe ingresar una hipótesis");
        String valor = value.toString();
        if(valor.isEmpty()){            
            System.out.println("Error de validacion");
            throw new ValidatorException(fm);
        }
    }
    
    public void insertReason(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        FacesMessage fm = new FacesMessage("Debe ingresar el motivo de la consulta");
        String valor = value.toString();
        if(valor.isEmpty()){            
            System.out.println("Error de validacion");
            throw new ValidatorException(fm);
        }
    }
}
