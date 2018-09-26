/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import sv.edu.udb.www.Utilities;

/**
 *
 * @author Diego Lemus
 */
@Entity
@Table(name = "electoral_process")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "ElectoralProcess.findAll", query = "SELECT e FROM ElectoralProcess e")
    , @NamedQuery(name = "ElectoralProcess.findById", query = "SELECT e FROM ElectoralProcess e WHERE e.id = :id")
    , @NamedQuery(name = "ElectoralProcess.findByCode", query = "SELECT e FROM ElectoralProcess e WHERE e.code = :code")
    , @NamedQuery(name = "ElectoralProcess.findByName", query = "SELECT e FROM ElectoralProcess e WHERE e.name = :name")
    , @NamedQuery(name = "ElectoralProcess.findByYear", query = "SELECT e FROM ElectoralProcess e WHERE e.year = :year")
    , @NamedQuery(name = "ElectoralProcess.findByInitDate", query = "SELECT e FROM ElectoralProcess e WHERE e.initDate = :initDate")
    , @NamedQuery(name = "ElectoralProcess.findByEndDate", query = "SELECT e FROM ElectoralProcess e WHERE e.endDate = :endDate")
    , @NamedQuery(name = "ElectoralProcess.findByProcessDate", query = "SELECT e FROM ElectoralProcess e WHERE e.processDate = :processDate")})
public class ElectoralProcess implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electoralProcessId")
    private Collection<PoliticGroupVotes> politicGroupVotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electoralProcessId")
    private Collection<CandidatesForCities> candidatesForCitiesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electoralProcessId")
    private Collection<CitizenVotes> citizenVotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electoralProcessId")
    private Collection<Jrv> jrvCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "electoralProcessId")
    private Collection<PresidencialCandidates> presidencialCandidatesCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "year")
    private String year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "init_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "process_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processDate;
    @JoinColumn(name = "electoral_process_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ElectoralProcessStatus electoralProcessStatusId;
    @JoinColumn(name = "electoral_process_types_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ElectoralProcessTypes electoralProcessTypesId;

    public ElectoralProcess() {
    }

    public ElectoralProcess(Integer id) {
        this.id = id;
    }

    public ElectoralProcess(Integer id, String code, String name, String year, Date initDate, Date endDate, Date processDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.year = year;
        this.initDate = initDate;
        this.endDate = endDate;
        this.processDate = processDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public ElectoralProcessStatus getElectoralProcessStatusId() {
        return electoralProcessStatusId;
    }

    public void setElectoralProcessStatusId(ElectoralProcessStatus electoralProcessStatusId) {
        this.electoralProcessStatusId = electoralProcessStatusId;
    }

    public ElectoralProcessTypes getElectoralProcessTypesId() {
        return electoralProcessTypesId;
    }

    public void setElectoralProcessTypesId(ElectoralProcessTypes electoralProcessTypesId) {
        this.electoralProcessTypesId = electoralProcessTypesId;
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
        if (!(object instanceof ElectoralProcess)) {
            return false;
        }
        ElectoralProcess other = (ElectoralProcess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.ElectoralProcess[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<PresidencialCandidates> getPresidencialCandidatesCollection() {
        return presidencialCandidatesCollection;
    }

    public void setPresidencialCandidatesCollection(Collection<PresidencialCandidates> presidencialCandidatesCollection) {
        this.presidencialCandidatesCollection = presidencialCandidatesCollection;
    }

    @XmlTransient
    public Collection<PoliticGroupVotes> getPoliticGroupVotesCollection() {
        return politicGroupVotesCollection;
    }

    public void setPoliticGroupVotesCollection(Collection<PoliticGroupVotes> politicGroupVotesCollection) {
        this.politicGroupVotesCollection = politicGroupVotesCollection;
    }

    @XmlTransient
    public Collection<CandidatesForCities> getCandidatesForCitiesCollection() {
        return candidatesForCitiesCollection;
    }

    public void setCandidatesForCitiesCollection(Collection<CandidatesForCities> candidatesForCitiesCollection) {
        this.candidatesForCitiesCollection = candidatesForCitiesCollection;
    }

    @XmlTransient
    public Collection<CitizenVotes> getCitizenVotesCollection() {
        return citizenVotesCollection;
    }

    public void setCitizenVotesCollection(Collection<CitizenVotes> citizenVotesCollection) {
        this.citizenVotesCollection = citizenVotesCollection;
    }

    @XmlTransient
    public Collection<Jrv> getJrvCollection() {
        return jrvCollection;
    }

    public void setJrvCollection(Collection<Jrv> jrvCollection) {
        this.jrvCollection = jrvCollection;
    }
    
    public String initDateFormat(){
        return new SimpleDateFormat("dd-MM-yyyy").format(this.initDate);
    }
    
    public String endDateFormat(){
        return new SimpleDateFormat("dd-MM-yyyy").format(this.endDate);
    }
    
    public String processDateFormat(){
        return new SimpleDateFormat("dd-MM-yyyy").format(this.processDate);
    }

    public boolean avalible(){
        return Utilities.isEquealOrAfterNow(this.processDate);
    }
}
