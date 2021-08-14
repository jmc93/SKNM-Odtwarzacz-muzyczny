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

public class BazaOdtwarzacza {

   
    private Integer idNaOdtwarzaczu;
   
    private String artysta;
   
    private String utwor;
    
    private String lokalizacja;
    
    private String poczatek;
    
    private String koniec;

    public BazaOdtwarzacza() {
    }

public BazaOdtwarzacza(Integer idNaOdtwarzaczu, String artysta, String utwor, String poczatek, String koniec, String lokalizacja) {
        this.idNaOdtwarzaczu = idNaOdtwarzaczu;
        this.artysta = artysta;
        this.utwor = utwor;
        
         this.poczatek = poczatek;
          this.koniec = koniec;
          this.lokalizacja = lokalizacja;
    }

 public Integer getIdNaOdtwarzaczu() {
        return idNaOdtwarzaczu;
    }

    public String getArtysta() {
        return artysta;
    }
   
    public String getUtwor() {
        return utwor;
    }
 
    public String getPoczatek() {
        return poczatek;
    }

    public String getKoniec() {
        return koniec;
    }
    public String getLokalizacja() {
        return lokalizacja;
    }

   
}