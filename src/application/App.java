package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employees;

public class App {

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("Enter full file path:");
		String path = sc.nextLine();
		
		//Lendo arquivo
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employees> list = new ArrayList<>();
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employees(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();

		//Logica de filtragem pelo salario e ordenando email	
			List<String> email = list.stream()
					.filter(x -> x.getSalary() > salary)
					.map(x -> x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than: " + String.format("%.2f", salary) + ":");
			email.forEach(System.out::println);
			
		//Logica de filtragem pela 1letra do nome	
			double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y );
			
			System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));
			
		}catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}

}
