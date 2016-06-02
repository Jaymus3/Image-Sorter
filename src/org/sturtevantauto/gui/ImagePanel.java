package org.sturtevantauto.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{

    private BufferedImage image;
    private Image scaledImage;

    public ImagePanel(String filepath) {
       try {                
          image = ImageIO.read(new File(filepath));
          scaledImage = image.getScaledInstance(95 ,95 ,Image.SCALE_FAST);
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaledImage, 0, 0, null);         
    }

}
