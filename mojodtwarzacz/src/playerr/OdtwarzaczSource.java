package playerr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jacek Cierpka
 */


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class OdtwarzaczSource 
{
    FileInputStream FIS;
    BufferedInputStream BIS;
    
    public Player player;
    public long pauseLocation;
    public long songtotalLength;
    public String fileLocation;
    
  
    private int Id_Pozycji;
    private String Nazwa_Artysty;
    private String Nazwa_Utworu;
    private String Lokalizacja_Utworu;
    private String Dlugosc;
    private String Tempo;
    private String zacznij;
    private String skoncz;
    
   
     public OdtwarzaczSource(int Bpozycja, String Bartysta, String Butwor, String Blokalizacja, String Bdlugosc, String Btempo)
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
     public String getZacznij() {
        return zacznij;
    }
    public String getSkoncz() {
        return skoncz;
    }
    

     

    
}
