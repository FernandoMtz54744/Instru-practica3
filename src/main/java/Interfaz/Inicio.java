package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Fernando Mtz
 */
public class Inicio extends JFrame implements  ActionListener{
    JLabel titulo, back, grupo, nombres;
    JButton start;
    
    public Inicio(){
        setBounds(20, 20, 1200, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        titulo = new JLabel("Bienvenido al sistema de llenado automático", SwingConstants.CENTER);
        titulo.setBounds(100, 40, 1000, 60);
        titulo.setFont(new Font("Dialog", Font.PLAIN, 48));
        add(titulo);
        
        grupo = new JLabel("Grupo 3CV13", SwingConstants.CENTER);
        grupo.setBounds(80, 650, 200, 60);
        grupo.setFont(new Font("Dialog", Font.PLAIN, 32));
        add(grupo);
        
        nombres = new JLabel("<html>Hernandez Bravo Luis Fernando<br/>Martinez Martinez Fernando<br/>Vazquez Perez Denzel Omar</html>", SwingConstants.CENTER);
        nombres.setBounds(750, 620, 400, 100);
        nombres.setFont(new Font("Dialog", Font.PLAIN, 22));
        add(nombres);
        
        start = new JButton("Iniciar");
        start.setBounds(370, 510, 400, 60);
        start.setFont(new Font("Dialog", Font.PLAIN, 40));
        start.setBackground(new Color(0,33,113));
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.addActionListener(this);
        add(start);
        
        ImageIcon background = new ImageIcon("src/img/agua.jpg");
        Image img = background.getImage();
        back = new JLabel(background);
        back.setBounds(0, 0, 1200, 800);
        add(back);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Animation animacion = new Animation();
        dispose();
    }
}
