package urunler;

import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;



public class RAM extends Product {
    public static ArrayList<RAM> rams = new ArrayList<>();

    private String model;
    private int capacity;
    private double frequancy;
    private String memType;
    private boolean ocable;

    public RAM(double price,int stock, int id, String brand, String model, int capacity, double frequancy, String memType, boolean ocable) {
        super(stock, id, brand,price);
        this.model = model;
        this.capacity = capacity;
        this.frequancy = frequancy;
        this.memType = memType;
        this.ocable = ocable;
        title = brand +" " +model ;
    }
    
    public RAM() {super();}

    public String getModel() {
        return model;
    }
    
    @Override
    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getFrequancy() {
        return frequancy;
    }

    public void setFrequancy(double frequancy) {
        this.frequancy = frequancy;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public boolean isOcable() {
        return ocable;
    }

    public void setOcable(boolean ocable) {
        this.ocable = ocable;
    }

    @Override
    public void addProduct(String features) throws Exception{
    	String[] strs = new String[4];
		strs = features.split(",");
		capacity = Integer.parseInt(strs[0]);
		frequancy = Double.parseDouble(strs[1]);
		memType = strs[2];
		ocable = Boolean.parseBoolean(strs[3]);
		title = brand + model ;
		id = rams.getLast().getId()+1;
		products.add(this);
		rams.add(this);
		
    }
    
    @Override
    public String getFeatures() {
    	String abc ;
    	String abd = ocable ? "Mevcut" : "Mevcut değil";
    	abc = " KAPASİTE: " + capacity + "GB" +"\n"+
                " FREKANS: " + frequancy + "MHz" +"\n"+
                " BELLEK TİPİ: " + memType +"\n"+
                " HIZ AŞIMI: " + abd ;
    	return abc ;
    }
    
    @Override
    public String getCategory() {
		return "RAM";
	}
    
}
