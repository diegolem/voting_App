/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego Lemus
 */
@Entity
@Table(name = "candidates_for_cities")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "CandidatesForCities.findAll", query = "SELECT c FROM CandidatesForCities c")
    , @NamedQuery(name = "CandidatesForCities.findById", query = "SELECT c FROM CandidatesForCities c WHERE c.id = :id")})
public class CandidatesForCities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Candidates candidateId;
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cities cityId;
    @JoinColumn(name = "electoral_process_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ElectoralProcess electoralProcessId;

    public CandidatesForCities() {
    }

    public CandidatesForCities(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidates getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Candidates candidateId) {
        this.candidateId = candidateId;
    }

    public Cities getCityId() {
        return cityId;
    }

    public void setCityId(Cities cityId) {
        this.cityId = cityId;
    }

    public ElectoralProcess getElectoralProcessId() {
        return electoralProcessId;
    }

    public void setElectoralProcessId(ElectoralProcess electoralProcessId) {
        this.electoralProcessId = electoralProcessId;
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
        if (!(object instanceof CandidatesForCities)) {
            return false;
        }
        CandidatesForCities other = (CandidatesForCities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.CandidatesForCities[ id=" + id + " ]";
    }
    
}
