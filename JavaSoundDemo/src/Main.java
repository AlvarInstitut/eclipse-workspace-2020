import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
    	 File Url=new File("audio/trippygaia1.mid");
    	 
       AudioClip clip = Applet.newAudioClip(Url.toURI().toURL());
       JFrame frame = new JFrame();
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setLayout(new FlowLayout());
       frame.add(new JButton(new AbstractAction("Sound") {
         @Override
         public void actionPerformed(ActionEvent e) {
          clip.loop();
         }
       }));
       frame.add(new JButton(new AbstractAction("No Sound") {
         @Override
         public void actionPerformed(ActionEvent e) {
          clip.stop();
         }
       }));
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
    }

}