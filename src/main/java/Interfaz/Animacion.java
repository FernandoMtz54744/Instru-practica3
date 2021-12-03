package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Fernando Mtz
 */
public class Animacion extends JFrame implements ActionListener{
    JLabel back, motor;
    int alturaAgua = 20;
    public Animacion(){
        //Configurando ventana
        setBounds(20, 20, 1200, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ImageIcon backgroundMotor = new ImageIcon("src/img/motor.png");
        Image imgMotor = backgroundMotor.getImage();
        Image tempMotor = imgMotor.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        backgroundMotor = new ImageIcon(tempMotor);
        motor = new JLabel(backgroundMotor);
        motor.setBounds(400, 500, 100, 100);
        add(motor);
        
        //Configurando fondo
        ImageIcon background = new ImageIcon("src/img/tinacoAgua.jpg");
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        alturaAgua+=10;
        System.out.println("Altura: " + alturaAgua);
        repaint();
    }
    
    public void paint(Graphics grap){
        super.paint(grap);
        Graphics2D g = (Graphics2D) grap;
        g.setColor(new Color(131, 215, 238));
        g.fillRect(598, 622-alturaAgua, 345, 10+alturaAgua);
        
    }
}
