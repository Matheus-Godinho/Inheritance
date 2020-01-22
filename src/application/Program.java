package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {

	public static final char COMMON = 'c';
	public static final char USED = 'u';
	public static final char IMPORTED = 'i';

	public static Product usedProduct(Scanner sc, String name, double price) throws ParseException {
		SimpleDateFormat sdf;
		Date manufactureDate;

		sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.printf("Manufacture date (DD/MM/YYYY): ");
		manufactureDate = sdf.parse(sc.next());
		return new UsedProduct(name, price, manufactureDate);
	}

	public static Product importedProduct(Scanner sc, String name, double price) {
		double customsFee;

		System.out.printf("Customs fee: ");
		customsFee = sc.nextDouble();
		return new ImportedProduct(name, price, customsFee);
	}

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> products;
		Product product;
		String name;
		double price;
		char option;
		int n;

		System.out.printf("Enter the number of products: ");
		n = sc.nextInt();
		products = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			System.out.printf("Product #%d data:%n", i);
			do {
				System.out.printf("Common, used or imported (c/u/i)? ");
				option = sc.next().charAt(0);
			} while (option != COMMON && option != USED && option != IMPORTED);
			sc.nextLine();
			System.out.printf("Name: ");
			name = sc.nextLine();
			System.out.printf("Price: ");
			price = sc.nextDouble();
			product = (option == COMMON) ? new Product(name, price)
					: (option == USED) ? usedProduct(sc, name, price) : importedProduct(sc, name, price);
			products.add(product);
		}
		
		System.out.printf("%nPRICE TAGS:%n");
		for (Product p: products)
			System.out.printf("%s%n", p.priceTag());

		sc.close();

	}

}
