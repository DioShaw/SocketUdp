/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yisus
 */
public class UdpClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            String bv = "Ingresa Tu nombre";

            DatagramSocket socket = new DatagramSocket();

            while (true) {
                String msj = JOptionPane.showInputDialog(bv);
                byte[] mensaje = msj.getBytes();
                InetAddress host = InetAddress.getByName("127.0.0.1");
                int puerto = 9107;
                DatagramPacket paquete = new DatagramPacket(mensaje, msj.length(), host, puerto);
                socket.send(paquete);

                // creamos el paquete de resivido  
                byte[] bufer = new byte[1024];
                DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
                socket.receive(respuesta);
                //System.out.println("Mensaje:"+new String(respuesta.getData(),0,respuesta.getLength()));
                    bv = new String(respuesta.getData(), 0, respuesta.getLength());

            }

        } catch (SocketException ex) {
            System.out.println("Error" + ex);
        } catch (IOException e) {
            System.out.println("Error" + e);
        }

    }

}
