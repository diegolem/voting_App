/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
@Table(name = "jrv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jrv.findAll", query = "SELECT j FROM Jrv j")
    , @NamedQuery(name = "Jrv.existByCode", query = "SELECT count(j) FROM Jrv j where j.code = :code")
    , @NamedQuery(name = "Jrv.existByAnotherCode", query = "SELECT count(j) FROM Jrv j where j.code = :code AND j.id != :id")
    , @NamedQuery(name = "Jrv.findById", query = "SELECT j FROM Jrv j WHERE j.id = :id")
    , @NamedQuery(name = "Jrv.findByCode", query = "SELECT j FROM Jrv j WHERE j.code = :code")})
public class Jrv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "code")
    private String code;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jrvId")
    private Collection<PoliticGroupVotes> politicGroupVotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jrvId")
    private Collection<CitizenVotes> citizenVotesCollection;
    @JoinColumn(name = "electoral_process_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ElectoralProcess electoralProcessId;
    @JoinColumn(name = "headquarter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Headquarters headquarterId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jrvId")
    private Collection<JrvCitizen> jrvCitizenCollection;
    @Transient
    private int total;
    
    private void defaultJrv() {
        this.electoralProcessId = new ElectoralProcess();
        this.headquarterId = new Headquarters();
    }

    public Jrv() {
        defaultJrv();
    }

    public Jrv(Integer id) {
        this.id = id;
    }

    public Jrv(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Collection<PoliticGroupVotes> getPoliticGroupVotesCollection() {
        return politicGroupVotesCollection;
    }

    public void setPoliticGroupVotesCollection(Collection<PoliticGroupVotes> politicGroupVotesCollection) {
        this.politicGroupVotesCollection = politicGroupVotesCollection;
    }

    @XmlTransient
    public Collection<CitizenVotes> getCitizenVotesCollection() {
        return citizenVotesCollection;
    }

    public void setCitizenVotesCollection(Collection<CitizenVotes> citizenVotesCollection) {
        this.citizenVotesCollection = citizenVotesCollection;
    }

    public ElectoralProcess getElectoralProcessId() {
        return electoralProcessId;
    }

    public void setElectoralProcessId(ElectoralProcess electoralProcessId) {
        this.electoralProcessId = electoralProcessId;
    }

    public Headquarters getHeadquarterId() {
        return headquarterId;
    }

    public void setHeadquarterId(Headquarters headquarterId) {
        this.headquarterId = headquarterId;
    }

    @XmlTransient
    public Collection<JrvCitizen> getJrvCitizenCollection() {
        return jrvCitizenCollection;
    }

    public void setJrvCitizenCollection(Collection<JrvCitizen> jrvCitizenCollection) {
        this.jrvCitizenCollection = jrvCitizenCollection;
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
        if (!(object instanceof Jrv)) {
            return false;
        }
        Jrv other = (Jrv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.Jrv[ id=" + id + " ]";
    }

    public boolean canDelete(){
        return (this.politicGroupVotesCollection == null  || this.politicGroupVotesCollection.isEmpty() ) && (this.jrvCitizenCollection == null || this.jrvCitizenCollection.isEmpty()) && (this.citizenVotesCollection == null || this.citizenVotesCollection.isEmpty());
    }
    
    public boolean hasCandidates(){
        return (this.electoralProcessId.getElectoralProcessTypesId().getId() == 1) ? this.electoralProcessId.getPresidencialCandidatesCollection().size() > 0 : this.electoralProcessId.getCandidatesForCitiesCollection().size() > 0;
    }
    
    public boolean hasCandidatesVoting(){
        return (this.electoralProcessId.getElectoralProcessTypesId().getId() == 1) ? this.electoralProcessId.getPresidencialCandidatesCollection().size() > 1 : this.electoralProcessId.getCandidatesForCitiesCollection().size() > 1;
    }
    
    public boolean startVoting(){
        return this.electoralProcessId.getElectoralProcessStatusId().getId() == 3;
    }
    
    public boolean endProcessStep(){
        return this.electoralProcessId.getElectoralProcessStatusId().getId() == 4;
    }
    
    public boolean endVoting(){
        return this.electoralProcessId.end();
    }
    
    public List<PoliticGroupVotes> result(){
        
        List<PoliticGroupVotes> votes = new ArrayList();
        int idProcess = this.electoralProcessId.getId();
        this.total = 0;
        
        for(PoliticGroupVotes vote : this.politicGroupVotesCollection)
            if (vote.getElectoralProcessId().getId() == idProcess)
                this.total += vote.getVotes();
        
        for(PoliticGroupVotes vote : this.politicGroupVotesCollection){
            vote.setPorcentage(((double)vote.getVotes() / (double)this.total) * 100);
            votes.add(vote);
        }
            
        return votes;
        
    }
    
    public int votesUsed(){
        int total = 0;
        
        for(CitizenVotes votes : this.electoralProcessId.getCitizenVotesCollection())
            if (votes.getStatus() == (short)1)
                total++;
        
        return total;
    }
}
