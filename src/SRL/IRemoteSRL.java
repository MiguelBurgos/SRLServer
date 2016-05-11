/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SRL;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author MiguelAngel
 */
public interface IRemoteSRL extends Remote{

    public List<Spectacle> readSpectacles() throws RemoteException;

    public int updateSeats(Spectacle s) throws RemoteException;

    public int updateSeat(Spectacle sp, Seat se) throws RemoteException;

    public Spectacle readSeats(Spectacle s) throws RemoteException;
    
    public boolean removeSession() throws RemoteException;
    
    public boolean addSession(Spectacle s) throws RemoteException;
    
    public List<Seat> getSelectedSeats(Spectacle s) throws RemoteException;

    public List<Seat> getSoldSeats(Spectacle s) throws RemoteException;
    
    public void setSelectedSeats(List<Seat> seats) throws RemoteException;
    
    public void setSoldSeats(List<Seat> seats) throws RemoteException;

}
