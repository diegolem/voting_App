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
@Table(name = "jrv_citizen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JrvCitizen.findAll", query = "SELECT j FROM JrvCitizen j")
    , @NamedQuery(name = "JrvCitizen.findById", query = "SELECT j FROM JrvCitizen j WHERE j.id = :id")})
public class JrvCitizen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Citizens citizenId;
    @JoinColumn(name = "jrv_citizen_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JrvCitizenTypes jrvCitizenTypeId;
    @JoinColumn(name = "jrv_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jrv jrvId;

    public JrvCitizen() {
    }

    public JrvCitizen(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Citizens getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Citizens citizenId) {
        this.citizenId = citizenId;
    }

    public JrvCitizenTypes getJrvCitizenTypeId() {
        return jrvCitizenTypeId;
    }

    public void setJrvCitizenTypeId(JrvCitizenTypes jrvCitizenTypeId) {
        this.jrvCitizenTypeId = jrvCitizenTypeId;
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
        if (!(object instanceof JrvCitizen)) {
            return false;
        }
        JrvCitizen other = (JrvCitizen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.JrvCitizen[ id=" + id + " ]";
    }
    
}
