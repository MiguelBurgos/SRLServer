/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SRL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author MiguelAngel
 */
public class Session implements Serializable{

    private static final long serialVersionUID = 1L;
    private final String owner;
    private final Spectacle show;

    private List<Seat> selected;
    private List<Seat> sold;
    private static Map<String, Session> interactions = new HashMap<>();

    private Session(String owner, Spectacle show) {
        this.owner = owner;
        this.show = show;
        this.selected = new ArrayList<>();
        this.sold = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public Spectacle getShow() {
        return show;
    }

    public List<Seat> getSelected() {
        return selected;
    }

    public List<Seat> getSold() {
        return sold;
    }

    public void setSelected(List<Seat> selected) {
        this.selected = selected;
    }

    public void setSold(List<Seat> preSold) {
        this.sold = preSold;
    }
    
    public static boolean add(String ip, Spectacle s){
        try {
            interactions.put(ip, new Session(ip, s));
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean remove(String ip){
        try{
            interactions.remove(ip);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public static Session getSession(String ownerIP){
        return interactions.get(ownerIP);
    }

    public static List<Seat> getSelectedSeats(String requestIp, Spectacle s){

        List<Seat> selectedSeats = new ArrayList<>();
        
        for(Entry<String, Session> entry : interactions.entrySet()) {
            
            Session value = entry.getValue();
            if((value.getSelected().size() > 0)
                    && (value.getShow().getId() == s.getId())
                    && !(value.getOwner().equals(requestIp))) {
                for (Seat current : value.getSelected()) {
                    selectedSeats.add(current);
                }
            }
        }
        
        return selectedSeats;
    }

    public static List<Seat> getSoldSeats(Spectacle s){

        List<Seat> preSoldSeats = new ArrayList<>();

        for(Entry<String, Session> entry : interactions.entrySet()) {

            Session value = entry.getValue();
            if(!(value.getSold().isEmpty())
                    && (value.getShow().getId() == s.getId())) {
                
                for (Seat current : value.getSold()) {
                    preSoldSeats.add(current);
                }
            }
        }

        return preSoldSeats;
    }
}
