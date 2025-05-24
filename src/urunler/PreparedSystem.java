package urunler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PreparedSystem extends Product{
    public static ArrayList<PreparedSystem> systems= new ArrayList<>();
	
	private CPU proccessors;
    private GPU graphics;
    private RAM ram;
    private Storage storage;
    private Board board;

    public PreparedSystem(double price,int stock,int id,String brand,CPU proccessors, GPU graphics, RAM ram, Storage storage, Board board) {
        super(stock, id, brand,price);
    	this.proccessors = proccessors;
        this.graphics = graphics;
        this.ram = ram;
        this.storage = storage;
        this.board = board;
        title = brand;
    }
    
    public PreparedSystem() {super();}

    public CPU getCpu() {
        return proccessors;
    }

    public void setCpu(CPU proccessors) {
        this.proccessors = proccessors;
    }

    public GPU getGpu() {
        return graphics;
    }

    public void setGpu(GPU graphics) {
        this.graphics = graphics;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    @Override
    public void setBrand(String brand) {
    	this.brand = brand ;
    }

    public void displaySystemInfo() {
        System.out.println("=== Hazır Sistem Bilgileri ===");
        System.out.println("CPU: " + proccessors.getBrand() + " " + proccessors.getModel());
        System.out.println("GPU: " + graphics.getBrand() + " " + graphics.getModel());
        System.out.println("RAM: " + ram.getBrand() + " " + ram.getModel() + " " + ram.getCapacity() + "GB");
        System.out.println("Storage: " + storage.getBrand() + " " + storage.getCapacity() + "GB " + storage.getConnectType());
        System.out.println("Anakart: " + board.getBrand() + " " + board.getSocket() + " " + board.getFormFactor());
    }
    
    @Override
    public void addProduct(String features) throws Exception{
    	String[] strs = new String[5];
		strs = features.split(",");
		for (Board brd : Board.boards) {
			if(brd.getId()==Integer.parseInt(strs[0])) {board = brd ;}
		}
		
		for (CPU cp : CPU.cpus) {
			if(cp.getId()==Integer.parseInt(strs[1])) {proccessors = cp ;}
		}
		
		for (GPU gpu : GPU.gpus) {
			if(gpu.getId()==Integer.parseInt(strs[2])) {graphics = gpu ;}
		}
		
		for (RAM rm : RAM.rams) {
			if(rm.getId()==Integer.parseInt(strs[3])) {ram = rm;}
		}
		
		for (Storage str : Storage.storages) {
			if(str.getId()==Integer.parseInt(strs[4])) {storage = str ;}
		}
		id = systems.getLast().getId()+1;
		systems.add(this);
		products.add(this);
    }
    
    @Override
    public String getFeatures() {
    	String abc ;
    	abc = 	"İşlemci: " + proccessors.getBrand() +"\n"+
				"Ekran Kartı"+ graphics.getBrand() +"\n"+
				"Ram: "+ram.getBrand() +" " + ram.getCapacity() + "GB"+"\n"+
				"Depolama: "+storage.getBrand() + " " + storage.getCapacity() + "GB " +" "+ storage.getConnectType()+"\n"+
				"Anakart: " + board.getBrand() + " " + board.getSocket() + " " + board.getFormFactor();
    	return abc;
    }
    
    @Override
    public String getCategory() {
		return "Hazır Sistem";
	}
    
}

