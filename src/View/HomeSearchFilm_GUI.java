/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ClientController;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Chiquang
 */
public class HomeSearchFilm_GUI extends javax.swing.JFrame {    
    
    public int totalPage = 1;    
    public int currentPage = 1;
    public String search = "";
    
    public HomeSearchFilm_GUI() {        
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, 100);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);        
        try {
            String path = ".\\src\\assets\\icon\\emptylist.png";
            Image image = ImageIO.read(new File(path));
            image = image.getScaledInstance(jScrollPane1.getWidth()-20,jScrollPane1.getHeight(),Image.SCALE_DEFAULT);                       
            emptyListLabel.setIcon(new ImageIcon(image));
            listPanel.add(emptyListLabel);
            listPanel.setLayout(new GridLayout(1, 1));
        } catch (IOException ex) {
            new Alert_GUI().run();
        }        
    }
    
    public boolean checkValid(String result) {
        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
        return !regex.matcher(result).find();
    }
    
    public void appearResult() {
        try {                    
            if(!ClientController.message_from_server.equals("") && !ClientController.message_from_server.equals("null")) {                                             
                listPanel.removeAll();
                String jsonString = ClientController.message_from_server;               
                JSONObject json = new JSONObject(jsonString);
                JSONArray jsonArray = json.getJSONArray("results");
                int totalpage = json.getInt("total_pages");
                this.totalPage = totalpage;
                int length = jsonArray.length();
                for(int i = 0; i < length; i++) {
                    String id = jsonArray.getJSONObject(i).getString("id");
                    String title = jsonArray.getJSONObject(i).getString("original_title");
                    String poster = jsonArray.getJSONObject(i).getString("poster_path");
                    String description = jsonArray.getJSONObject(i).getString("overview");                    
                    ItemFilm item = new ItemFilm(id, poster, title, description);
                    listPanel.add(item);
                }                    
                listPanel.setLayout(new GridLayout(length, 1));
            }
            else {
                listPanel.removeAll();
                String path = ".\\src\\assets\\icon\\emptylist.png";
                Image image = ImageIO.read(new File(path));
                image = image.getScaledInstance(jScrollPane1.getWidth()-20,jScrollPane1.getHeight(),Image.SCALE_DEFAULT);                       
                emptyListLabel.setIcon(new ImageIcon(image));
                listPanel.add(emptyListLabel);
                listPanel.setLayout(new GridLayout(1, 1));
            }
            listPanel.revalidate();
            listPanel.repaint();
        } catch (IOException e) {
            new Alert_GUI().run();
        } catch (JSONException ex) {
            System.out.println("Error: " +ex);
            new Alert_GUI().run();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBody = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPanel = new javax.swing.JPanel();
        emptyListLabel = new javax.swing.JLabel();
        prevBtn = new javax.swing.JButton();
        pageLabel = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 100));
        setResizable(false);

        panelBody.setBackground(new java.awt.Color(102, 102, 102));

        Header.setBackground(new java.awt.Color(51, 51, 51));
        Header.setMaximumSize(new java.awt.Dimension(820, 986));

        btnSearch.setBackground(new java.awt.Color(0, 153, 255));
        btnSearch.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon("C:\\Users\\Chiquang\\Documents\\NetBeansProjects\\SearchFilmApp\\src\\assets\\icon\\search.png")); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("MOVIE");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("SEARCH");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addGap(23, 23, 23))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        backBtn.setBackground(new java.awt.Color(255, 0, 0));
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Thoát");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));

        listPanel.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout listPanelLayout = new javax.swing.GroupLayout(listPanel);
        listPanel.setLayout(listPanelLayout);
        listPanelLayout.setHorizontalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emptyListLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
        );
        listPanelLayout.setVerticalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emptyListLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listPanel);

        prevBtn.setBackground(new java.awt.Color(0, 153, 255));
        prevBtn.setForeground(new java.awt.Color(0, 153, 255));
        prevBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/previous-one.png"))); // NOI18N
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });

        pageLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pageLabel.setForeground(new java.awt.Color(0, 153, 255));
        pageLabel.setText("1");

        nextBtn.setBackground(new java.awt.Color(0, 153, 255));
        nextBtn.setForeground(new java.awt.Color(0, 153, 255));
        nextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/next-one.png"))); // NOI18N
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBodyLayout = new javax.swing.GroupLayout(panelBody);
        panelBody.setLayout(panelBodyLayout);
        panelBodyLayout.setHorizontalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBodyLayout.createSequentialGroup()
                        .addComponent(prevBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nextBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backBtn))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panelBodyLayout.setVerticalGroup(
            panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBodyLayout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backBtn)
                        .addComponent(prevBtn)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchVal = txtSearch.getText();
        search = searchVal;
        if(!searchVal.equals("") && searchVal!=null) {
            if(checkValid(searchVal)) {
                String message = searchVal + ";search;null;list-" + currentPage;
                Home_GUI.client.writeMessageToServer(message);
                Home_GUI.client.readMessageFromServer();
                appearResult();
            }
            else {
                JOptionPane.showMessageDialog(this, "Your input is not valid!");
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Please input your film name!");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        ClientController.message_from_server = "";
        this.dispose();
        new Home_GUI().run();
    }//GEN-LAST:event_backBtnActionPerformed

    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        if(currentPage != 1) {
            currentPage -= 1;
            pageLabel.setText(String.valueOf(currentPage));
            String message = search + ";search;null;list-" + currentPage;
            Home_GUI.client.writeMessageToServer(message);
            Home_GUI.client.readMessageFromServer();                
            appearResult();
        }
        else {
            JOptionPane.showMessageDialog(this, "Page not found!");
        }
    }//GEN-LAST:event_prevBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        if(currentPage != totalPage) {
            currentPage += 1;
            pageLabel.setText(String.valueOf(currentPage));
            pageLabel.setText(String.valueOf(currentPage));
            String message = search + ";search;null;list-" + currentPage;
            Home_GUI.client.writeMessageToServer(message);
            Home_GUI.client.readMessageFromServer();                
            appearResult();
        }
        else {
            JOptionPane.showMessageDialog(this, "Page not found!");
        }
    }//GEN-LAST:event_nextBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public void run() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeSearchFilm_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel emptyListLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel listPanel;
    private javax.swing.JLabel logo;
    private javax.swing.JButton nextBtn;
    private javax.swing.JLabel pageLabel;
    private javax.swing.JPanel panelBody;
    private javax.swing.JButton prevBtn;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
