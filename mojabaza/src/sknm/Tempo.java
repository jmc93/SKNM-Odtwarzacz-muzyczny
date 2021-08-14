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
@Table(name = "tempo", catalog = "playlista2", schema = "")
@NamedQueries({
    @NamedQuery(name = "Tempo.findAll", query = "SELECT t FROM Tempo t")
    , @NamedQuery(name = "Tempo.findByTempo", query = "SELECT t FROM Tempo t WHERE t.tempo = :tempo")})
public class Tempo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Tempo")
    public String tempo;

    public Tempo() {
    }

    public Tempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        String oldTempo = this.tempo;
        this.tempo = tempo;
        changeSupport.firePropertyChange("tempo", oldTempo, tempo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tempo != null ? tempo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tempo)) {
            return false;
        }
        Tempo other = (Tempo) object;
        if ((this.tempo == null && other.tempo != null) || (this.tempo != null && !this.tempo.equals(other.tempo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tempo;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
