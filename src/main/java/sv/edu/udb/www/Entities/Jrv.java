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
@Table(name = "jrv")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "Jrv.findAll", query = "SELECT j FROM Jrv j")
    , @NamedQuery(name = "Jrv.findById", query = "SELECT j FROM Jrv j WHERE j.id = :id")
    , @NamedQuery(name = "Jrv.findByCode", query = "SELECT j FROM Jrv j WHERE j.code = :code")})
public class Jrv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    public Jrv() {
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
    
}
