package urunler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage extends Product {
    public static ArrayList<Storage> storages = new ArrayList<>();

    private String connectType;
    private int capacity; // GB cinsinden
    private String interfaceType;
    private double readSpeed;   // MB/s
    private double writeSpeed;  // MB/s
    private String model ;

    public Storage(double price,int stock, int id, String brand,String model, String connectType, int capacity, String interfaceType, double readSpeed, double writeSpeed) {
        super(stock, id, brand,price);
        this.model = model;
        this.connectType = connectType;
        this.capacity = capacity;
        this.interfaceType = interfaceType;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
        title = brand +" " +model ;
    }
    
    public Storage() {super();}

    public String getModel() {
		return model;
	}
    
    @Override
	public void setModel(String model) {
		this.model = model;
	}

	public String getConnectType() {
        return connectType;
    }

    public void setConnectType(String connectType) {
        this.connectType = connectType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public double getReadSpeed() {
        return readSpeed;
    }

    public void setReadSpeed(double readSpeed) {
        this.readSpeed = readSpeed;
    }

    public double getWriteSpeed() {
        return writeSpeed;
    }

    public void setWriteSpeed(double writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    @Override
    public void addProduct(String features) throws Exception{
    	String[] strs = new String[5];
		strs = features.split(",");
		connectType = strs[0];
		capacity = Integer.parseInt(strs[1]);
		interfaceType = strs[2];
		readSpeed = Integer.parseInt(strs[3]);
		writeSpeed = Integer.parseInt(strs[4]);
		title = brand + model ;
		id = storages.getLast().getId()+1;
		products.add(this);
		storages.add(this);
    }
    
    @Override
    public String getFeatures() {
    	String abc ;
    	abc = 	" BAĞLANTI TİPİ: " + connectType +"\n"+
                " KAPASİTE: " + capacity + "GB" +"\n"+
                " ARAYÜZ: " + interfaceType +"\n"+
                " OKUMA HIZI: " + readSpeed + "MB/s" +"\n"+
                " YAZMA HIZI: " + writeSpeed + "MB/s";
    	return abc ;
    }
    
    @Override
    public String getCategory() {
		return "Depolama";
	}
}
