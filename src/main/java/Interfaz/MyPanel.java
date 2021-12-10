package Interfaz;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Fernando Mtz
 */

public class MyPanel extends JPanel{
    
    int alturaAgua = 20;
    BufferedImage nubeImg, motorImg, noche;
    int temperatura = 0;
    
    public MyPanel(){
        /*Panel transparente*/
        this.setOpaque(false);
        
        /*Se cargan las imagenes*/
        try {
            nubeImg = ImageIO.read(new File("src/img/nubeT.png"));
            motorImg = ImageIO.read(new File("src/img/motor.png"));
            noche = ImageIO.read(new File("src/img/Noche.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
   @Override
    protected void paintComponent(Graphics grap) {
        /*Se pinta el fondo*/
        super.paintComponent(grap); 

        Graphics2D g = (Graphics2D) grap;

        //Pintar el agua
        g.setColor(new Color(131, 215, 238));
        g.fillRect(590, 584-alturaAgua, 345, 10+alturaAgua);

        //Se pinta el fondo estrellado
        float opacity = 1f;
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g.drawImage(noche, 0, 0, 1200, 730,null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        //Se pinta la nube que tapa el sol
        int posXN=800+temperatura, posYN=20, nubeH=100, nubeW=300;
        g.drawImage(nubeImg, posXN,posYN,nubeW,nubeH,null);

        //Se pinta el motor y su rotacion
        int posX=400, posY=500, alturaImagen=100, anchuraImagen=100;
        AffineTransform old = g.getTransform();
        AffineTransform at = new AffineTransform(); 
        at.rotate(Math.toRadians(alturaAgua), posX+alturaImagen/2, posY+anchuraImagen/2);
        g.setTransform(at);
        g.drawImage(motorImg, posX,posY,alturaImagen,anchuraImagen,null);
        g.setTransform(old);

        //pintar la sombra 
        g.setColor(new Color(58, 110, 140, temperatura));
        g.fillRect(0, 0, 1200, 800);
    }
}
