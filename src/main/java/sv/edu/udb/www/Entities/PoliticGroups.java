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
import javax.persistence.Id;
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
@Table(name = "politic_groups")
@XmlRootElement
@Named
@RequestScoped
@NamedQueries({
    @NamedQuery(name = "PoliticGroups.findAll", query = "SELECT p FROM PoliticGroups p")
    , @NamedQuery(name = "PoliticGroups.findAllWithCandidates", query = "SELECT DISTINCT p FROM PoliticGroups p INNER JOIN p.candidatesCollection c")
    , @NamedQuery(name = "PoliticGroups.findById", query = "SELECT p FROM PoliticGroups p WHERE p.id = :id")
    , @NamedQuery(name = "PoliticGroups.findByName", query = "SELECT p FROM PoliticGroups p WHERE p.name = :name")
    , @NamedQuery(name = "PoliticGroups.findByAcronym", query = "SELECT p FROM PoliticGroups p WHERE p.acronym = :acronym")
    , @NamedQuery(name = "PoliticGroups.findByDescription", query = "SELECT p FROM PoliticGroups p WHERE p.description = :description")
    , @NamedQuery(name = "PoliticGroups.findByPhoto", query = "SELECT p FROM PoliticGroups p WHERE p.photo = :photo")})
public class PoliticGroups implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "politicGroupId")
    private Collection<PoliticGroupVotes> politicGroupVotesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "politicGroupId")
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
    @Column(name = "acronym")
    private String acronym;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "photo")
    private String photo;

    public PoliticGroups() {
    }

    public PoliticGroups(Integer id) {
        this.id = id;
    }

    public PoliticGroups(Integer id, String name, String acronym, String description, String photo) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.description = description;
        this.photo = photo;
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        if (!(object instanceof PoliticGroups)) {
            return false;
        }
        PoliticGroups other = (PoliticGroups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.PoliticGroups[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Candidates> getCandidatesCollection() {
        return candidatesCollection;
    }

    public void setCandidatesCollection(Collection<Candidates> candidatesCollection) {
        this.candidatesCollection = candidatesCollection;
    }

    @XmlTransient
    public Collection<PoliticGroupVotes> getPoliticGroupVotesCollection() {
        return politicGroupVotesCollection;
    }

    public void setPoliticGroupVotesCollection(Collection<PoliticGroupVotes> politicGroupVotesCollection) {
        this.politicGroupVotesCollection = politicGroupVotesCollection;
    }

}
