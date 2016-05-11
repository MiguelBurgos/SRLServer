/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import SRL.*;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MiguelAngel
 */
public class SRLObject extends UnicastRemoteObject implements IRemoteSRL{

    private static final long serialVersionUID = 1L;

    private Registry registry;
    private IRemoteSRL srl;

    public SRLObject() throws RemoteException{
        super();
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1");
            srl = (IRemoteSRL) registry.lookup("SRLDB");
        } catch (NotBoundException ex) {
            Logger.getLogger(SRLObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(SRLObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Spectacle> readSpectacles() throws RemoteException {
        try {
            System.out.println("Invoke readSpectacles from  " + getClientHost());
        } catch (ServerNotActiveException snae) {
            // TODO: handle exception
            snae.printStackTrace();
        }
        return srl.readSpectacles();
    }

    @Override
    public int updateSeats(Spectacle s) throws RemoteException {
        try {
            System.out.println("Invoke updateSeats from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            // TODO: handle exception
            snae.printStackTrace();
        }
        return srl.updateSeats(s);
    }

    @Override
    public int updateSeat(Spectacle sp, Seat se) throws RemoteException {
        try {
            System.out.println("Invoke updateSeat from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            // TODO: handle exception
            snae.printStackTrace();
        }
        return srl.updateSeat(sp, se);
    }

    @Override
    public Spectacle readSeats(Spectacle s) throws RemoteException {
        try {
            System.out.println("Invoke readSeats from " + getClientHost());
        } catch (ServerNotActiveException snae) {
            // TODO: handle exception
            snae.printStackTrace();
        }
        return srl.readSeats(s);
    }

    @Override
    public boolean removeSession() throws RemoteException {
        String clientIP = null;
        try {
            clientIP = getClientHost();
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(SRLObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return Session.remove(clientIP);
    }
    
    @Override
    public boolean addSession(Spectacle s) throws RemoteException {
        String clientIP = null;
        try {
            clientIP = getClientHost();
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(SRLObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return Session.add(clientIP, s);
    }
    
    @Override
    public List<Seat> getSelectedSeats(Spectacle s) throws RemoteException {
        try {
            return Session.getSelectedSeats(getClientHost(), s);
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(SRLObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Seat> getSoldSeats(Spectacle s) throws RemoteException {
        return Session.getSoldSeats(s);
    }

    @Override
    public void setSelectedSeats(List<Seat> seats) throws RemoteException {
        try {
            Session.getSession(getClientHost()).setSelected(seats);
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(SRLObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setSoldSeats(List<Seat> seats) throws RemoteException {
        try {
            Session.getSession(getClientHost()).setSold(seats);
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(SRLObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
