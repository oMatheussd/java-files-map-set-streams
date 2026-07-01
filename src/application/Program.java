package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import entities.Loan;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre com o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Loan> loans = new ArrayList<>();
			Set<String> readers = new TreeSet<>();
			Set<String> books = new TreeSet<>();
			Map<String, Integer> daysByGenre = new TreeMap<>();
			Map<String, Integer> totalDaysByReader = new TreeMap<>();

			String line = br.readLine();
			while (line != null) {

				String[] fields = line.split(",");
				loans.add(new Loan(fields[0], fields[1], fields[2], Integer.parseInt(fields[3])));
				line = br.readLine();
			}

			for (Loan loan : loans) {

				String readerName = loan.getReadersName();
				String genre = loan.getGenre();

				readers.add(readerName);

				books.add(loan.getBookTitle());

				if (daysByGenre.containsKey(genre)) {
					int totalDays = daysByGenre.get(genre);
					daysByGenre.put(genre, loan.getLoanDays() + totalDays);
				} else {
					daysByGenre.put(genre, loan.getLoanDays());
				}

				if (totalDaysByReader.containsKey(readerName)) {
					int totalDays = totalDaysByReader.get(readerName);
					totalDaysByReader.put(readerName, loan.getLoanDays() + totalDays);
				} else {
					totalDaysByReader.put(readerName, loan.getLoanDays());
				}

			}

			System.out.println("Quantidade de leitores: " + readers.size());

			double avg = loans.stream().mapToInt(loan -> loan.getLoanDays()).average().orElse(0.0);

			System.out.println();
			System.out.printf("Tempo médio dos empréstimos: %.2f dias%n", avg);

			System.out.println();
			System.out.println("Livros emprestados:");
			books.forEach(System.out::println);

			System.out.println();
			System.out.println("Dias por gênero:");
			for (String genre : daysByGenre.keySet()) {
				System.out.println(genre + ": " + daysByGenre.get(genre));
			}

			System.out.println();
			System.out.println("Leitor com maior tempo de empréstimo:");
			System.out.println(findTopReader(totalDaysByReader));

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		sc.close();
	}

	public static String findTopReader(Map<String, Integer> totalDaysByReader) {

		String reader = "";
		int maxDays = 0;

		for (String readerName : totalDaysByReader.keySet()) {
			int totalDays = totalDaysByReader.get(readerName);
			if (totalDays > maxDays) {
				maxDays = totalDays;
				reader = readerName;
			}
		}

		return reader + " - " + maxDays + " dias";
	}
}