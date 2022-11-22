/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.APIController;
import Controller.ClientController;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
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
public class FilmDetail_GUI extends javax.swing.JFrame {
    
    public static String id = "";    
    
    public FilmDetail_GUI() {
        initComponents();        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, 40);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        try {
            if(!ClientController.message_from_server.equals("") && !ClientController.message_from_server.equals("null")) {
                String jsonString = ClientController.message_from_server;       
                Locale usa = new Locale("en", "US");
                Currency dollars = Currency.getInstance(usa);
                NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);                                
                JSONObject json = new JSONObject(jsonString);  
                id = json.getString("id");
                String title = json.getString("original_title");
                String subTitle = json.getString("tagline");
                String runTime = json.getString("runtime");
                String releaseDate = json.getString("release_date");
                String year = releaseDate.split("-")[0];
                String revenue = dollarFormat.format(Integer.parseInt(json.getString("revenue")));
                String voteAverage = json.getString("vote_average");
                String voteCount = json.getString("vote_count");
                String overview = json.getString("overview");
                String overviewArray[] = overview.split(" ");
                String path = json.getString("poster_path");
                
                BigDecimal bd = new BigDecimal(Double.parseDouble(voteAverage));
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                voteAverage = String.valueOf(bd.doubleValue());                                                                                                
                
                JSONArray genresArray = json.getJSONArray("genres");
                String genres = "";
                String lineGenres = "";
                for(int i = 0; i < genresArray.length(); i++) {
                    lineGenres += genresArray.getJSONObject(i).getString("name") + ", ";
                    if(lineGenres.length() >= 65) {
                        genres += lineGenres + "<br>";
                        lineGenres = "";
                    }                    
                }
                genres += lineGenres;
                
                JSONArray companyArray = json.getJSONArray("production_companies");
                String companies = "";
                String lineCompanies = "";
                for(int i = 0; i < companyArray.length(); i++) {
                    lineCompanies += companyArray.getJSONObject(i).getString("name") + ", ";
                    if(lineCompanies.length() >= 65) {
                        companies += lineCompanies + "<br>";
                        lineCompanies = "";
                    }                    
                }
                companies += lineCompanies;
                
                JSONArray countriesArray = json.getJSONArray("production_countries");
                String countries = "";
                for(int i = 0; i < countriesArray.length(); i++) {
                    countries += countriesArray.getJSONObject(i).getString("name") + ", ";
                }
                
                String longOverview = "";
                String lineOverview = "";
                for(int i = 0; i < overviewArray.length; i++) {
                    lineOverview += overviewArray[i] + " ";                    
                    if(lineOverview.length() >= 65) {
                        longOverview += lineOverview + "<br>";
                        lineOverview = "";
                    }                    
                }
                longOverview += lineOverview;
                
                if(!genres.equals("")) genres = genres.substring(0, genres.length() - 2);
                if(!companies.equals("")) companies = companies.substring(0, companies.length() - 2);
                if(!countries.equals("")) countries = countries.substring(0, countries.length() - 2);
                txtTitleName.setText(title);
                txtTagLine.setText(subTitle);
                txtGenres.setText("<html><body>" + genres + "</body></html>");
                txtCompany.setText("<html><body>" + companies + "</body></html>");
                txtCountries.setText(countries);
                txtRunTime.setText(runTime + " Phút");
                txtYear.setText(year);
                txtRevenue.setText(revenue);
                txtVoteCount.setText(voteAverage + " (" + voteCount + ")");
                txtOverview.setText("<html><body>" + longOverview + "</body><html>");
                String newPath = null;
                if(!path.equals("null")) {
                    newPath = APIController.imageURL + path;
                    URL url = new URL(newPath);
                    Image image = ImageIO.read(url);
                    image = image.getScaledInstance(206,275,Image.SCALE_DEFAULT);
                    txtImage.setIcon(new ImageIcon(image));
                }
                else {
                    newPath = ".\\src\\assets\\icon\\noimage.jpg";
                    Image image = ImageIO.read(new File(newPath));
                    image = image.getScaledInstance(206,275,Image.SCALE_DEFAULT);
                    txtImage.setIcon(new ImageIcon(image));
                }
            }            
        } catch (Exception e) {
            
        }        
    }
    
    public String getKeyVideo(String id) {
        String key = "";
        try {
            String message = id + ";search;null;video-null";
            Home_GUI.client.writeMessageToServer(message);
            Home_GUI.client.readMessageFromServer();
            String jsonString = ClientController.message_from_server;
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonArray = json.getJSONArray("results");
            if(jsonArray.length() >= 1) {
                key = jsonArray.getJSONObject(0).getString("key");
                return key;
            }
        } catch (JSONException ex) {
            new Alert_GUI().run();
        }
        return key;
    }   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Layout = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTitleName = new javax.swing.JLabel();
        txtTagLine = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCompany = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtGenres = new javax.swing.JLabel();
        txtCountries = new javax.swing.JLabel();
        txtRunTime = new javax.swing.JLabel();
        txtYear = new javax.swing.JLabel();
        txtVoteCount = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        videoPanel = new javax.swing.JPanel();
        watchBtn = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtOverview = new javax.swing.JLabel();
        txtImage = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtRevenue = new javax.swing.JLabel();
        back_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 255));
        setLocation(new java.awt.Point(500, 150));
        setResizable(false);

        Layout.setBackground(new java.awt.Color(102, 102, 102));
        Layout.setForeground(new java.awt.Color(51, 51, 51));
        Layout.setMaximumSize(new java.awt.Dimension(820, 986));

        Header.setBackground(new java.awt.Color(51, 51, 51));
        Header.setMaximumSize(new java.awt.Dimension(820, 986));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        txtTitleName.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        txtTitleName.setForeground(new java.awt.Color(0, 153, 255));
        txtTitleName.setText("Người sói Wolverine");

        txtTagLine.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        txtTagLine.setForeground(new java.awt.Color(255, 255, 255));
        txtTagLine.setText("The Wolverine (2013)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Nhà sản xuất:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Thể loại:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Quốc gia:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Thời lượng:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Năm phát hành:");

        txtCompany.setForeground(new java.awt.Color(0, 153, 255));
        txtCompany.setText("James Mangold");

        txtGenres.setForeground(new java.awt.Color(0, 153, 255));
        txtGenres.setText("Phim lẻ, Phim chiếu rạp, Hành Động, Viễn Tưởng");

        txtCountries.setForeground(new java.awt.Color(0, 153, 255));
        txtCountries.setText("Mỹ, Anh");

        txtRunTime.setForeground(new java.awt.Color(0, 153, 255));
        txtRunTime.setText("0 Phút");

        txtYear.setForeground(new java.awt.Color(0, 153, 255));
        txtYear.setText("-");

        txtVoteCount.setForeground(new java.awt.Color(255, 255, 255));
        txtVoteCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/icons8-star-filled-24.png"))); // NOI18N
        txtVoteCount.setText("0");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 153, 255));
        jLabel24.setText("Thông tin");

        videoPanel.setBackground(new java.awt.Color(0, 0, 0));

        watchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/youtube.png"))); // NOI18N
        watchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                watchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout videoPanelLayout = new javax.swing.GroupLayout(videoPanel);
        videoPanel.setLayout(videoPanelLayout);
        videoPanelLayout.setHorizontalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, videoPanelLayout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(watchBtn)
                .addGap(150, 150, 150))
        );
        videoPanelLayout.setVerticalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, videoPanelLayout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(watchBtn)
                .addGap(100, 100, 100))
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 153, 255));
        jLabel25.setText("Trailer");

        txtOverview.setForeground(new java.awt.Color(255, 255, 255));
        txtOverview.setText("The Wolverine là cuộc phiêu lưu hành động nghẹt thở đưa anh hùng");

        txtImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/image_90.png"))); // NOI18N
        txtImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Doanh thu:");

        txtRevenue.setForeground(new java.awt.Color(0, 153, 255));
        txtRevenue.setText("0");

        back_btn.setBackground(new java.awt.Color(255, 51, 0));
        back_btn.setForeground(new java.awt.Color(255, 255, 255));
        back_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon/back.png"))); // NOI18N
        back_btn.setText("Quay lại");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LayoutLayout = new javax.swing.GroupLayout(Layout);
        Layout.setLayout(LayoutLayout);
        LayoutLayout.setHorizontalGroup(
            LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LayoutLayout.createSequentialGroup()
                .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel3))
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTitleName)
                                .addComponent(txtTagLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(LayoutLayout.createSequentialGroup()
                                    .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel15))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtRevenue)
                                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtRunTime)
                                            .addGroup(LayoutLayout.createSequentialGroup()
                                                .addComponent(txtYear)
                                                .addGap(211, 211, 211)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtCountries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtGenres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addComponent(txtVoteCount))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LayoutLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtCompany, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel12))
                        .addContainerGap())))
            .addGroup(LayoutLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addComponent(back_btn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addComponent(videoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LayoutLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(LayoutLayout.createSequentialGroup()
                                .addComponent(txtOverview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        LayoutLayout.setVerticalGroup(
            LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutLayout.createSequentialGroup()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addComponent(txtTitleName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTagLine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCompany))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtGenres))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtCountries))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtRunTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(txtYear)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtRevenue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVoteCount)
                        .addGap(53, 53, 53))
                    .addGroup(LayoutLayout.createSequentialGroup()
                        .addComponent(txtImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24))
                .addGap(14, 14, 14)
                .addGroup(LayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(videoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOverview))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back_btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(Layout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Layout, javax.swing.GroupLayout.PREFERRED_SIZE, 730, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        this.dispose();        
    }//GEN-LAST:event_back_btnActionPerformed

    private void watchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_watchBtnActionPerformed
        String key = getKeyVideo(id);
        if(key.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Lỗi","Không có trailer cho phim này!",JOptionPane.CANCEL_OPTION);
            return;
        }        
        NativeInterface.open();
        new PlayVideoV(key);
//        NativeInterface.runEventPump();
//            
//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
//            @Override
//            public void run() {
//                NativeInterface.close();
//            }                
//        }));
    }//GEN-LAST:event_watchBtnActionPerformed

    public void run() {        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FilmDetail_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Layout;
    private javax.swing.JButton back_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel txtCompany;
    private javax.swing.JLabel txtCountries;
    private javax.swing.JLabel txtGenres;
    private javax.swing.JLabel txtImage;
    private javax.swing.JLabel txtOverview;
    private javax.swing.JLabel txtRevenue;
    private javax.swing.JLabel txtRunTime;
    private javax.swing.JLabel txtTagLine;
    private javax.swing.JLabel txtTitleName;
    private javax.swing.JLabel txtVoteCount;
    private javax.swing.JLabel txtYear;
    private javax.swing.JPanel videoPanel;
    private javax.swing.JButton watchBtn;
    // End of variables declaration//GEN-END:variables
}
