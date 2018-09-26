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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "citizen_votes")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "CitizenVotes.findAll", query = "SELECT c FROM CitizenVotes c")
    , @NamedQuery(name = "CitizenVotes.findById", query = "SELECT c FROM CitizenVotes c WHERE c.id = :id")
    , @NamedQuery(name = "CitizenVotes.findByStatus", query = "SELECT c FROM CitizenVotes c WHERE c.status = :status")})
public class CitizenVotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name = "status")
    private Short status;
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Citizens citizenId;
    @JoinColumn(name = "electoral_process_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ElectoralProcess electoralProcessId;
    @JoinColumn(name = "jrv_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jrv jrvId;

    public CitizenVotes() {
    }

    public CitizenVotes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Citizens getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Citizens citizenId) {
        this.citizenId = citizenId;
    }

    public ElectoralProcess getElectoralProcessId() {
        return electoralProcessId;
    }

    public void setElectoralProcessId(ElectoralProcess electoralProcessId) {
        this.electoralProcessId = electoralProcessId;
    }

    public Jrv getJrvId() {
        return jrvId;
    }

    public void setJrvId(Jrv jrvId) {
        this.jrvId = jrvId;
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
        if (!(object instanceof CitizenVotes)) {
            return false;
        }
        CitizenVotes other = (CitizenVotes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.CitizenVotes[ id=" + id + " ]";
    }
    
}
