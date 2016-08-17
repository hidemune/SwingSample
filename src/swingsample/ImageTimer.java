/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingsample;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JViewport;

/**
 *
 * @author user
 */
public class ImageTimer extends JViewport{
    ImageIcon image = null;
    ImageIcon imageBG = null;
    int time = 0;
    ImageTimer parent;
    int allsec = 180;
    
    public ImageTimer(){
        parent = this;
        image = new ImageIcon("sunadokei.png");
        imageBG = new ImageIcon("sunadokeiBG.png");
        setSize(image.getIconWidth(), image.getIconHeight());
        setBorder(null);
        setOpaque(false);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        BufferedImage bgimg = new BufferedImage(image.getIconWidth(), image.getIconHeight()
                , BufferedImage.TYPE_INT_ARGB);
        
        //fgimg = ImageIO.read("");
        g.setColor(Color.GREEN);
        
        int center = 200;
        g.clearRect(0, 0, image.getIconWidth(), image.getIconHeight());
        g.drawImage(imageBG.getImage(), 0, 0, this);
        g.fillRect(0, 200 - 120 + time, 200, 120 - time);
        g.fillRect(0, 200 + 155 - time, 200, time);
        g.drawImage(image.getImage(), 0, 0, this);
        
    }
    
    public void setTime(int tim) {
        time = tim;
    }
    
    public void min3() {
        allsec = 180;
        SubThread t = new SubThread();
        t.run();
    }
    
    public void min4() {
        allsec = 240;
        SubThread t = new SubThread();
        t.run();
    }
    
    public void min5() {
        allsec = 300;
        SubThread t = new SubThread();
        t.run();
    }
    
    class SubThread extends Thread{
        public void run(){
            time = 0;
            for (int i = 0; i <= allsec; i++) {
                //Util.println("" + i);
                time = (int)Math.round((Double.valueOf(i) / Double.valueOf(allsec)) * 120D);
                //Util.println("" + time);
                parent.paintComponent(parent.getGraphics());
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImageTimer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
