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
@Table(name = "jrv_citizen_types")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "JrvCitizenTypes.findAll", query = "SELECT j FROM JrvCitizenTypes j")
    , @NamedQuery(name = "JrvCitizenTypes.findById", query = "SELECT j FROM JrvCitizenTypes j WHERE j.id = :id")
    , @NamedQuery(name = "JrvCitizenTypes.findByName", query = "SELECT j FROM JrvCitizenTypes j WHERE j.name = :name")
    , @NamedQuery(name = "JrvCitizenTypes.findByDescription", query = "SELECT j FROM JrvCitizenTypes j WHERE j.description = :description")})
public class JrvCitizenTypes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jrvCitizenTypeId")
    private Collection<JrvCitizen> jrvCitizenCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;

    public JrvCitizenTypes() {
    }

    public JrvCitizenTypes(Integer id) {
        this.id = id;
    }

    public JrvCitizenTypes(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
        if (!(object instanceof JrvCitizenTypes)) {
            return false;
        }
        JrvCitizenTypes other = (JrvCitizenTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.JrvCitizenTypes[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<JrvCitizen> getJrvCitizenCollection() {
        return jrvCitizenCollection;
    }

    public void setJrvCitizenCollection(Collection<JrvCitizen> jrvCitizenCollection) {
        this.jrvCitizenCollection = jrvCitizenCollection;
    }
    
}
