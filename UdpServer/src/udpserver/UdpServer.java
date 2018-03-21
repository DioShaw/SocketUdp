/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yisus
 */
public class UdpServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] vec = new String[4];
        int cont = 0;
        String msj = "";
        
        try {

            DatagramSocket socket = new DatagramSocket(9107);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                socket.receive(peticion);
                
                System.out.println("Mensaje:" + new String(peticion.getData(), 0, peticion.getLength()));
                vec[cont] = new String(peticion.getData(), 0, peticion.getLength());
                cont = cont + 1;

                if (cont == 1) { msj = "Ingresa tu NRC";}
                if (cont == 2) { msj = "Ingresa nombre de la materia"; }
                if (cont == 3) { msj = "Ingresa nombre del docente";}
                if (cont == 4) { msj = "Gracias";
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Nombre: " + vec[0] + "\n" + "NRC :" + vec[1] + "\n" + "Asignatura: " + vec[2] + "\n" + "Docente: " + vec[3] + "\n");
}

                byte[] rr = msj.getBytes();
                DatagramPacket respuesta = new DatagramPacket(rr, rr.length, peticion.getAddress(), peticion.getPort());
                socket.send(respuesta);
            }

        } catch (SocketException ex) {
            System.out.println("Error:" + ex);
        } catch (IOException e) {
            System.out.println("Error 2" + e);

        }

    }

}
