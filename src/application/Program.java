package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Product> list = new ArrayList<>();
			
			String line = br.readLine();
			while(line!=null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
				
			}
			
			// Obtém a média dos produtos
			double avg = list.stream().map(p -> p.getPrice()).reduce(0.0, (x,y) -> x + y) / list.size();
			
			System.out.println("Preço médio: " + avg);
			
			// Pega os produtos que possuem preço abaixo da média e ordena seus nomes em ordem decrescente.
			List<String> names = list.stream().filter(p->p.getPrice() < avg).map(p->p.getName())
					.sorted((s1,s2) -> -(s1.toUpperCase().compareTo(s2.toUpperCase())))
					.collect(Collectors.toList());
			
			for(String name:names) {
				System.out.println(name);
			}
			
		
		} 
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();

	}

}
