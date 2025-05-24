package application;
import java.sql.*;
import java.util.ArrayList;

import urunler.Board;
import urunler.CPU;
import urunler.GPU;
import urunler.PreparedSystem;
import urunler.Product;
import urunler.RAM;
import urunler.Storage;

public class SqLiteConnection {
	public static Connection connector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:app.db");
			return conn ;
		} catch (Exception e) {
			return null ;
		}
	}
	public static void insertGPUs(ArrayList<GPU> gpuList) {
	    String url = "jdbc:sqlite:app.db";

	    String createTable = "CREATE TABLE IF NOT EXISTS gpus (" +
	            "id INTEGER PRIMARY KEY," +
	            "brand TEXT," +
	            "price REAL," +
	            "stock INTEGER," +
	            "model TEXT," +
	            "vram REAL," +
	            "ocable INTEGER," +
	            "memType TEXT," +
	            "clockRate INTEGER)";

	    String deleteSQL = "DELETE FROM gpus"; // Tablodaki tüm verileri siler

	    String insertSQL = "INSERT INTO gpus(id, brand, price, stock, model, vram, ocable, memType, clockRate) " +
	                       "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(url)) {
	        conn.setAutoCommit(false); // Tüm işlemleri tek bir transaction içinde yap

	        // Tablo oluşturulmamışsa oluştur
	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(createTable);
	        }

	        // Eski verileri temizle
	        try (Statement stmt = conn.createStatement()) {
	            stmt.executeUpdate(deleteSQL);
	        }

	        // Yeni verileri ekle
	        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
	            for (GPU gpu : gpuList) {
	                pstmt.setInt(1, gpu.getId());
	                pstmt.setString(2, gpu.getBrand());
	                pstmt.setDouble(3, gpu.getPrice());
	                pstmt.setInt(4, gpu.getStock());
	                pstmt.setString(5, gpu.getModel());
	                pstmt.setDouble(6, gpu.getVram());
	                pstmt.setInt(7, gpu.isocable() ? 1 : 0);
	                pstmt.setString(8, gpu.getMemType());
	                pstmt.setInt(9, gpu.getClockRate());

	                pstmt.executeUpdate();
	            }
	        }

	        conn.commit(); // İşlemleri veritabanına kaydet
	        System.out.println("GPU verileri başarıyla güncellendi.");

	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	
	public static void fillGPUListFromDB(ArrayList<GPU> gpuList) {
	    String url = "jdbc:sqlite:app.db";
	    String query = "SELECT * FROM gpus"; // Tablo adı güncellendi

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            double price = rs.getDouble("price");
	            int stock = rs.getInt("stock");
	            int id = rs.getInt("id");
	            String brand = rs.getString("brand");
	            String model = rs.getString("model");
	            double vram = rs.getDouble("vram");
	            boolean ocable = rs.getInt("ocable") == 1;
	            String memType = rs.getString("memType");
	            int clockRate = rs.getInt("clockRate");

	            GPU gpu = new GPU(price, stock, id, brand, model, vram, ocable, memType, clockRate);
	            gpuList.add(gpu);
	        }

	        System.out.println("Veriler başarıyla listeye eklendi.");

	    } catch (SQLException e) {
	        System.out.println("Veritabanı okuma hatası: " + e.getMessage());
	    }
	}
	
	public static void insertCPUs(ArrayList<CPU> cpuList) {
	    String url = "jdbc:sqlite:app.db";

	    String createTable = "CREATE TABLE IF NOT EXISTS cpus (" +
	            "id INTEGER PRIMARY KEY," +
	            "brand TEXT," +
	            "price REAL," +
	            "stock INTEGER," +
	            "model TEXT," +
	            "coreNum INTEGER," +
	            "threadNum INTEGER," +
	            "frequancy REAL," +
	            "socket TEXT," +
	            "ramType TEXT," +
	            "ocable INTEGER)";

	    String deleteSQL = "DELETE FROM cpus";

	    String insertSQL = "INSERT INTO cpus(id, brand, price, stock, model, coreNum, threadNum, frequancy, socket, ramType, ocable) " +
	            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(url)) {
	        conn.setAutoCommit(false);

	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(createTable);
	            stmt.executeUpdate(deleteSQL);
	        }

	        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
	            for (CPU cpu : cpuList) {
	                pstmt.setInt(1, cpu.getId());
	                pstmt.setString(2, cpu.getBrand());
	                pstmt.setDouble(3, cpu.getPrice());
	                pstmt.setInt(4, cpu.getStock());
	                pstmt.setString(5, cpu.getModel());
	                pstmt.setInt(6, cpu.getCoreNum());
	                pstmt.setInt(7, cpu.getThreadNum());
	                pstmt.setDouble(8, cpu.getFrequancy());
	                pstmt.setString(9, cpu.getSocket());
	                pstmt.setString(10, cpu.getRamType());
	                pstmt.setInt(11, cpu.isOcable() ? 1 : 0);

	                pstmt.executeUpdate();
	            }
	        }

	        conn.commit();
	        System.out.println("CPU verileri başarıyla güncellendi.");

	    } catch (SQLException e) {
	        System.out.println("CPU veritabanı hatası: " + e.getMessage());
	    }
	}
	
	public static void fillCPUListFromDB(ArrayList<CPU> cpuList) {
	    String url = "jdbc:sqlite:app.db";
	    String query = "SELECT * FROM cpus";

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            double price = rs.getDouble("price");
	            int stock = rs.getInt("stock");
	            int id = rs.getInt("id");
	            String brand = rs.getString("brand");
	            String model = rs.getString("model");
	            int coreNum = rs.getInt("coreNum");
	            int threadNum = rs.getInt("threadNum");
	            double frequancy = rs.getDouble("frequancy");
	            String socket = rs.getString("socket");
	            String ramType = rs.getString("ramType");
	            boolean ocable = rs.getInt("ocable") == 1;

	            CPU cpu = new CPU(price, stock, id, brand, model, coreNum, threadNum, frequancy, socket, ramType, ocable);
	            cpuList.add(cpu);
	        }

	        System.out.println("CPU verileri başarıyla listeye eklendi.");

	    } catch (SQLException e) {
	        System.out.println("CPU veritabanı okuma hatası: " + e.getMessage());
	    }
	}
	
	public static void insertRAMs(ArrayList<RAM> ramList) {
	    String url = "jdbc:sqlite:app.db";

	    String createTable = "CREATE TABLE IF NOT EXISTS rams (" +
	            "id INTEGER PRIMARY KEY," +
	            "brand TEXT," +
	            "price REAL," +
	            "stock INTEGER," +
	            "model TEXT," +
	            "capacity INTEGER," +
	            "frequancy REAL," +
	            "memType TEXT," +
	            "ocable INTEGER)";

	    String deleteSQL = "DELETE FROM rams";

	    String insertSQL = "INSERT INTO rams(id, brand, price, stock, model, capacity, frequancy, memType, ocable) " +
	                       "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(url)) {
	        conn.setAutoCommit(false);

	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(createTable);
	            stmt.executeUpdate(deleteSQL);
	        }

	        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
	            for (RAM ram : ramList) {
	                pstmt.setInt(1, ram.getId());
	                pstmt.setString(2, ram.getBrand());
	                pstmt.setDouble(3, ram.getPrice());
	                pstmt.setInt(4, ram.getStock());
	                pstmt.setString(5, ram.getModel());
	                pstmt.setInt(6, ram.getCapacity());
	                pstmt.setDouble(7, ram.getFrequancy());
	                pstmt.setString(8, ram.getMemType());
	                pstmt.setInt(9, ram.isOcable() ? 1 : 0);

	                pstmt.executeUpdate();
	            }
	        }

	        conn.commit();
	        System.out.println("RAM verileri başarıyla güncellendi.");

	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}
	
	public static void fillRAMListFromDB(ArrayList<RAM> ramList) {
	    String url = "jdbc:sqlite:app.db";
	    String query = "SELECT * FROM rams";

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            double price = rs.getDouble("price");
	            int stock = rs.getInt("stock");
	            int id = rs.getInt("id");
	            String brand = rs.getString("brand");
	            String model = rs.getString("model");
	            int capacity = rs.getInt("capacity");
	            double frequancy = rs.getDouble("frequancy");
	            String memType = rs.getString("memType");
	            boolean ocable = rs.getInt("ocable") == 1;

	            RAM ram = new RAM(price, stock, id, brand, model, capacity, frequancy, memType, ocable);
	            ramList.add(ram);
	        }

	        System.out.println("RAM verileri başarıyla listeye eklendi.");

	    } catch (SQLException e) {
	        System.out.println("Veritabanı okuma hatası: " + e.getMessage());
	    }
	}
	
	public static void insertBoards(ArrayList<Board> boardList) {
	    String url = "jdbc:sqlite:app.db";

	    String createTable = "CREATE TABLE IF NOT EXISTS boards (" +
	            "id INTEGER PRIMARY KEY," +
	            "brand TEXT," +
	            "model TEXT," +
	            "price REAL," +
	            "stock INTEGER," +
	            "socket TEXT," +
	            "chipset TEXT," +
	            "memType TEXT," +
	            "memSlots INTEGER," +
	            "formFactor TEXT)";

	    String deleteSQL = "DELETE FROM boards";

	    String insertSQL = "INSERT INTO boards(id, brand,model, price, stock, socket, chipset, memType, memSlots, formFactor) " +
	            "VALUES(?, ?,?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(url)) {
	        conn.setAutoCommit(false);

	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(createTable);
	            stmt.executeUpdate(deleteSQL);
	        }

	        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
	            for (Board board : boardList) {
	                pstmt.setInt(1, board.getId());
	                pstmt.setString(2, board.getBrand());
	                pstmt.setString(3,board.getModel());
	                pstmt.setDouble(4, board.getPrice());
	                pstmt.setInt(5, board.getStock());
	                pstmt.setString(6, board.getSocket());
	                pstmt.setString(7, board.getChipset());
	                pstmt.setString(8, board.getMemType());
	                pstmt.setInt(9, board.getMemSlots());
	                pstmt.setString(10, board.getFormFactor());

	                pstmt.executeUpdate();
	            }
	        }

	        conn.commit();
	        System.out.println("Board verileri başarıyla güncellendi.");
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}
	
	public static void fillBoardListFromDB(ArrayList<Board> boardList) {
	    String url = "jdbc:sqlite:app.db";
	    String query = "SELECT * FROM boards";

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            double price = rs.getDouble("price");
	            int stock = rs.getInt("stock");
	            int id = rs.getInt("id");
	            String brand = rs.getString("brand");
	            String socket = rs.getString("socket");
	            String chipset = rs.getString("chipset");
	            String memType = rs.getString("memType");
	            int memSlots = rs.getInt("memSlots");
	            String formFactor = rs.getString("formFactor");
	            String model = rs.getString("model");

	            Board board = new Board(price, stock, id, brand, socket, chipset, memType, memSlots, formFactor,model);
	            boardList.add(board);
	        }

	        System.out.println("Board verileri başarıyla listeye eklendi.");

	    } catch (SQLException e) {
	        System.out.println("Veritabanı okuma hatası: " + e.getMessage());
	    }
	}
	
	public static void insertStorages(ArrayList<Storage> storageList) {
	    String url = "jdbc:sqlite:app.db";

	    String createTable = "CREATE TABLE IF NOT EXISTS storages (" +
	            "id INTEGER PRIMARY KEY," +
	            "brand TEXT," +
	            "model TEXT," +
	            "price REAL," +
	            "stock INTEGER," +
	            "connectType TEXT," +
	            "capacity INTEGER," +
	            "interfaceType TEXT," +
	            "readSpeed REAL," +
	            "writeSpeed REAL)";

	    String deleteSQL = "DELETE FROM storages";

	    String insertSQL = "INSERT INTO storages(id, brand,model, price, stock, connectType, capacity, interfaceType, readSpeed, writeSpeed) " +
	            "VALUES(?, ?, ?,?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(url)) {
	        conn.setAutoCommit(false);

	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(createTable);
	            stmt.executeUpdate(deleteSQL);
	        }

	        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
	            for (Storage s : storageList) {
	                pstmt.setInt(1, s.getId());
	                pstmt.setString(2, s.getBrand());
	                pstmt.setString(3,s.getModel());
	                pstmt.setDouble(4, s.getPrice());
	                pstmt.setInt(5, s.getStock());
	                pstmt.setString(6, s.getConnectType());
	                pstmt.setInt(7, s.getCapacity());
	                pstmt.setString(8, s.getInterfaceType());
	                pstmt.setDouble(9, s.getReadSpeed());
	                pstmt.setDouble(10, s.getWriteSpeed());
	                pstmt.executeUpdate();
	            }
	        }

	        conn.commit();
	        System.out.println("Storage verileri başarıyla güncellendi.");
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}
	
	public static void fillStorageListFromDB(ArrayList<Storage> storageList) {
	    String url = "jdbc:sqlite:app.db";
	    String query = "SELECT * FROM storages";

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            double price = rs.getDouble("price");
	            int stock = rs.getInt("stock");
	            int id = rs.getInt("id");
	            String brand = rs.getString("brand");
	            String connectType = rs.getString("connectType");
	            int capacity = rs.getInt("capacity");
	            String interfaceType = rs.getString("interfaceType");
	            double readSpeed = rs.getDouble("readSpeed");
	            double writeSpeed = rs.getDouble("writeSpeed");
	            String model = rs.getString("model");

	            Storage s = new Storage(price, stock, id, brand,model, connectType, capacity, interfaceType, readSpeed, writeSpeed);
	            storageList.add(s);
	        }

	        System.out.println("Storage verileri başarıyla listeye eklendi.");
	    } catch (SQLException e) {
	        System.out.println("Veritabanı okuma hatası: " + e.getMessage());
	    }
	}
	
	public static void fillPreparedSystemsFromDB(ArrayList<PreparedSystem> system) {
	    String url = "jdbc:sqlite:app.db";
	    String query = "SELECT * FROM preparedsystems";

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int stock = rs.getInt("stock");
	            String brand = rs.getString("brand");
	            double price = rs.getDouble("price");

	            int cpuId = rs.getInt("cpu_id");
	            int gpuId = rs.getInt("gpu_id");
	            int ramId = rs.getInt("ram_id");
	            int storageId = rs.getInt("storage_id");
	            int boardId = rs.getInt("board_id");

	            CPU cpu = CPU.cpus.stream()
	                    .filter(c -> c.getId() == cpuId)
	                    .findFirst()
	                    .orElseThrow(() -> new RuntimeException("CPU with id " + cpuId + " not found"));

	            GPU gpu = GPU.gpus.stream()
	                    .filter(g -> g.getId() == gpuId)
	                    .findFirst()
	                    .orElseThrow(() -> new RuntimeException("GPU with id " + gpuId + " not found"));

	            RAM ram = RAM.rams.stream()
	                    .filter(r -> r.getId() == ramId)
	                    .findFirst()
	                    .orElseThrow(() -> new RuntimeException("RAM with id " + ramId + " not found"));

	            Storage storage = Storage.storages.stream()
	                    .filter(s -> s.getId() == storageId)
	                    .findFirst()
	                    .orElseThrow(() -> new RuntimeException("Storage with id " + storageId + " not found"));

	            Board board = Board.boards.stream()
	                    .filter(b -> b.getId() == boardId)
	                    .findFirst()
	                    .orElseThrow(() -> new RuntimeException("Board with id " + boardId + " not found"));

	            PreparedSystem ps = new PreparedSystem(price, stock, id, brand, cpu, gpu, ram, storage, board);
	            system.add(ps);
	        }

	        System.out.println("PreparedSystems verileri başarıyla yüklendi.");

	    } catch (SQLException e) {
	        System.out.println("Veritabanı okuma hatası: " + e.getMessage());
	    } catch (RuntimeException e) {
	        System.out.println("Hata: " + e.getMessage());
	    }
	}
	
	public static void insertPreparedSystems(ArrayList<PreparedSystem> systems) {
	    String url = "jdbc:sqlite:app.db";
	    
	    String createTable = "CREATE TABLE IF NOT EXISTS preparedsystems (" +
	            "id INTEGER PRIMARY KEY," +
	            "stock INTEGER," +
	            "brand TEXT," +
	            "price REAL," +
	            "cpu_id INTEGER, cpu_brand TEXT, cpu_model TEXT," +
	            "gpu_id INTEGER, gpu_brand TEXT, gpu_model TEXT," +
	            "ram_id INTEGER, ram_brand TEXT, ram_model TEXT," +
	            "storage_id INTEGER, storage_brand TEXT, storage_model TEXT," +
	            "board_id INTEGER, board_brand TEXT, board_model TEXT" +
	            ")";
	    
	    String deleteSQL = "DELETE FROM preparedsystems";
	    
	    String insertSQL = "INSERT INTO preparedsystems (id, stock, brand, price, " +
	            "cpu_id, cpu_brand, cpu_model, " +
	            "gpu_id, gpu_brand, gpu_model, " +
	            "ram_id, ram_brand, ram_model, " +
	            "storage_id, storage_brand, storage_model, " +
	            "board_id, board_brand, board_model) " +
	            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = DriverManager.getConnection(url)) {
	        conn.setAutoCommit(false);
	        
	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(createTable);
	            stmt.executeUpdate(deleteSQL);
	        }
	        
	        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
	            for (PreparedSystem system : systems) {
	                pstmt.setInt(1, system.getId());
	                pstmt.setInt(2, system.getStock());
	                pstmt.setString(3, system.getBrand());
	                pstmt.setDouble(4, system.getPrice());
	                
	                CPU cpu = system.getCpu();
	                pstmt.setInt(5, cpu.getId());
	                pstmt.setString(6, cpu.getBrand());
	                pstmt.setString(7, cpu.getModel());
	                
	                GPU gpu = system.getGpu();
	                pstmt.setInt(8, gpu.getId());
	                pstmt.setString(9, gpu.getBrand());
	                pstmt.setString(10, gpu.getModel());
	                
	                RAM ram = system.getRam();
	                pstmt.setInt(11, ram.getId());
	                pstmt.setString(12, ram.getBrand());
	                pstmt.setString(13, ram.getModel());
	                
	                Storage storage = system.getStorage();
	                pstmt.setInt(14, storage.getId());
	                pstmt.setString(15, storage.getBrand());
	                pstmt.setString(16, storage.getModel());
	                
	                Board board = system.getBoard();
	                pstmt.setInt(17, board.getId());
	                pstmt.setString(18, board.getBrand());
	                pstmt.setString(19, board.getModel());
	                
	                pstmt.executeUpdate();
	            }
	        }
	        
	        conn.commit();
	        System.out.println("PreparedSystems başarıyla veritabanına kaydedildi.");
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}
	
	public static void insertLabors(ArrayList<Labor> laborList) {
	    String url = "jdbc:sqlite:app.db";

	    String createTable = "CREATE TABLE IF NOT EXISTS labors (" +
	            "userName TEXT PRIMARY KEY," +
	            "password TEXT," +
	            "role TEXT," +
	            "laborid INTEGER)";

	    String deleteSQL = "DELETE FROM labors";

	    String insertSQL = "INSERT INTO labors(userName, password, role, laborid) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(url)) {
	        conn.setAutoCommit(false);

	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(createTable);
	            stmt.executeUpdate(deleteSQL);
	        }

	        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
	            for (Labor labor : laborList) {
	                pstmt.setString(1, labor.getUserName());
	                pstmt.setString(2, labor.getPassword());
	                pstmt.setString(3, "Labor"); // rol sabit
	                pstmt.setInt(4, labor.getLaborid());
	                pstmt.executeUpdate();
	            }
	        }

	        conn.commit();
	        System.out.println("Labor kullanıcıları başarıyla veritabanına kaydedildi.");
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}
	
	public static void fillLaborListFromDB(ArrayList<Labor> laborList) {
	    String url = "jdbc:sqlite:app.db";
	    String query = "SELECT * FROM labors";

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            String userName = rs.getString("userName");
	            String password = rs.getString("password");
	            int laborid = rs.getInt("laborid");

	            Labor labor = new Labor(userName, password, laborid);
	            laborList.add(labor);
	        }

	        System.out.println("Labor kullanıcıları başarıyla listeye yüklendi.");
	    } catch (SQLException e) {
	        System.out.println("Veritabanı okuma hatası: " + e.getMessage());
	    }
	}
	
	public static void insertCustomers(ArrayList<Customer> customerList) {
	    String url = "jdbc:sqlite:app.db";

	    String createTable = "CREATE TABLE IF NOT EXISTS customers (" +
	            "userName TEXT PRIMARY KEY," +
	            "password TEXT," +
	            "userType TEXT)";

	    String deleteSQL = "DELETE FROM customers";

	    String insertSQL = "INSERT INTO customers(userName, password, userType) VALUES (?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(url)) {
	        conn.setAutoCommit(false);

	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(createTable);
	            stmt.executeUpdate(deleteSQL);
	        }

	        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
	            for (Customer customer : customerList) {
	                pstmt.setString(1, customer.getUserName());
	                pstmt.setString(2, customer.getPassword());
	                pstmt.setString(3, customer.getUserType());
	                pstmt.executeUpdate();
	            }
	        }

	        conn.commit();
	        System.out.println("Customer verileri başarıyla eklendi.");
	    } catch (SQLException e) {
	        System.out.println("Veritabanı hatası: " + e.getMessage());
	    }
	}
	
	public static void fillCustomerListFromDB(ArrayList<Customer> customerList) {
	    String url = "jdbc:sqlite:app.db";
	    String query = "SELECT * FROM customers";

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            String userName = rs.getString("userName");
	            String password = rs.getString("password");
	            String userType = rs.getString("userType");

	            Customer customer = new Customer(userName, password);
	            customerList.add(customer);
	        }

	        System.out.println("Customer verileri başarıyla listeye eklendi.");
	    } catch (SQLException e) {
	        System.out.println("Veritabanı okuma hatası: " + e.getMessage());
	    }
	}

	
	public static void insertAll() {
		insertBoards(Board.boards);
		insertCPUs(CPU.cpus);
		insertGPUs(GPU.gpus);
		insertRAMs(RAM.rams);
		insertStorages(Storage.storages);
		insertPreparedSystems(PreparedSystem.systems);
		insertCustomers(Customer.customers);
		insertLabors(Labor.labors);
	}
	
	public static void fillAll() {
		fillCPUListFromDB(CPU.cpus);
        fillGPUListFromDB(GPU.gpus);
        fillBoardListFromDB(Board.boards);
        fillRAMListFromDB(RAM.rams);
        fillStorageListFromDB(Storage.storages);
        fillPreparedSystemsFromDB(PreparedSystem.systems);
        fillCustomerListFromDB(Customer.customers);
        fillLaborListFromDB(Labor.labors);
        Product.products.addAll(GPU.gpus);
        Product.products.addAll(CPU.cpus);
        Product.products.addAll(Board.boards);
        Product.products.addAll(RAM.rams);
        Product.products.addAll(Storage.storages);
        Product.products.addAll(PreparedSystem.systems);
	}
	
}
