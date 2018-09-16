/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego Lemus
 */
@Entity
@Table(name = "citizen")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "Citizen.findAll", query = "SELECT c FROM Citizen c")
    , @NamedQuery(name = "Citizen.findById", query = "SELECT c FROM Citizen c WHERE c.id = :id")
    , @NamedQuery(name = "Citizen.findByName", query = "SELECT c FROM Citizen c WHERE c.name = :name")
    , @NamedQuery(name = "Citizen.findByLastname", query = "SELECT c FROM Citizen c WHERE c.lastname = :lastname")
    , @NamedQuery(name = "Citizen.findByDui", query = "SELECT c FROM Citizen c WHERE c.dui = :dui")
    , @NamedQuery(name = "Citizen.findByAdress", query = "SELECT c FROM Citizen c WHERE c.adress = :adress")
    , @NamedQuery(name = "Citizen.findByBirthdate", query = "SELECT c FROM Citizen c WHERE c.birthdate = :birthdate")})
public class Citizen implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenId")
    private Collection<CitizenVotes> citizenVotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenId")
    private Collection<JrvCitizen> jrvCitizenCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenId")

    private Collection<Candidates> candidatesCollection;

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
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "dui")
    private String dui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "adress")
    private String adress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @JoinColumn(name = "citizen_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CitizenTypes citizenTypeId;
    @JoinColumn(name = "headquarter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Headquarters headquarterId;

    public Citizen() {
    }

    public Citizen(Integer id) {
        this.id = id;
    }

    public Citizen(Integer id, String name, String lastname, String dui, String adress, Date birthdate) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dui = dui;
        this.adress = adress;
        this.birthdate = birthdate;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public CitizenTypes getCitizenTypeId() {
        return citizenTypeId;
    }

    public void setCitizenTypeId(CitizenTypes citizenTypeId) {
        this.citizenTypeId = citizenTypeId;
    }

    public Headquarters getHeadquarterId() {
        return headquarterId;
    }

    public void setHeadquarterId(Headquarters headquarterId) {
        this.headquarterId = headquarterId;
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
        if (!(object instanceof Citizen)) {
            return false;
        }
        Citizen other = (Citizen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.Citizen[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Candidates> getCandidatesCollection() {
        return candidatesCollection;
    }

    public void setCandidatesCollection(Collection<Candidates> candidatesCollection) {
        this.candidatesCollection = candidatesCollection;
    }

    @XmlTransient
    public Collection<CitizenVotes> getCitizenVotesCollection() {
        return citizenVotesCollection;
    }

    public void setCitizenVotesCollection(Collection<CitizenVotes> citizenVotesCollection) {
        this.citizenVotesCollection = citizenVotesCollection;
    }

    @XmlTransient
    public Collection<JrvCitizen> getJrvCitizenCollection() {
        return jrvCitizenCollection;
    }

    public void setJrvCitizenCollection(Collection<JrvCitizen> jrvCitizenCollection) {
        this.jrvCitizenCollection = jrvCitizenCollection;
    }
    
}
