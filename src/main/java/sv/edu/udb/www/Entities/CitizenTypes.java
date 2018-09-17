/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "citizen_types")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "CitizenTypes.findAll", query = "SELECT c FROM CitizenTypes c")
    , @NamedQuery(name = "CitizenTypes.findById", query = "SELECT c FROM CitizenTypes c WHERE c.id = :id")
    , @NamedQuery(name = "CitizenTypes.findByDescription", query = "SELECT c FROM CitizenTypes c WHERE c.description = :description")})
public class CitizenTypes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenTypeId")
    private Collection<Citizens> citizensCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenTypeId")
    private Collection<Citizens> citizenCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;

    public CitizenTypes() {
    }

    public CitizenTypes(String id) {
        this.id = id;
    }

    public CitizenTypes(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof CitizenTypes)) {
            return false;
        }
        CitizenTypes other = (CitizenTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.CitizenTypes[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Citizens> getCitizensCollection() {
        return citizensCollection;
    }

    public void setCitizensCollection(Collection<Citizens> citizensCollection) {
        this.citizensCollection = citizensCollection;
    }
    
}
