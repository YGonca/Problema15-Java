package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entitites.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();
		
		System.out.print("Path: ");
		String pathIn = sc.nextLine();
		File file = new File(pathIn);
		String pathOut = file.getParent();
		new File(pathOut + "\\out").mkdir();
		
		try(BufferedReader br = new BufferedReader(new FileReader(pathIn)); BufferedWriter bw = new BufferedWriter(new FileWriter(pathOut + "\\out\\summary.csv"))) {
			String line = br.readLine();
			while(line != null) {
				System.out.println(line);
				String[] vect = line.split(",");
				String name = vect[0];
				Double price = Double.parseDouble(vect[1]);
				Integer quantity = Integer.parseInt(vect[2]);
				list.add(new Product(name, price, quantity));
				line = br.readLine();
			}
			for(Product l : list) {
				bw.write(l.toString());
				bw.newLine();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
	}
}
