package urunler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CPU extends Product{
	public static ArrayList<CPU> cpus = new ArrayList<>();
	
	private String model ;
	private int coreNum ,threadNum;
	private double frequancy ;
	private String socket ;
	private String ramType ;
	private boolean ocable ;
	public CPU(double price,int stock, int id, String brand, String model, int coreNum, int threadNum, double frequancy,
			String socket, String ramType, boolean ocable) {
		super(stock, id, brand,price);
		this.model = model;
		this.coreNum = coreNum;
		this.threadNum = threadNum;
		this.frequancy = frequancy;
		this.socket = socket;
		this.ramType = ramType;
		this.ocable = ocable;
		title = brand +" " +model ;
	}
	public CPU() {super();}
	public String getModel() {
		return model;
	}
	
	@Override
	public void setModel(String model) {
		this.model = model;
	}
	public int getCoreNum() {
		return coreNum;
	}
	public void setCoreNum(int coreNum) {
		this.coreNum = coreNum;
	}
	public int getThreadNum() {
		return threadNum;
	}
	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
	public double getFrequancy() {
		return frequancy;
	}
	public void setFrequancy(double frequancy) {
		this.frequancy = frequancy;
	}
	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	public String getRamType() {
		return ramType;
	}
	public void setRamType(String ramType) {
		this.ramType = ramType;
	}
	public boolean isOcable() {
		return ocable;
	}
	public void setOcable(boolean ocable) {
		this.ocable = ocable;
	}
	
	@Override
	public void addProduct(String features)throws Exception {
		String[] strs = new String[6];
		strs = features.split(",");
		coreNum = Integer.parseInt(strs[0]);
		threadNum = Integer.parseInt(strs[1]);
		ocable = Boolean.parseBoolean(strs[2]);
		frequancy = Double.parseDouble(strs[3]);
		socket = strs[4] ;
		ramType = strs[5];
		title = brand + model ;
		this.id = cpus.getLast().getId()+1;
		products.add(this);
		cpus.add(this);
		
	}
	
	@Override
	public String getFeatures() {
		String abc ;
		String abd = ocable ? "Mevcut":"Mevcut değil";
		abc = 	"ÇEKİRDEK/THREAD SAYISI: "+coreNum +"/"+threadNum+"\n"+
				"HIZ AŞIMI: " +abd+"\n"+
				"Frekans: "+frequancy+"\n"+
				"SOKET: "+socket+"\n"+
				"BELLEK DESTEĞİ: "+ramType ;
		return abc ;
	}
	
	@Override
	public String getCategory() {
		return "İşlemci";
	}
	
}
