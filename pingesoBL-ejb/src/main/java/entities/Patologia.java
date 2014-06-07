/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "patologia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patologia.findAll", query = "SELECT p FROM Patologia p"),
    @NamedQuery(name = "Patologia.findByPatologiaid", query = "SELECT p FROM Patologia p WHERE p.patologiaid = :patologiaid"),
    @NamedQuery(name = "Patologia.findByPatologianombre", query = "SELECT p FROM Patologia p WHERE p.patologianombre = :patologianombre"),
    @NamedQuery(name = "Patologia.findByPatologiages", query = "SELECT p FROM Patologia p WHERE p.patologiages = :patologiages")})
public class Patologia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patologiaid")
    private Integer patologiaid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "patologianombre")
    private String patologianombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "patologiages")
    private boolean patologiages;

    public Patologia() {
    }

    public Patologia(Integer patologiaid) {
        this.patologiaid = patologiaid;
    }

    public Patologia(Integer patologiaid, String patologianombre, boolean patologiages) {
        this.patologiaid = patologiaid;
        this.patologianombre = patologianombre;
        this.patologiages = patologiages;
    }

    public Integer getPatologiaid() {
        return patologiaid;
    }

    public void setPatologiaid(Integer patologiaid) {
        this.patologiaid = patologiaid;
    }

    public String getPatologianombre() {
        return patologianombre;
    }

    public void setPatologianombre(String patologianombre) {
        this.patologianombre = patologianombre;
    }

    public boolean getPatologiages() {
        return patologiages;
    }

    public void setPatologiages(boolean patologiages) {
        this.patologiages = patologiages;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patologiaid != null ? patologiaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patologia)) {
            return false;
        }
        Patologia other = (Patologia) object;
        if ((this.patologiaid == null && other.patologiaid != null) || (this.patologiaid != null && !this.patologiaid.equals(other.patologiaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Patologia[ patologiaid=" + patologiaid + " ]";
    }
    
}
