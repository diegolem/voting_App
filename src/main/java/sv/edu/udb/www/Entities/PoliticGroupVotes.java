/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego Lemus
 */
@Entity
@Table(name = "politic_group_votes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoliticGroupVotes.findAll", query = "SELECT p FROM PoliticGroupVotes p")
    , @NamedQuery(name = "PoliticGroupVotes.findById", query = "SELECT p FROM PoliticGroupVotes p WHERE p.id = :id")
    , @NamedQuery(name = "PoliticGroupVotes.findByVotes", query = "SELECT p FROM PoliticGroupVotes p WHERE p.votes = :votes")})
public class PoliticGroupVotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "votes")
    private Integer votes;
    @JoinColumn(name = "electoral_process_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ElectoralProcess electoralProcessId;
    @JoinColumn(name = "jrv_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jrv jrvId;
    @JoinColumn(name = "politic_group_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PoliticGroups politicGroupId;

    public PoliticGroupVotes() {
    }

    public PoliticGroupVotes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
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
        if (!(object instanceof PoliticGroupVotes)) {
            return false;
        }
        PoliticGroupVotes other = (PoliticGroupVotes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.PoliticGroupVotes[ id=" + id + " ]";
    }
    
}
