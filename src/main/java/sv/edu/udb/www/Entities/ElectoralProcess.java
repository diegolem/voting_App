/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
@NamedQueries({
    @NamedQuery(name = "ElectoralProcess.findAll", query = "SELECT e FROM ElectoralProcess e")
    , @NamedQuery(name = "ElectoralProcess.findById", query = "SELECT e FROM ElectoralProcess e WHERE e.id = :id")
    , @NamedQuery(name = "ElectoralProcess.findPresidencialProcessActive", query = "SELECT DISTINCT e FROM ElectoralProcess e INNER JOIN e.presidencialCandidatesCollection p INNER JOIN p.candidatesId c INNER JOIN c.politicGroupId pg WHERE pg.id != :id AND e.processDate > CURRENT_TIMESTAMP AND e.electoralProcessTypesId.id = 1")
    , @NamedQuery(name = "ElectoralProcess.findByCode", query = "SELECT e FROM ElectoralProcess e WHERE e.code = :code")
    , @NamedQuery(name = "ElectoralProcess.findByName", query = "SELECT e FROM ElectoralProcess e WHERE e.name = :name")
    , @NamedQuery(name = "ElectoralProcess.findByYear", query = "SELECT e FROM ElectoralProcess e WHERE e.year = :year")
    , @NamedQuery(name = "ElectoralProcess.findByInitDate", query = "SELECT e FROM ElectoralProcess e WHERE e.initDate = :initDate")
    , @NamedQuery(name = "ElectoralProcess.findByEndDate", query = "SELECT e FROM ElectoralProcess e WHERE e.endDate = :endDate")
    , @NamedQuery(name = "ElectoralProcess.findAllByEndDateNow", query = "SELECT e FROM ElectoralProcess e WHERE e.processDate > CURRENT_TIMESTAMP")
    , @NamedQuery(name = "ElectoralProcess.findAllByEndDateNowPresidential", query = "SELECT DISTINCT e FROM ElectoralProcess e WHERE e.processDate > CURRENT_TIMESTAMP AND e.electoralProcessTypesId.id = 1")
    , @NamedQuery(name = "ElectoralProcess.findAllByEndDateNowPresidentialBeta", query = "SELECT DISTINCT e FROM ElectoralProcess e WHERE e.processDate > CURRENT_TIMESTAMP AND e.electoralProcessTypesId.id = 1 AND :id != 0")
    , @NamedQuery(name = "ElectoralProcess.findAllByEndDateNowDepartament", query = "SELECT DISTINCT e FROM ElectoralProcess e WHERE e.processDate > CURRENT_TIMESTAMP AND e.electoralProcessTypesId.id = 2")
    , @NamedQuery(name = "ElectoralProcess.findActiveByCandidate", query = "SELECT Distinct e FROM ElectoralProcess e Join Fetch e.candidatesForCitiesCollection c WHERE e.processDate > CURRENT_TIMESTAMP AND c.candidateId.id = :idCandidate")
    , @NamedQuery(name = "ElectoralProcess.findByProcessDate", query = "SELECT e FROM ElectoralProcess e WHERE e.processDate = :processDate")})
public class ElectoralProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
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

    @XmlTransient
    public Collection<PresidencialCandidates> getPresidencialCandidatesCollection() {
        return presidencialCandidatesCollection;
    }

    public void setPresidencialCandidatesCollection(Collection<PresidencialCandidates> presidencialCandidatesCollection) {
        this.presidencialCandidatesCollection = presidencialCandidatesCollection;
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

    public String initDateFormat() {
        return new SimpleDateFormat("dd-MM-yyyy").format(this.initDate);
    }

    public String endDateFormat() {
        return new SimpleDateFormat("dd-MM-yyyy").format(this.endDate);
    }

    public String processDateFormat() {
        return new SimpleDateFormat("dd-MM-yyyy").format(this.processDate);
    }

    public boolean avalible() {
        return Utilities.isEquealOrAfterNow(this.processDate);
    }

    public boolean startProcess() {
        return Utilities.isEquealOrBerofeNow(this.processDate);
    }

    public boolean end() {
        return Utilities.isEquealOrBerofeNow(this.endDate);
    }

    public boolean avalibleUpdate() {
        return Utilities.isEquealOrAfterNow(this.getProcessDate());
    }

    public boolean stepEndInscription() {
        return this.electoralProcessStatusId.getId() == 2;
    }

    public boolean startProcessByType() {
        return this.electoralProcessTypesId.getId() == 2;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.ElectoralProcess[ id=" + id + " ]";
    }

    public boolean typePresidential() {
        return this.electoralProcessTypesId.getId() == 1;
    }

    public boolean typeDepartamental() {
        return this.electoralProcessTypesId.getId() == 2;
    }

    public int porcentage() {
        return this.electoralProcessStatusId.getId() * 25;
    }

    public String iconStep() {
        switch (this.electoralProcessStatusId.getId()) {
            case 1:
                return "clipboard.svg";
            case 2:
                return "politician.svg";
            case 3:
                return "vote.svg";
            case 4:
                return "star.svg";
            default:
                return "";
        }
    }

    public String nameHeadquarter() {
        if (this.jrvCollection != null && this.jrvCollection.size() > 0) {

            List sedes;

            if (this.jrvCollection instanceof List) {
                sedes = (List) this.jrvCollection;
            } else {
                sedes = new ArrayList(this.jrvCollection);
            }

            Jrv jrv = (Jrv) sedes.get(sedes.size() - 1);
            
            return jrv.getHeadquarterId().getName();
        }
        
        return "";
    }
    
    public int idHeadquarter() {
        if (this.jrvCollection != null && this.jrvCollection.size() > 0) {

            List sedes;

            if (this.jrvCollection instanceof List) {
                sedes = (List) this.jrvCollection;
            } else {
                sedes = new ArrayList(this.jrvCollection);
            }

            Jrv jrv = (Jrv) sedes.get(sedes.size() - 1);
            
            return jrv.getHeadquarterId().getId();
        }
        
        return 0;
    }
}
