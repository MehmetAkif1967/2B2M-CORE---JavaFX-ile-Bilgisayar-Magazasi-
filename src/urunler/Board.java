package urunler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class Board extends Product {
    public static ArrayList<Board> boards = new ArrayList<>();

    private String socket;
    private String chipset;
    private String memType;
    private int memSlots;
    private String formFactor;
    private String model;

    public Board(double price,int stock, int id, String brand, String socket, String chipset, String memType, int memSlots, String formFactor,String model) {
        super(stock, id, brand, price);
        this.socket = socket;
        this.chipset = chipset;
        this.memType = memType;
        this.memSlots = memSlots;
        this.formFactor = formFactor;
        this.model = model;
        title = brand +" "+ model ;
    }
    
    public Board() {super();}

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public int getMemSlots() {
        return memSlots;
    }

    public void setMemSlots(int memSlots) {
        this.memSlots = memSlots;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
    
    public String getModel() {
    	return model ;
    }
    
    @Override
    public void setModel(String model) {
    	this.model = model;
    }
    
    @Override
    public void addProduct(String features) throws Exception{
    	String[] strs = new String[5] ;
    	 strs= features.split(",");
    	 socket = strs[0] ;
    	 chipset = strs[1] ;
    	 memType = strs[2] ;
    	 memSlots =Integer.parseInt(strs[3]) ;
    	 formFactor = strs[4] ;
    	 this.id = boards.getLast().getId()+1;
    	 title = brand + model ;
    	 boards.add(this);
    	
    }
    
    @Override
    public String getFeatures() {
    	String abc ;
    	abc =	" SOKET: " + socket + "\n"+
                " YONGA SETİ: " + chipset + "\n"+
                " BELLEK TİPİ: " + memType + "\n"+
                " BELLEK YUVA SAYISI: " + memSlots + "\n"+
                " FORM FAKTÖRÜ: " + formFactor;
    	return abc ;
    }
    
    @Override
    public String getCategory() {
		return "Anakart";
	}
    
}
