package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Vazquez Perez Denzel
 * @author Fernando Mtz
 * @author Luis Fernando
 */
public class Animation extends JFrame implements ActionListener{
        
    MyPanel panel;
    JLabel back;
    String msj = "";
    
    public Animation(){
        //Se configura la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(100, 20, 1200, 950);
        setLocationRelativeTo(null);
        
        //Se configura el panel para dibujar
        panel  = new MyPanel();
        panel.setBounds(0, 0, 1200, 800);
        add(panel);
        
        //Se agrega el fondo
        ImageIcon background = new ImageIcon("src/img/tinacoAguaSol.jpg");
        Image img = background.getImage();
        Image temp=img.getScaledInstance(1200,800,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        back = new JLabel(background);
        back.setBounds(0, 0, 1200, 800);
        add(back);
        
        
        
        //Se agrega el botón
        JButton atras = new JButton("Atras");
        atras.setBounds(400, 820, 400, 60);
        atras.addActionListener(this);
        atras.setFont(new Font("Dialog", Font.PLAIN, 20));
        atras.setBackground(new Color(0,33,113));
        atras.setForeground(Color.WHITE);
        atras.setFocusPainted(false);
        add(atras);
        
        
        setVisible(true);
        
        //Se inicia la conexion
        Serial serial = new Serial();
        serial.connect();
        
        //Se lee el mensaje cada 3 segundos
        Runnable listen = new Runnable() {
             public void run() {
                    msj = serial.getMsj();
                    System.out.println(msj);
                    repaint();
            }
        };
        
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(listen, 0, 3, TimeUnit.SECONDS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.alturaAgua+=10;
        panel.temperatura+=10;
        panel.repaint();
    }
}
