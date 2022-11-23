/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ClientController;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Chiquang
 */
public class Alert_GUI extends javax.swing.JFrame {    
    /**
     * Creates new form ErrorAlert_GUI
     */
    public static String alertType = "";
    public static String alertMessage = "";
    
    public Alert_GUI() {
        initComponents();
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);        
        if(alertType.equals("Success")) {
            message.setText(alertMessage);
            titleTxt.setForeground(Color.GREEN);
            message.setForeground(Color.GREEN);
            jLabel2.setIcon(new ImageIcon(".\\src\\assets\\icon\\icons8-ok-64.png"));
        }
        else if(alertType.equals("Error")) {             
            message.setText(alertMessage);
            titleTxt.setForeground(Color.RED);
            message.setForeground(Color.RED);
            jLabel2.setIcon(new ImageIcon(".\\src\\assets\\icon\\icons8-cancel-64.png"));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        titleTxt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        closeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(800, 250));
        setResizable(false);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        titleTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titleTxt.setForeground(new java.awt.Color(255, 51, 51));
        titleTxt.setText("Thông báo");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icons8-cancel-64.png"))); // NOI18N

        message.setForeground(new java.awt.Color(255, 51, 51));
        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        message.setText("Error");
        message.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        closeBtn.setBackground(new java.awt.Color(51, 153, 255));
        closeBtn.setForeground(new java.awt.Color(255, 255, 255));
        closeBtn.setText("OK");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(0, 88, Short.MAX_VALUE)
                                .addComponent(titleTxt)
                                .addGap(86, 86, 86)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(111, 111, 111))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(closeBtn)
                                .addGap(121, 121, 121))))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public void run() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alert_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel message;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel titleTxt;
    // End of variables declaration//GEN-END:variables
}
