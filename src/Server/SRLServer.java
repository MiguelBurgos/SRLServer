/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author MiguelAngel
 */
public class SRLServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {

            //create and get reference to rmi registry
            Registry registry = LocateRegistry.createRegistry(1098);
            // Iniciate server object
            SRLObject srl = new SRLObject();
            //Register server object
            registry.rebind("SRL", srl);
            System.out.println("SRLServer is created!!!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
