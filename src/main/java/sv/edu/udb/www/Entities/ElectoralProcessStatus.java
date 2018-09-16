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
@Table(name = "electoral_process_status")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "ElectoralProcessStatus.findAll", query = "SELECT e FROM ElectoralProcessStatus e")
    , @NamedQuery(name = "ElectoralProcessStatus.findById", query = "SELECT e FROM ElectoralProcessStatus e WHERE e.id = :id")
    , @NamedQuery(name = "ElectoralProcessStatus.findByDescription", query = "SELECT e FROM ElectoralProcessStatus e WHERE e.description = :description")})
public class ElectoralProcessStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electoralProcessStatusId")
    private Collection<ElectoralProcess> electoralProcessCollection;

    public ElectoralProcessStatus() {
    }

    public ElectoralProcessStatus(Integer id) {
        this.id = id;
    }

    public ElectoralProcessStatus(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<ElectoralProcess> getElectoralProcessCollection() {
        return electoralProcessCollection;
    }

    public void setElectoralProcessCollection(Collection<ElectoralProcess> electoralProcessCollection) {
        this.electoralProcessCollection = electoralProcessCollection;
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
        if (!(object instanceof ElectoralProcessStatus)) {
            return false;
        }
        ElectoralProcessStatus other = (ElectoralProcessStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.ElectoralProcessStatus[ id=" + id + " ]";
    }
    
}
