package view;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(String path) {
       try {                
          image = ImageIO.read(new File(path));
          Dimension d=new Dimension(new Dimension(image.getWidth(),image.getHeight()));
          this.setPreferredSize(d);
          this.setMaximumSize(d);
          this.setMinimumSize(d);
          this.setSize(d);
          
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}
