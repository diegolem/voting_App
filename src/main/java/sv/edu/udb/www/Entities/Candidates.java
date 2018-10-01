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
import javax.persistence.Transient;
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
@NamedQueries({
    @NamedQuery(name = "Candidates.findAll", query = "SELECT c FROM Candidates c")
    , @NamedQuery(name = "Candidates.findById", query = "SELECT c FROM Candidates c WHERE c.id = :id")
    , @NamedQuery(name = "Candidates.findByPhoto", query = "SELECT c FROM Candidates c WHERE c.photo = :photo")
    , @NamedQuery(name = "Candidates.findByPoliticGroup", query = "SELECT c FROM Candidates c WHERE c.politicGroupId.id = :id OR :id = 0")})
public class Candidates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "photo")
    private String photo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateId")
    private Collection<CandidatesForCities> candidatesForCitiesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatesId")
    private Collection<PresidencialCandidates> presidencialCandidatesCollection;
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Citizens citizenId;
    @JoinColumn(name = "politic_group_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PoliticGroups politicGroupId;
    @Transient
    private CandidatesForCities electoralProcessActive;
    @Transient
    private PresidencialCandidates presidencialCandidatesActive;
    
    // //////////////////´///////////////////////////////////////
    public CandidatesForCities getElectoralProcessActive() {
        return electoralProcessActive;
    }
    public void setElectoralProcessActive(CandidatesForCities electoralProcessActive) {    
        this.electoralProcessActive = electoralProcessActive;
    }
    public boolean hasElectoralProcessActive(){
        return this.electoralProcessActive != null && this.presidencialCandidatesActive == null;
    }
    public boolean hasElectoralProcessActiveProcess(){
        return hasElectoralProcessActive() && this.electoralProcessActive.getElectoralProcessId().avalibleUpdate();
    }
    public PresidencialCandidates getPresidencialCandidatesActive() {
        return presidencialCandidatesActive;
    }
    
    public void setPresidencialCandidatesActive(PresidencialCandidates presidencialCandidatesActive) {
        this.presidencialCandidatesActive = presidencialCandidatesActive;
    }
    public boolean hasPresidencialCandidatesActive(){
        return this.presidencialCandidatesActive != null && this.electoralProcessActive == null;
    }
     public boolean notHasPresidencialCandidatesActive(){
        return this.presidencialCandidatesActive == null && this.electoralProcessActive == null;
    }
    public boolean hasPresidencialCandidatesProcessActive(){
        return hasPresidencialCandidatesActive() && this.presidencialCandidatesActive.getElectoralProcessId().avalibleUpdate();
    }
    /// //////////////////////////
    public boolean avalible() {
        return true;
    }

    private void defaultVal() {
        this.citizenId = new Citizens();
        this.citizenId.setHeadquarterId(new Headquarters());
        this.setPoliticGroupId(new PoliticGroups());
    }

    public Candidates() {
        defaultVal();
    }

    public Candidates(Integer id) {
        this.id = id;
        defaultVal();
    }

    public Candidates(Integer id, String photo) {
        this.id = id;
        this.photo = photo;
        defaultVal();
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

    @XmlTransient
    public Collection<CandidatesForCities> getCandidatesForCitiesCollection() {
        return candidatesForCitiesCollection;
    }

    public void setCandidatesForCitiesCollection(Collection<CandidatesForCities> candidatesForCitiesCollection) {
        this.candidatesForCitiesCollection = candidatesForCitiesCollection;
    }

    @XmlTransient
    public Collection<PresidencialCandidates> getPresidencialCandidatesCollection() {
        return presidencialCandidatesCollection;
    }

    public void setPresidencialCandidatesCollection(Collection<PresidencialCandidates> presidencialCandidatesCollection) {
        this.presidencialCandidatesCollection = presidencialCandidatesCollection;
    }

    public Citizens getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Citizens citizenId) {
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
}
