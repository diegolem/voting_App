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
@Table(name = "presidencial_candidates")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "PresidencialCandidates.findAll", query = "SELECT p FROM PresidencialCandidates p")
    , @NamedQuery(name = "PresidencialCandidates.findById", query = "SELECT p FROM PresidencialCandidates p WHERE p.id = :id")})
public class PresidencialCandidates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "electoral_process_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ElectoralProcess electoralProcessId;
    @JoinColumn(name = "candidates_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Candidates candidatesId;

    public PresidencialCandidates() {
    }

    public PresidencialCandidates(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ElectoralProcess getElectoralProcessId() {
        return electoralProcessId;
    }

    public void setElectoralProcessId(ElectoralProcess electoralProcessId) {
        this.electoralProcessId = electoralProcessId;
    }

    public Candidates getCandidatesId() {
        return candidatesId;
    }

    public void setCandidatesId(Candidates candidatesId) {
        this.candidatesId = candidatesId;
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
        if (!(object instanceof PresidencialCandidates)) {
            return false;
        }
        PresidencialCandidates other = (PresidencialCandidates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.PresidencialCandidates[ id=" + id + " ]";
    }
    
}
