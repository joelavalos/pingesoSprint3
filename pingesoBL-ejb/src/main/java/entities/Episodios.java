/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "episodios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Episodios.findAll", query = "SELECT e FROM Episodios e"),
    @NamedQuery(name = "Episodios.findByEpisodioid", query = "SELECT e FROM Episodios e WHERE e.episodioid = :episodioid")})
public class Episodios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "episodioid")
    private Integer episodioid;
    @JoinColumn(name = "registroclinicoid", referencedColumnName = "registroclinicoid")
    @ManyToOne(optional = false)
    private RegistroClinico registroclinicoid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "episodioid")
    private Collection<Consulta> consultaCollection;

    public Episodios() {
    }

    public Episodios(Integer episodioid) {
        this.episodioid = episodioid;
    }

    public Integer getEpisodioid() {
        return episodioid;
    }

    public void setEpisodioid(Integer episodioid) {
        this.episodioid = episodioid;
    }

    public RegistroClinico getRegistroclinicoid() {
        return registroclinicoid;
    }

    public void setRegistroclinicoid(RegistroClinico registroclinicoid) {
        this.registroclinicoid = registroclinicoid;
    }

    @XmlTransient
    public Collection<Consulta> getConsultaCollection() {
        return consultaCollection;
    }

    public void setConsultaCollection(Collection<Consulta> consultaCollection) {
        this.consultaCollection = consultaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (episodioid != null ? episodioid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Episodios)) {
            return false;
        }
        Episodios other = (Episodios) object;
        if ((this.episodioid == null && other.episodioid != null) || (this.episodioid != null && !this.episodioid.equals(other.episodioid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Episodios[ episodioid=" + episodioid + " ]";
    }
    
}
