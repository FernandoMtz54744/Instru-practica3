package Interfaz;

import com.fazecast.jSerialComm.SerialPort;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/*
 * @author Fernando Mtz
 */
public class Serial {
    
    private String msj = "";
    private BufferedReader in = null;
    
    public void connect(){
        SerialPort sp = SerialPort.getCommPorts()[0];
        sp.openPort();
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
        this.in = new BufferedReader(new InputStreamReader(sp.getInputStream()));
        /*
        do{
            try {
                this.msj = in.readLine();
                System.out.println(msj);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(true);*/
    }
    
        public String getMsj(){
            try {
                this.msj = in.readLine();
                System.out.println(msj);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return this.msj;
        }
    
    
    
}
