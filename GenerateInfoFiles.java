package Entrega;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.File;


public class GenerateInfoFiles {
	   private int numVendors;
	    private int numProducts;
	    private int numSalesPerVendor;

	    public GenerateInfoFiles(int numVendors, int numProducts, int numSalesPerVendor) {
	        this.numVendors = numVendors;
	        this.numProducts = numProducts;
	        this.numSalesPerVendor = numSalesPerVendor;
	    }

	    public void generateFiles(String outputFolder) {
	        try {
	            // Crear carpeta de salida si no existe
	            new File(outputFolder).mkdirs();

	            // Generar archivo de vendedores
	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFolder + "/vendedores.txt"))) {
	                for (int i = 1; i <= numVendors; i++) {
	                    writer.write("CI;" + i + ";Vendedor" + i + " Apellido" + i + "\n");
	                }
	            }

	            // Generar archivo de productos
	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFolder + "/productos.txt"))) {
	                for (int i = 1; i <= numProducts; i++) {
	                    writer.write("P" + i + ";Producto" + i + ";" + (10 + (int) (Math.random() * 91)) + "\n");
	                }
	            }

	            // Generar archivos de ventas por vendedor
	            Random random = new Random();
	            for (int i = 1; i <= numVendors; i++) {
	                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFolder + "/vendedor_" + i + ".txt"))) {
	                    for (int j = 0; j < numSalesPerVendor; j++) {
	                        int productId = random.nextInt(numProducts) + 1;
	                        int quantity = random.nextInt(10) + 1; // Cantidad vendida aleatoria entre 1 y 10
	                        writer.write("CI;" + i + ";P" + productId + ";" + quantity + "\n");
	                    }
	                }
	            }

	            System.out.println("Archivos generados exitosamente.");
	        } catch (IOException e) {
	            System.err.println("Error al generar archivos: " + e.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        GenerateInfoFiles generator = new GenerateInfoFiles(5, 10, 3); // Ejemplo de 5 vendedores, 10 productos, 3 ventas por vendedor
	        generator.generateFiles("output");
	    }

	
	
}
