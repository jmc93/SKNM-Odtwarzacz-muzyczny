package playerr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sknm.OknoBazy;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *
 * @author Jacek Cierpka
 */

public class Odtwarzacz extends javax.swing.JFrame {

   
    FileInputStream FIS;
    BufferedInputStream BIS;
    
    public AdvancedPlayer player;
    public Player player2;
    
    public long pauseLocation;
    public long songtotalLength;
    public long calkowityLength;
    public long poczatekLocation;
    public int poczatekLocation2;
    public int rozpocznij;
    public int rozpocznijFrame;
    public int rozpocznijOdt;
    public int zakoncz;
    public int zakonczFrame;
    public int zakonczOdt;
    public int advpoczatekLocation;
    public long koniecLocation;
    public int koniecLocation2;
    public int advkoniecLocation;
    public long aktualnaLocation;
    public String fileLocation;
    public String fileLocationOdtwarzacz;
   
    
    
    /**
     * Creates new form Odtwarzacz
     */
    
    public Odtwarzacz() {
        initComponents();
        getConnection();
        Wyswietl_Rekordy();
        Wyswietl_Odtwarzacz();
    }
    
    int pozycja = 0;
    public Connection getConnection(){
    
            Connection polaczenie=null;
        try {
            polaczenie= DriverManager.getConnection("jdbc:mysql://localhost/playlista2","root","");
            return polaczenie;
        } catch (SQLException ex) {
            Logger.getLogger(Odtwarzacz.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Odrzucono Polaczenie");
            return null;
        }
            
    }
    
      public ArrayList<OdtwarzaczSource> pobierzPlayliste()
    {
            ArrayList<OdtwarzaczSource> listaPlaylisty  = new ArrayList<OdtwarzaczSource>();
            Connection polaczenie = getConnection();
            String pytanie = "SELECT * FROM playlista";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = polaczenie.createStatement();
            rs = st.executeQuery(pytanie);
            OdtwarzaczSource playlista;
            
            while(rs.next())
            {
                playlista = new OdtwarzaczSource(rs.getInt("Id_Na_Playliscie"),rs.getString("Nazwa_Artysty"),rs.getString("Nazwa_Utworu"),rs.getString("Lokalizacja_pliku"),rs.getString("Dlugosc_Utworu"),rs.getString("Tempo"));
                listaPlaylisty.add(playlista);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Odtwarzacz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  listaPlaylisty; 
                
    }
    public ArrayList<BazaOdtwarzacza> pobierzOdtwarzaczPlayliste()
    {
    ArrayList<BazaOdtwarzacza> listaOdtwarzacza  = new ArrayList<BazaOdtwarzacza>();
            Connection polaczenie = getConnection();
            String pytanie = "SELECT * FROM odtwarzacz_baza";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = polaczenie.createStatement();
            rs = st.executeQuery(pytanie);
            BazaOdtwarzacza playlistaodtwarzacza;
            
            while(rs.next())
            {
                playlistaodtwarzacza = new BazaOdtwarzacza(rs.getInt("Id_Na_Odtwarzaczu"),rs.getString("Artysta"),rs.getString("Utwor"),rs.getString("Poczatek"),rs.getString("Koniec"),rs.getString("Lokalizacja"));
                listaOdtwarzacza.add(playlistaodtwarzacza);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Odtwarzacz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaOdtwarzacza; 
                
    }
    
    public void Wyswietl_Odtwarzacz()
    {
        ArrayList<BazaOdtwarzacza> lista = pobierzOdtwarzaczPlayliste();
        DefaultTableModel model = (DefaultTableModel)zawartosc_odtwarzacza.getModel();
        
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i = 0; i < lista.size(); i++)
        {
            row[0] = lista.get(i).getIdNaOdtwarzaczu();
            row[1] = lista.get(i).getArtysta();
            row[2] = lista.get(i).getUtwor();
            row[3] = lista.get(i).getPoczatek();
            row[4] = lista.get(i).getKoniec();
            row[5] = lista.get(i).getLokalizacja();
            model.addRow(row);
        }
    
    }
    public void Wyswietl_Rekordy()
    {
        ArrayList<OdtwarzaczSource> lista = pobierzPlayliste();
        DefaultTableModel model = (DefaultTableModel)zawartosc_tabeli.getModel();
        
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i = 0; i < lista.size(); i++)
        {
            row[0] = lista.get(i).getId_Playlisty();
            row[1] = lista.get(i).getNazwa_Artysty();
            row[2] = lista.get(i).getNazwa_Utworu();
            row[3] = lista.get(i).getDlugosc();
            row[4] = lista.get(i).getTempo();
            row[5] = lista.get(i).getLokalizacja_Utworu();
            model.addRow(row);
        }
    
    } 

   
     public void Wyswietl_Zawartosc(int index)
    {
        pole_artysta.setText(pobierzPlayliste().get(index).getNazwa_Artysty());
        pole_utwor.setText(pobierzPlayliste().get(index).getNazwa_Utworu());
        pokaz_adres.setText(pobierzPlayliste().get(index).getLokalizacja_Utworu());
           
    } 
    public void Wyswietl_Odtwarzacz(int indexOdt)
    {
        aktualna_lokalizacja.setText(pobierzOdtwarzaczPlayliste().get(indexOdt).getLokalizacja());
        pocz.setText(pobierzOdtwarzaczPlayliste().get(indexOdt).getPoczatek());
        koncz.setText(pobierzOdtwarzaczPlayliste().get(indexOdt).getKoniec());
    }
     
     public void Play(String path)
    {
        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
            player = new AdvancedPlayer(BIS);
            songtotalLength = FIS.available();
            calkowityLength=songtotalLength;
            System.out.println(songtotalLength);
            fileLocation = path +"";
        } 
        catch (FileNotFoundException | JavaLayerException ex) 
        {
          
        } catch (IOException ex) {
            Logger.getLogger(OdtwarzaczSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
                @Override
                public void run()
                {
                    
                    try {
                        player.play();
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(Odtwarzacz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                };
        }.start();
    }
      public void Stop()
    {
        if(player != null){
        player.close();
        pauseLocation = 0;
        songtotalLength = 0;
        }
    }
    public void Pause()
    {
       
        if(player != null){
            try {
                pauseLocation = FIS.available();
                System.out.println(pauseLocation);
                player.close();
            } catch (IOException ex) {
                
            }
        }
    }
    
    public void Resume()
    {
        try {
            FIS = new FileInputStream(fileLocation);
            BIS = new BufferedInputStream(FIS);                    
            player = new AdvancedPlayer(BIS);
            FIS.skip(songtotalLength - pauseLocation);
                                             
        }  catch (JavaLayerException ex) 
        {
            
        } catch (IOException ex) {
            Logger.getLogger(OdtwarzaczSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
                @Override
                public void run()
                {
                    try {
                       
                        player.play();
                           
                    } catch (JavaLayerException ex) {
                        
                    } 
                };
        }.start();
                
        
    }
    public void GrajFragment(String path)
    {
            rozpocznijOdt=Integer.parseInt(pocz.getText());
            zakonczOdt=Integer.parseInt(koncz.getText());
            System.out.println(rozpocznijOdt+" "+" "+zakonczOdt);
            rozpocznijFrame=39*rozpocznijOdt;
            zakonczFrame=39*zakonczOdt;
            try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
            player = new AdvancedPlayer(BIS);
            }  catch (JavaLayerException ex) 
        {
            
        } catch (IOException ex) {
            Logger.getLogger(OdtwarzaczSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
                @Override
                public void run()
                {
                    try {
                        System.out.println("wskoczylo");
                        System.out.println(poczatekLocation2);
                        System.out.println(koniecLocation2);
                        if(pocz != null && koncz != null)
                        player.play(rozpocznijFrame, zakonczFrame);
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(Odtwarzacz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                };
        }.start();
              
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playlista2PUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("playlista2PU").createEntityManager();
        jPanel1 = new javax.swing.JPanel();
        guz_add = new javax.swing.JButton();
        guz_pause = new javax.swing.JButton();
        guz_play = new javax.swing.JButton();
        guz_stop = new javax.swing.JButton();
        Display = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        zawartosc_tabeli = new javax.swing.JTable();
        pole_artysta = new javax.swing.JTextField();
        pole_utwor = new javax.swing.JTextField();
        pokaz_adres = new javax.swing.JTextField();
        guz_wyczysc = new javax.swing.JButton();
        bazaa = new javax.swing.JButton();
        zacznij = new javax.swing.JTextField();
        skoncz = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        zawartosc_odtwarzacza = new javax.swing.JTable();
        aktualna_lokalizacja = new javax.swing.JTextField();
        pocz = new javax.swing.JTextField();
        koncz = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(1484, 510));

        guz_add.setText("Dodaj");
        guz_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guz_addActionPerformed(evt);
            }
        });

        guz_pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikony/icons/pause-icon.png"))); // NOI18N
        guz_pause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                guz_pauseMouseReleased(evt);
            }
        });

        guz_play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikony/icons/play-icon.png"))); // NOI18N
        guz_play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                guz_playMouseReleased(evt);
            }
        });

        guz_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ikony/icons/stop-icon.png"))); // NOI18N
        guz_stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                guz_stopMouseReleased(evt);
            }
        });
        guz_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guz_stopActionPerformed(evt);
            }
        });

        zawartosc_tabeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nr", "Artysta", "Utwor", "Dlugosc", "Tempo", "Adres Pliku"
            }
        ));
        zawartosc_tabeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zawartosc_tabeliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(zawartosc_tabeli);

        pole_artysta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pole_artystaActionPerformed(evt);
            }
        });

        guz_wyczysc.setText("Wyczysc");
        guz_wyczysc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guz_wyczyscActionPerformed(evt);
            }
        });

        bazaa.setText("Baza");
        bazaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bazaaActionPerformed(evt);
            }
        });

        zacznij.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zacznijActionPerformed(evt);
            }
        });

        skoncz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skonczActionPerformed(evt);
            }
        });

        zawartosc_odtwarzacza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Artysta", "Utwor", "Poczatek", "Koniec", "Lokalizacja"
            }
        ));
        zawartosc_odtwarzacza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zawartosc_odtwarzaczaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(zawartosc_odtwarzacza);

        pocz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poczActionPerformed(evt);
            }
        });

        koncz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                konczActionPerformed(evt);
            }
        });

        jLabel1.setText("Początek [s]: ");

        jLabel2.setText("Koniec [s]:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(aktualna_lokalizacja, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pocz, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(koncz, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(guz_play, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(guz_pause, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(guz_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(zacznij, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(skoncz, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(guz_add, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(guz_wyczysc, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bazaa, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(103, 103, 103)
                                                .addComponent(pole_artysta, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pole_utwor))))
                                    .addComponent(jScrollPane1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(210, 210, 210)
                                    .addComponent(pokaz_adres, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(Display, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(Display, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(guz_play, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(guz_stop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(guz_pause, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pokaz_adres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pole_artysta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pole_utwor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guz_add)
                            .addComponent(zacznij)
                            .addComponent(guz_wyczysc)
                            .addComponent(bazaa)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(skoncz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aktualna_lokalizacja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pocz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(koncz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void poczActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poczActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_poczActionPerformed

    private void zawartosc_odtwarzaczaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zawartosc_odtwarzaczaMouseClicked
        int indexOdt = zawartosc_odtwarzacza.getSelectedRow();
        Wyswietl_Odtwarzacz(indexOdt);
        String adrOdt = aktualna_lokalizacja.getText();
        GrajFragment(adrOdt);
    }//GEN-LAST:event_zawartosc_odtwarzaczaMouseClicked

    private void skonczActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skonczActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skonczActionPerformed

    private void zacznijActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zacznijActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zacznijActionPerformed

    private void bazaaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bazaaActionPerformed

        new OknoBazy().setVisible(true);
    }//GEN-LAST:event_bazaaActionPerformed

    private void guz_wyczyscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guz_wyczyscActionPerformed

        try {
            Connection polaczenie = getConnection();
            PreparedStatement ps = polaczenie.prepareStatement("DELETE FROM odtwarzacz_baza");

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usunieto pozycje");
        } catch (SQLException ex) {
            Logger.getLogger(OknoBazy.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Nie udalo sie usunac pozycji");
        }

    }//GEN-LAST:event_guz_wyczyscActionPerformed

    private void pole_artystaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pole_artystaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pole_artystaActionPerformed

    private void zawartosc_tabeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zawartosc_tabeliMouseClicked
        int index = zawartosc_tabeli.getSelectedRow();
        Wyswietl_Zawartosc(index);
        String adr = pokaz_adres.getText();
        Play(adr);

    }//GEN-LAST:event_zawartosc_tabeliMouseClicked

    private void guz_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guz_stopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guz_stopActionPerformed

    private void guz_stopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guz_stopMouseReleased
        Stop();
    }//GEN-LAST:event_guz_stopMouseReleased

    private void guz_playMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guz_playMouseReleased
        Resume();
    }//GEN-LAST:event_guz_playMouseReleased

    private void guz_pauseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guz_pauseMouseReleased
        Pause();
    }//GEN-LAST:event_guz_pauseMouseReleased

    private void guz_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guz_addActionPerformed
        Connection polaczenie=getConnection();
        try {PreparedStatement ps= polaczenie.prepareStatement("INSERT INTO odtwarzacz_baza(Artysta,Utwor,Lokalizacja,Poczatek,Koniec) VALUES(?,?,?,?,?)");
            ps.setString(1, pole_artysta.getText());
            ps.setString(2, pole_utwor.getText());
            ps.setString(3, pokaz_adres.getText());
            ps.setString(4, zacznij.getText());
            ps.setString(5, skoncz.getText());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OknoBazy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guz_addActionPerformed

    private void konczActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_konczActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_konczActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Odtwarzacz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Odtwarzacz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Odtwarzacz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Odtwarzacz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Odtwarzacz().setVisible(true);
               
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Display;
    private javax.swing.JTextField aktualna_lokalizacja;
    private javax.swing.JButton bazaa;
    private javax.swing.JButton guz_add;
    private javax.swing.JButton guz_pause;
    private javax.swing.JButton guz_play;
    private javax.swing.JButton guz_stop;
    private javax.swing.JButton guz_wyczysc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField koncz;
    private javax.persistence.EntityManager playlista2PUEntityManager;
    private javax.swing.JTextField pocz;
    private javax.swing.JTextField pokaz_adres;
    private javax.swing.JTextField pole_artysta;
    private javax.swing.JTextField pole_utwor;
    private javax.swing.JTextField skoncz;
    private javax.swing.JTextField zacznij;
    private javax.swing.JTable zawartosc_odtwarzacza;
    private javax.swing.JTable zawartosc_tabeli;
    // End of variables declaration//GEN-END:variables
}
