/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego Lemus
 */
@Entity
@Table(name = "headquarters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Headquarters.findAll", query = "SELECT h FROM Headquarters h")
    , @NamedQuery(name = "Headquarters.findById", query = "SELECT h FROM Headquarters h WHERE h.id = :id")
    , @NamedQuery(name = "Headquarters.findByName", query = "SELECT h FROM Headquarters h WHERE h.name = :name")
    , @NamedQuery(name = "Headquarters.findByX", query = "SELECT h FROM Headquarters h WHERE h.x = :x")
    , @NamedQuery(name = "Headquarters.findByY", query = "SELECT h FROM Headquarters h WHERE h.y = :y")})
public class Headquarters implements Serializable {

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
    @Column(name = "x")
    private String x;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "y")
    private String y;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "headquarterId")
    private Collection<Jrv> jrvCollection;
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cities cityId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "headquarterId")
    private Collection<Citizens> citizensCollection;

    public Headquarters() {
    }

    public Headquarters(Integer id) {
        this.id = id;
    }

    public Headquarters(Integer id, String name, String x, String y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
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

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @XmlTransient
    public Collection<Jrv> getJrvCollection() {
        return jrvCollection;
    }

    public void setJrvCollection(Collection<Jrv> jrvCollection) {
        this.jrvCollection = jrvCollection;
    }

    public Cities getCityId() {
        return cityId;
    }

    public void setCityId(Cities cityId) {
        this.cityId = cityId;
    }

    @XmlTransient
    public Collection<Citizens> getCitizensCollection() {
        return citizensCollection;
    }

    public void setCitizensCollection(Collection<Citizens> citizensCollection) {
        this.citizensCollection = citizensCollection;
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
        if (!(object instanceof Headquarters)) {
            return false;
        }
        Headquarters other = (Headquarters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.Entities.Headquarters[ id=" + id + " ]";
    }
    
}
