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
public class ConsentimientoGesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "consultaid")
    private int consultaid;
    @Basic(optional = false)
    @Column(name = "id_cges")
    private int idCges;

    public ConsentimientoGesPK() {
    }

    public ConsentimientoGesPK(int consultaid, int idCges) {
        this.consultaid = consultaid;
        this.idCges = idCges;
    }

    public int getConsultaid() {
        return consultaid;
    }

    public void setConsultaid(int consultaid) {
        this.consultaid = consultaid;
    }

    public int getIdCges() {
        return idCges;
    }

    public void setIdCges(int idCges) {
        this.idCges = idCges;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) consultaid;
        hash += (int) idCges;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsentimientoGesPK)) {
            return false;
        }
        ConsentimientoGesPK other = (ConsentimientoGesPK) object;
        if (this.consultaid != other.consultaid) {
            return false;
        }
        if (this.idCges != other.idCges) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ConsentimientoGesPK[ consultaid=" + consultaid + ", idCges=" + idCges + " ]";
    }
    
}
