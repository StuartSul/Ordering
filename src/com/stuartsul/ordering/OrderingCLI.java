package com.stuartsul.ordering;

import java.util.Scanner;

public class OrderingCLI {
	
	public static void cli() {
		System.out.print("Enter input: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		sc.close();
		
		int n = -1, r = -1, mode = -1, index = -1;

		if (input.length() > 10)
			errorExit(100);

		if ((index = input.indexOf('!')) != -1) {
			mode = 1;
			input = input.substring(0, index) + 'p' + input.substring(0, index);
		} else if ((index = input.indexOf('p')) != -1 || (index = input.indexOf('P')) != -1)
			mode = 2;
		else if ((index = input.indexOf('c')) != -1 || (index = input.indexOf('C')) != -1)
			mode = 3;
		else
			errorExit(101);

		try {
			n = Integer.parseInt(input.substring(0, index));
			r = Integer.parseInt(input.substring(index + 1));
		} catch (NumberFormatException e) {
			errorExit(102);
		}

		if (!(1 <= n && n <= 10 && 1 <= r && r <= 10 && n >= r))
			errorExit(103);

		Ordering ord = new Ordering(n, r, mode);
		ord.printOrdering();
	}

	public static void errorExit(int cause) {
		switch (cause) {
		case 100:
			System.out.println("Invalid input: input is too long");
			break;
		case 101:
			System.out.println("Invalid input: no appropriate operator");
			break;
		case 102:
			System.out.println("Invalid input: no appropriate integer found");
			break;
		case 103:
			System.out.println("Invalid input: integer size inappropriate");
			break;
		}

		System.exit(0);
	}
	
}
