/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "consentimiento_ges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsentimientoGes.findAll", query = "SELECT c FROM ConsentimientoGes c"),
    @NamedQuery(name = "ConsentimientoGes.findByConsultaid", query = "SELECT c FROM ConsentimientoGes c WHERE c.consentimientoGesPK.consultaid = :consultaid"),
    @NamedQuery(name = "ConsentimientoGes.findByIdCges", query = "SELECT c FROM ConsentimientoGes c WHERE c.consentimientoGesPK.idCges = :idCges"),
    @NamedQuery(name = "ConsentimientoGes.findByConsentimientofecha", query = "SELECT c FROM ConsentimientoGes c WHERE c.consentimientofecha = :consentimientofecha"),
    @NamedQuery(name = "ConsentimientoGes.findByConfirmacionges", query = "SELECT c FROM ConsentimientoGes c WHERE c.confirmacionges = :confirmacionges"),
    @NamedQuery(name = "ConsentimientoGes.findByConfirmaciontratamiento", query = "SELECT c FROM ConsentimientoGes c WHERE c.confirmaciontratamiento = :confirmaciontratamiento")})
public class ConsentimientoGes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsentimientoGesPK consentimientoGesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consentimientofecha")
    @Temporal(TemporalType.DATE)
    private Date consentimientofecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "confirmacionges")
    private boolean confirmacionges;
    @Basic(optional = false)
    @NotNull
    @Column(name = "confirmaciontratamiento")
    private boolean confirmaciontratamiento;
    @JoinColumn(name = "consultaid", referencedColumnName = "consultaid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Consulta consulta;

    public ConsentimientoGes() {
    }

    public ConsentimientoGes(ConsentimientoGesPK consentimientoGesPK) {
        this.consentimientoGesPK = consentimientoGesPK;
    }

    public ConsentimientoGes(ConsentimientoGesPK consentimientoGesPK, Date consentimientofecha, boolean confirmacionges, boolean confirmaciontratamiento) {
        this.consentimientoGesPK = consentimientoGesPK;
        this.consentimientofecha = consentimientofecha;
        this.confirmacionges = confirmacionges;
        this.confirmaciontratamiento = confirmaciontratamiento;
    }

    public ConsentimientoGes(int consultaid, int idCges) {
        this.consentimientoGesPK = new ConsentimientoGesPK(consultaid, idCges);
    }

    public ConsentimientoGesPK getConsentimientoGesPK() {
        return consentimientoGesPK;
    }

    public void setConsentimientoGesPK(ConsentimientoGesPK consentimientoGesPK) {
        this.consentimientoGesPK = consentimientoGesPK;
    }

    public Date getConsentimientofecha() {
        return consentimientofecha;
    }

    public void setConsentimientofecha(Date consentimientofecha) {
        this.consentimientofecha = consentimientofecha;
    }

    public boolean getConfirmacionges() {
        return confirmacionges;
    }

    public void setConfirmacionges(boolean confirmacionges) {
        this.confirmacionges = confirmacionges;
    }

    public boolean getConfirmaciontratamiento() {
        return confirmaciontratamiento;
    }

    public void setConfirmaciontratamiento(boolean confirmaciontratamiento) {
        this.confirmaciontratamiento = confirmaciontratamiento;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consentimientoGesPK != null ? consentimientoGesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsentimientoGes)) {
            return false;
        }
        ConsentimientoGes other = (ConsentimientoGes) object;
        if ((this.consentimientoGesPK == null && other.consentimientoGesPK != null) || (this.consentimientoGesPK != null && !this.consentimientoGesPK.equals(other.consentimientoGesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ConsentimientoGes[ consentimientoGesPK=" + consentimientoGesPK + " ]";
    }
    
}
