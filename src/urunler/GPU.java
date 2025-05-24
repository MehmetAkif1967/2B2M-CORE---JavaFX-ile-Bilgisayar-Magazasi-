package urunler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GPU extends Product{
	public static ArrayList<GPU> gpus = new ArrayList<>();
	
	private String model ;
	private double vram ;
	private boolean ocable ;
	private String memType ;
	private int clockRate ;
	
	public GPU(double price,int stock,int id, String brand, String model, double vram, boolean ocable, String memType, int clockRate) {
		super(stock,id, brand,price);
		this.model = model;
		this.vram = vram;
		this.ocable = ocable;
		this.memType = memType;
		this.clockRate = clockRate;
		title = brand +" " +model ;
	}
	
	public GPU() {super();}

	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	public double getVram() {
		return vram;
	}

	public void setVram(double vram) {
		this.vram = vram;
	}

	public boolean isocable() {
		return ocable;
	}

	public void setocable(boolean ocable) {
		this.ocable = ocable;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}

	public int getClockRate() {
		return clockRate;
	}

	public void setClockRate(int clockRate) {
		this.clockRate = clockRate;
	}
	
	@Override
	public void addProduct(String features) throws Exception{
		String[] strs = new String[4];
		strs = features.split(",");
		vram = Double.parseDouble(strs[0]);
		ocable = Boolean.parseBoolean(strs[1]);
		memType = strs[2];
		clockRate = Integer.parseInt(strs[3]);
		title = brand + model ;
		id = gpus.getLast().getId()+1;
		products.add(this);
		gpus.add(this);
	}
	
	@Override
	public String getFeatures() {
		String abc ;
		String abd = ocable ? "Mevcut":"Mevcut değil";
		abc =	"VRAM KAPASİTESİ: "+vram+"\n"+
				"HIZ AŞIMI: " +abd+"\n"+
				"BELLEK TİPİ: "+memType+"\n"+
				"ÇEKİRDEK HIZI: "+clockRate;
		return abc ;
	}
	
	@Override
	public String getCategory() {
		return "Ekran Kartı";
	}
	

}