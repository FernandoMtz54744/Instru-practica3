package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author Vazquez Perez Denzel
 * @author Fernando Mtz
 * @author Luis Fernando
 */
public class Animacion extends JFrame implements ActionListener{
    JLabel back, motor;
    int alturaAgua = 20;
    int temperatura = 0;
    String msj = "";
    
    public Animacion(){
        //Configurando ventana
        setBounds(20, 20, 1200, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Configurando fondo
        ImageIcon background = new ImageIcon("src/img/tinacoAguaSol.jpg");
        Image img = background.getImage();
        Image temp=img.getScaledInstance(1200,800,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        back = new JLabel(background);
        back.setBounds(0, 0, 1200, 800);
        add(back);
        
        JButton prueba = new JButton("Prueba");
        prueba.setBounds(50, 50, 100, 100);
        prueba.addActionListener(this);
        add(prueba);
        
        setVisible(true);
        
        //Se inicia la conexion
        Serial serial = new Serial();
        serial.connect();
        
        //Se lee el mensaje cada 3 segundos
        Runnable listen = new Runnable() {
             public void run() {
                    System.out.println(serial.getMsj());
            }
        };
        
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(listen, 0, 3, TimeUnit.SECONDS);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        alturaAgua+=10;
        temperatura+=20;
        System.out.println("Altura: " + alturaAgua);
        repaint();
    }
    
    public void paint(Graphics grap){
        super.paint(grap);
        
        Graphics2D g = (Graphics2D) grap;
        
        //Pintar el agua
        g.setColor(new Color(131, 215, 238));
        g.fillRect(598, 622-alturaAgua, 345, 10+alturaAgua);
        
         //pintar la sombra
        g.setColor(new Color(58, 110, 140, temperatura));
        g.fillRect(0, 0, 1200, 800);
        
        //Pintar la nube que tapa el sol
        try{
           BufferedImage nube = ImageIO.read(new File("src/img/nubeT.png"));
           int posXN=800+temperatura, posYN=20, nubeH=100, nubeW=300;
           g.drawImage(nube, posXN,posYN,nubeW,nubeH,null);
        }catch(IOException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        
        //Pintar el motor
        try {
            BufferedImage motor = ImageIO.read(new File("src/img/motor.png"));
            int posX=400, posY=500, alturaImagen=100, anchuraImagen=100;
            //Rotar imagen
            AffineTransform old = g.getTransform();
            AffineTransform at = new AffineTransform(); 
            at.rotate(Math.toRadians(alturaAgua), posX+alturaImagen/2, posY+anchuraImagen/2);
            g.setTransform(at);
            g.drawImage(motor, posX,posY,alturaImagen,anchuraImagen,null);
            g.setTransform(old);
        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        
        
        
    }
    
        
}
