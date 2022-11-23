
package View;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Chiquang
 */
public class PlayVideoV extends JFrame {
    
    public PlayVideoV(String key) {
        try {        		                                       
            String url = "https://www.youtube.com/embed/" + key;
            JPanel panel = new JPanel(new BorderLayout());
            JWebBrowser browser = new JWebBrowser();
            panel.add(browser, BorderLayout.CENTER);
            browser.setBarsVisible(false);
            browser.setBarsVisible(false);

            browser.navigate(url);
            
            JFrame frame = new JFrame("Youtube Video");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setSize(700, 400);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width/2-frame.getSize().width/2, 150);
            frame.setVisible(true);
            frame.addWindowListener(new WindowListener() {

                @Override
                public void windowOpened(WindowEvent e) {
                        // TODO Auto-generated method stub				
                }

                @Override
                public void windowIconified(WindowEvent e) {
                        // TODO Auto-generated method stub				
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                        // TODO Auto-generated method stub				
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                        // TODO Auto-generated method stub				
                }

                @Override
                public void windowClosing(WindowEvent e) {

                }

                @Override
                public void windowClosed(WindowEvent e) {
                        // TODO Auto-generated method stub				
                }

                @Override
                public void windowActivated(WindowEvent e) {
                        // TODO Auto-generated method stub				
                }                      
            });                          
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }   
}
