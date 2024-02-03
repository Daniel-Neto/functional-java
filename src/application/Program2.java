package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter full file path: ");
		String path = sc.nextLine();
		System.out.println("Enter salary: ");
		double salary = sc.nextDouble();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();

			}
			
			// Obtém os emails em ordem alfabética dos usuários que possuem um salário maior do que o informado.
			List<String> emails = list.stream().filter(e -> e.getSalary() > salary).map(p -> p.getEmail()).sorted().collect(Collectors.toList());
			
			for(String email: emails) {
				System.out.println(email);
			}
			
			double sum = list.stream().filter(e -> e.getName().charAt(0) == 'M').map(p -> p.getSalary()).reduce(0.0, (e1,e2) -> e1+e2);
			
			System.out.println(sum);
			

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();

	}
}