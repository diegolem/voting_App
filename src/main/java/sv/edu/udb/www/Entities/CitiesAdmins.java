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
@Table(name = "cities_admins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitiesAdmins.findAll", query = "SELECT c FROM CitiesAdmins c")
    , @NamedQuery(name = "CitiesAdmins.findById", query = "SELECT c FROM CitiesAdmins c WHERE c.id = :id")})
public class CitiesAdmins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Citizens citizenId;
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Departments departmentId;

    public CitiesAdmins() {
    }

    public CitiesAdmins(Integer id) {
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

    public Departments getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Departments departmentId) {
        this.departmentId = departmentId;
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
        if (!(object instanceof CitiesAdmins)) {
            return false;
        }
        CitiesAdmins other = (CitiesAdmins) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.CitiesAdmins[ id=" + id + " ]";
    }
    
}
