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
@Table(name = "candidates")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "Candidates.findAll", query = "SELECT c FROM Candidates c")
    , @NamedQuery(name = "Candidates.findById", query = "SELECT c FROM Candidates c WHERE c.id = :id")
    , @NamedQuery(name = "Candidates.findByPhoto", query = "SELECT c FROM Candidates c WHERE c.photo = :photo")})
public class Candidates implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateId")
    private Collection<CandidatesForCities> candidatesForCitiesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatesId")
    private Collection<PresidencialCandidates> presidencialCandidatesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatesId")

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "photo")
    private String photo;
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Citizen citizenId;
    @JoinColumn(name = "politic_group_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PoliticGroups politicGroupId;

    public Candidates() {
    }

    public Candidates(Integer id) {
        this.id = id;
    }

    public Candidates(Integer id, String photo) {
        this.id = id;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Citizen getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Citizen citizenId) {
        this.citizenId = citizenId;
    }

    public PoliticGroups getPoliticGroupId() {
        return politicGroupId;
    }

    public void setPoliticGroupId(PoliticGroups politicGroupId) {
        this.politicGroupId = politicGroupId;
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
        if (!(object instanceof Candidates)) {
            return false;
        }
        Candidates other = (Candidates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.Candidates[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<PresidencialCandidates> getPresidencialCandidatesCollection() {
        return presidencialCandidatesCollection;
    }

    public void setPresidencialCandidatesCollection(Collection<PresidencialCandidates> presidencialCandidatesCollection) {
        this.presidencialCandidatesCollection = presidencialCandidatesCollection;
    }

    @XmlTransient
    public Collection<CandidatesForCities> getCandidatesForCitiesCollection() {
        return candidatesForCitiesCollection;
    }

    public void setCandidatesForCitiesCollection(Collection<CandidatesForCities> candidatesForCitiesCollection) {
        this.candidatesForCitiesCollection = candidatesForCitiesCollection;
    }

    
}
