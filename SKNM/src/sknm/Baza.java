/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sknm;

import java.sql.Connection;
/**
 *
 * @author Jacek Cierpka
 */
public class Baza {
    private int Id_Pozycji;
    private String Nazwa_Artysty;
    private String Nazwa_Utworu;
    private String Lokalizacja_Utworu;
    private String Dlugosc;
    private String Tempo;
    
     public Baza(int Bpozycja, String Bartysta, String Butwor, String Blokalizacja, String Bdlugosc, String Btempo)
    {
        this.Id_Pozycji=Bpozycja;
        this.Nazwa_Artysty=Bartysta;
        this.Nazwa_Utworu=Butwor;
        this.Lokalizacja_Utworu=Blokalizacja;
        this.Dlugosc=Bdlugosc;
        this.Tempo=Btempo;
    }
    
    public int getId_Playlisty()
    {
        return Id_Pozycji;
    }
    
    public String getNazwa_Artysty()
    {
        return Nazwa_Artysty;
    }
    
    public String getNazwa_Utworu()
    {
        return Nazwa_Utworu;
    }
    
    public String getLokalizacja_Utworu()
    {
        return  Lokalizacja_Utworu;
    }
    
    public String getDlugosc()
    {
        return Dlugosc;
    }
    public String getTempo()
    {
        return Tempo;
    }
}

    
    

