package View;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Chiquang
 */
public class HandleImage_GUI extends javax.swing.JFrame {
    
    public Home_GUI home = new Home_GUI();
    
    public HandleImage_GUI() {
        initComponents();
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
    }
    
    public void test(String path) {
        try {
            String encodedfile = null;
            File file = new File(path);                
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.getEncoder().encode(bytes));
            System.out.println(encodedfile);

//                byte[] test = Base64.getMimeDecoder().decode(encodedfile);
//                FileUtils.writeByteArrayToFile(new File(".\\src\\assets\\image\\image_handled\\test.jpg"), test);                
//                System.out.println(test);

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        image_handled = new javax.swing.JLabel();
        zoom_btn = new javax.swing.JButton();
        grayscale_btn = new javax.swing.JButton();
        changeimage_btn = new javax.swing.JButton();
        zipped_btn = new javax.swing.JButton();
        findsame_btn = new javax.swing.JButton();
        identify_btn = new javax.swing.JButton();
        back_btn = new javax.swing.JButton();
        chooseimage_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(700, 150));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("XỬ LÝ ẢNH");

        image_handled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/image/image_handled/film_DemonSlayer.jpg"))); // NOI18N
        image_handled.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        zoom_btn.setBackground(new java.awt.Color(0, 153, 255));
        zoom_btn.setForeground(new java.awt.Color(255, 255, 255));
        zoom_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/zoom.png"))); // NOI18N
        zoom_btn.setText("Phóng to/Thu nhỏ");
        zoom_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        grayscale_btn.setBackground(new java.awt.Color(0, 153, 255));
        grayscale_btn.setForeground(new java.awt.Color(255, 255, 255));
        grayscale_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/grayscale.png"))); // NOI18N
        grayscale_btn.setText("Grayscale");
        grayscale_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        changeimage_btn.setBackground(new java.awt.Color(0, 153, 255));
        changeimage_btn.setForeground(new java.awt.Color(255, 255, 255));
        changeimage_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/image_setting.png"))); // NOI18N
        changeimage_btn.setText("Chuyển đổi ảnh");
        changeimage_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        zipped_btn.setBackground(new java.awt.Color(0, 153, 255));
        zipped_btn.setForeground(new java.awt.Color(255, 255, 255));
        zipped_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/zipper.png"))); // NOI18N
        zipped_btn.setText("Nén ảnh");
        zipped_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        findsame_btn.setBackground(new java.awt.Color(0, 153, 255));
        findsame_btn.setForeground(new java.awt.Color(255, 255, 255));
        findsame_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/search_white.png"))); // NOI18N
        findsame_btn.setText("Tìm kiếm ảnh");
        findsame_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        identify_btn.setBackground(new java.awt.Color(0, 153, 255));
        identify_btn.setForeground(new java.awt.Color(255, 255, 255));
        identify_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/find-user-male.png"))); // NOI18N
        identify_btn.setText("Nhận dạng ảnh");
        identify_btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        identify_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identify_btnActionPerformed(evt);
            }
        });

        back_btn.setBackground(new java.awt.Color(255, 51, 0));
        back_btn.setForeground(new java.awt.Color(255, 255, 255));
        back_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/back.png"))); // NOI18N
        back_btn.setText("Quay lại");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        chooseimage_btn.setBackground(new java.awt.Color(255, 153, 0));
        chooseimage_btn.setForeground(new java.awt.Color(255, 255, 255));
        chooseimage_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/image-file-add.png"))); // NOI18N
        chooseimage_btn.setText("Chọn ảnh");
        chooseimage_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseimage_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(image_handled))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel1)))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(findsame_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zipped_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changeimage_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zoom_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(grayscale_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(identify_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(back_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(chooseimage_btn)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(image_handled)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(zipped_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeimage_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoom_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grayscale_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(findsame_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(identify_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(back_btn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chooseimage_btn)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void identify_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identify_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identify_btnActionPerformed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        this.dispose();
        new Home_GUI().run();
    }//GEN-LAST:event_back_btnActionPerformed

    private void chooseimage_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseimage_btnActionPerformed
        String path;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(HandleImage_GUI.this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getCurrentDirectory()+"\\"+chooser.getSelectedFile().getName();
            ImageIcon imageIcon = new ImageIcon(path);
            Image image = imageIcon.getImage();
            Image imageScale = image.getScaledInstance(image_handled.getWidth(), image_handled.getHeight(), Image.SCALE_SMOOTH);            
            System.out.println(path);
            image_handled.setIcon(new ImageIcon(imageScale));                        
        }
    }//GEN-LAST:event_chooseimage_btnActionPerformed

    public void run() {        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HandleImage_GUI().setVisible(true);                
            }
        });
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_btn;
    private javax.swing.JButton changeimage_btn;
    private javax.swing.JButton chooseimage_btn;
    private javax.swing.JButton findsame_btn;
    private javax.swing.JButton grayscale_btn;
    private javax.swing.JButton identify_btn;
    private javax.swing.JLabel image_handled;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton zipped_btn;
    private javax.swing.JButton zoom_btn;
    // End of variables declaration//GEN-END:variables
}
