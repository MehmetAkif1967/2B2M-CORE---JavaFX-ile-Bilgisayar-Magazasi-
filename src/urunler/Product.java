package urunler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import application.Inter;

public class Product implements Inter{
	public static ArrayList<Product> products = new ArrayList<>();
	protected int stock ;
	public int id ;
	protected String brand ;
	public FileWriter writer ;
	protected double price ;
	public String title ;
	
	public Product(int stock, int id, String brand, double price) {
		//super();
		this.stock = stock;
		this.id = id;
		this.brand = brand;
		this.price = price ;
        //try {writer = new FileWriter("boards.txt");}
		//catch (Exception e) {System.out.println("Hata oluştu");}

	}

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		if(stock >=0) {
			this.stock = stock;
			GPU theGpu ; CPU theCpu ; RAM theRam ; Board theBoard; Storage theStorer; PreparedSystem system ;
			 if(this.getClass().getName().equals("GPU")) {
				 theGpu = (GPU) this ;
				 for (GPU gpu : GPU.gpus) {
					if(gpu.equals(theGpu)) {gpu.setStock(stock);}
				}
				 }
			 else if(this.getClass().getName().equals("CPU")) {
				 theCpu = (CPU) this ;
				 for (CPU cpu : CPU.cpus) {
					if(cpu.equals(theCpu)) {cpu.setStock(stock);}
				 }
			 	}
			 else if(this.getClass().getName().equals("RAM")) {
				 theRam = (RAM) this ;
				 for (RAM ram : RAM.rams) {
					if(ram.equals(theRam)) {ram.setStock(stock);}
				}
				 }
			 else if(this.getClass().getName().equals("Board")) {
				 theBoard = (Board) this ;
				 for (Board board : Board.boards) {
					if(board.equals(theBoard)) {board.setStock(stock);}
				}
				 }
			 else if(this.getClass().getName().equals("Storage")) {
				 theStorer = (Storage) this;
				 for (Storage storage : Storage.storages) {
					if(storage.equals(theStorer)) {storage.setStock(stock);}
				}
				 }
			 else if(this.getClass().getName().equals("PreparedSystem")) {
				 system = (PreparedSystem) this ;
				 for (PreparedSystem sys : PreparedSystem.systems) {
					if(sys.equals(system)) {
						sys.setStock(stock);
						sys.getGpu().setStock(stock);
						sys.getCpu().setStock(stock);
						sys.getRam().setStock(stock);
						sys.getBoard().setStock(stock);
						sys.getStorage().setStock(stock);
						}
				}
				 }
		}
		else {System.out.println("Yanlış stok bilgisi");}
	}
	
	@Override
	public void setModel(String model) {
		
	}
	
	@Override
	public void addProduct(String features) throws Exception {

		products.add(this);
	}
	
	@Override
	public String getFeatures() {return "";}
	
	@Override
	public String getCategory() {//düzelt
		String kategori = this.getClass().getName();
		 return kategori.substring(8) ;
	}
	
}
