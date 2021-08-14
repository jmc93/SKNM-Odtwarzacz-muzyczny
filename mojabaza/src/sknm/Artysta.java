/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sknm;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Jacek Cierpka
 */
@Entity
@Table(name = "artysta", catalog = "playlista2", schema = "")
@NamedQueries({
    @NamedQuery(name = "Artysta.findAll", query = "SELECT a FROM Artysta a")
    , @NamedQuery(name = "Artysta.findByNazwaArtysty", query = "SELECT a FROM Artysta a WHERE a.nazwaArtysty = :nazwaArtysty")})
public class Artysta implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Nazwa_Artysty")
    public String nazwaArtysty;

    public Artysta() {
    }

    public Artysta(String nazwaArtysty) {
        this.nazwaArtysty = nazwaArtysty;
    }

    public String getNazwaArtysty() {
        return nazwaArtysty;
    }

    public void setNazwaArtysty(String nazwaArtysty) {
        String oldNazwaArtysty = this.nazwaArtysty;
        this.nazwaArtysty = nazwaArtysty;
        changeSupport.firePropertyChange("nazwaArtysty", oldNazwaArtysty, nazwaArtysty);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nazwaArtysty != null ? nazwaArtysty.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artysta)) {
            return false;
        }
        Artysta other = (Artysta) object;
        if ((this.nazwaArtysty == null && other.nazwaArtysty != null) || (this.nazwaArtysty != null && !this.nazwaArtysty.equals(other.nazwaArtysty))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazwaArtysty;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
