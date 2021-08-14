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
@Table(name = "utwor", catalog = "playlista2", schema = "")
@NamedQueries({
    @NamedQuery(name = "Utwor.findAll", query = "SELECT u FROM Utwor u")
    , @NamedQuery(name = "Utwor.findByNazwaUtworu", query = "SELECT u FROM Utwor u WHERE u.nazwaUtworu = :nazwaUtworu")
    , @NamedQuery(name = "Utwor.findByDlugoscUtworu", query = "SELECT u FROM Utwor u WHERE u.dlugoscUtworu = :dlugoscUtworu")
    , @NamedQuery(name = "Utwor.findByLokalizacjapliku", query = "SELECT u FROM Utwor u WHERE u.lokalizacjapliku = :lokalizacjapliku")})
public class Utwor implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Nazwa_Utworu")
    public String nazwaUtworu;
    @Basic(optional = false)
    @Column(name = "Dlugosc_Utworu")
    public String dlugoscUtworu;
    @Id
    @Basic(optional = false)
    @Column(name = "Lokalizacja_pliku")
    public String lokalizacjapliku;

    public Utwor() {
    }

    public Utwor(String lokalizacjapliku) {
        this.lokalizacjapliku = lokalizacjapliku;
    }

    public Utwor(String lokalizacjapliku, String nazwaUtworu, String dlugoscUtworu) {
        this.lokalizacjapliku = lokalizacjapliku;
        this.nazwaUtworu = nazwaUtworu;
        this.dlugoscUtworu = dlugoscUtworu;
    }

    public String getNazwaUtworu() {
        return nazwaUtworu;
    }

    public void setNazwaUtworu(String nazwaUtworu) {
        String oldNazwaUtworu = this.nazwaUtworu;
        this.nazwaUtworu = nazwaUtworu;
        changeSupport.firePropertyChange("nazwaUtworu", oldNazwaUtworu, nazwaUtworu);
    }

    public String getDlugoscUtworu() {
        return dlugoscUtworu;
    }

    public void setDlugoscUtworu(String dlugoscUtworu) {
        String oldDlugoscUtworu = this.dlugoscUtworu;
        this.dlugoscUtworu = dlugoscUtworu;
        changeSupport.firePropertyChange("dlugoscUtworu", oldDlugoscUtworu, dlugoscUtworu);
    }

    public String getLokalizacjapliku() {
        return lokalizacjapliku;
    }

    public void setLokalizacjapliku(String lokalizacjapliku) {
        String oldLokalizacjapliku = this.lokalizacjapliku;
        this.lokalizacjapliku = lokalizacjapliku;
        changeSupport.firePropertyChange("lokalizacjapliku", oldLokalizacjapliku, lokalizacjapliku);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lokalizacjapliku != null ? lokalizacjapliku.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utwor)) {
            return false;
        }
        Utwor other = (Utwor) object;
        if ((this.lokalizacjapliku == null && other.lokalizacjapliku != null) || (this.lokalizacjapliku != null && !this.lokalizacjapliku.equals(other.lokalizacjapliku))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazwaUtworu +" || "+ dlugoscUtworu+" || "+lokalizacjapliku;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
