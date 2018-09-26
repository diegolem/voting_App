/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego Lemus
 */
@Entity
@Table(name = "cities")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cities.findAll", query = "SELECT c FROM Cities c")
    , @NamedQuery(name = "Cities.findById", query = "SELECT c FROM Cities c WHERE c.id = :id")
    , @NamedQuery(name = "Cities.findByName", query = "SELECT c FROM Cities c WHERE c.name = :name")})
public class Cities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "deparment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Departments deparmentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityId")
    private Collection<CandidatesForCities> candidatesForCitiesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityId")
    private Collection<Headquarters> headquartersCollection;

    public Cities() {
    }

    public Cities(Integer id) {
        this.id = id;
    }

    public Cities(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Departments getDeparmentId() {
        return deparmentId;
    }

    public void setDeparmentId(Departments deparmentId) {
        this.deparmentId = deparmentId;
    }

    @XmlTransient
    public Collection<CandidatesForCities> getCandidatesForCitiesCollection() {
        return candidatesForCitiesCollection;
    }

    public void setCandidatesForCitiesCollection(Collection<CandidatesForCities> candidatesForCitiesCollection) {
        this.candidatesForCitiesCollection = candidatesForCitiesCollection;
    }

    @XmlTransient
    public Collection<Headquarters> getHeadquartersCollection() {
        return headquartersCollection;
    }

    public void setHeadquartersCollection(Collection<Headquarters> headquartersCollection) {
        this.headquartersCollection = headquartersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cities)) {
            return false;
        }
        Cities other = (Cities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.Cities[ id=" + id + " ]";
    }
    
}
