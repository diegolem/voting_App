/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego Lemus
 */
@Entity
@Table(name = "citizens")
@XmlRootElement
@Named
@SessionScoped
@NamedQueries({
    @NamedQuery(name = "Citizens.findAll", query = "SELECT c FROM Citizens c")
    , @NamedQuery(name = "Citizens.findById", query = "SELECT c FROM Citizens c WHERE c.id = :id")
    , @NamedQuery(name = "Citizens.findByName", query = "SELECT c FROM Citizens c WHERE c.name = :name")
    , @NamedQuery(name = "Citizens.findByLastname", query = "SELECT c FROM Citizens c WHERE c.lastname = :lastname")
    , @NamedQuery(name = "Citizens.findByPassword", query = "SELECT c FROM Citizens c WHERE c.password = :password")
    , @NamedQuery(name = "Citizens.findByDui", query = "SELECT c FROM Citizens c WHERE c.dui = :dui")
    , @NamedQuery(name = "Citizens.findByAdress", query = "SELECT c FROM Citizens c WHERE c.adress = :adress")
    , @NamedQuery(name = "Citizens.findByHeadquarter", query = "SELECT c FROM Citizens c WHERE c.headquarterId.id = :idHeadquarter and c.citizenTypeId.id = 'CITIZN' and c.state = 1")
    , @NamedQuery(name = "Citizens.findByBirthdate", query = "SELECT c FROM Citizens c WHERE c.birthdate = :birthdate")
    , @NamedQuery(name = "Citizens.findAllByDepartament", query = "SELECT c FROM Citizens c where c.headquarterId.cityId.deparmentId.id = :id and c.state = 1 and (c.citizenTypeId.id = 'CITIZN' OR c.citizenTypeId.id = 'PREJRV')")
    , @NamedQuery(name = "Citizens.findByState", query = "SELECT c FROM Citizens c WHERE c.state = :state")})
public class Citizens implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 64)
    @Column(name = "password")
    private String password;
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
    @Column(name = "state")
    private Short state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenId")
    private Collection<CitiesAdmins> citiesAdminsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenId")
    private Collection<CitizenVotes> citizenVotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenId")
    private Collection<Candidates> candidatesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenId")
    private Collection<JrvCitizen> jrvCitizenCollection;
    @JoinColumn(name = "citizen_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CitizenTypes citizenTypeId;
    @JoinColumn(name = "headquarter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Headquarters headquarterId;

    @Transient
    private boolean logged;

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Citizens() {
    }

    public Citizens(Integer id) {
        this.id = id;
    }

    public Citizens(Integer id, String name, String lastname, String dui, String adress, Date birthdate) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    @XmlTransient
    public Collection<CitiesAdmins> getCitiesAdminsCollection() {
        return citiesAdminsCollection;
    }

    public void setCitiesAdminsCollection(Collection<CitiesAdmins> citiesAdminsCollection) {
        this.citiesAdminsCollection = citiesAdminsCollection;
    }

    @XmlTransient
    public Collection<CitizenVotes> getCitizenVotesCollection() {
        return citizenVotesCollection;
    }

    public void setCitizenVotesCollection(Collection<CitizenVotes> citizenVotesCollection) {
        this.citizenVotesCollection = citizenVotesCollection;
    }

    @XmlTransient
    public Collection<Candidates> getCandidatesCollection() {
        return candidatesCollection;
    }

    public void setCandidatesCollection(Collection<Candidates> candidatesCollection) {
        this.candidatesCollection = candidatesCollection;
    }

    @XmlTransient
    public Collection<JrvCitizen> getJrvCitizenCollection() {
        return jrvCitizenCollection;
    }

    public void setJrvCitizenCollection(Collection<JrvCitizen> jrvCitizenCollection) {
        this.jrvCitizenCollection = jrvCitizenCollection;
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
        if (!(object instanceof Citizens)) {
            return false;
        }
        Citizens other = (Citizens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.Citizens[ id=" + id + " ]";
    }

    public boolean isEmpty() {
        boolean result = (this.adress == null || this.adress.isEmpty());
        result &= (this.dui == null || this.dui.isEmpty());
        result &= (this.id == null || this.id == 0);
        result &= (this.lastname == null || this.lastname.isEmpty());
        result &= (this.name == null || this.name.isEmpty());
        result &= (this.password == null || this.password.isEmpty());

        return result;
    }
    
    //Obtener la carpeta segun el tipo de usuario
    public String folderByUserType(){
        switch(this.citizenTypeId.getId()){
            case "ADMGEN":
                return "/generalAdministration";
                
            case "ADMDEP":
                return "/departmentalAdministration";
                
            case "EMRNPN":
                return "/employeeRnpn";
                
            case "PREJRV":
                return "/jrvPresident";
                
            default:
                return "";
        }
    }
    
    public boolean isCitizen(){
        return this.citizenTypeId.getId().equals("CITIZN");
    }
}
