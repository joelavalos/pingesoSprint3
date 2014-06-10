/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Joel
 */
@Embeddable
public class IpdGesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "consultaid")
    private int consultaid;
    @Basic(optional = false)
    @Column(name = "id_ipd")
    private int idIpd;

    public IpdGesPK() {
    }

    public IpdGesPK(int consultaid, int idIpd) {
        this.consultaid = consultaid;
        this.idIpd = idIpd;
    }

    public int getConsultaid() {
        return consultaid;
    }

    public void setConsultaid(int consultaid) {
        this.consultaid = consultaid;
    }

    public int getIdIpd() {
        return idIpd;
    }

    public void setIdIpd(int idIpd) {
        this.idIpd = idIpd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) consultaid;
        hash += (int) idIpd;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IpdGesPK)) {
            return false;
        }
        IpdGesPK other = (IpdGesPK) object;
        if (this.consultaid != other.consultaid) {
            return false;
        }
        if (this.idIpd != other.idIpd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IpdGesPK[ consultaid=" + consultaid + ", idIpd=" + idIpd + " ]";
    }
    
}
