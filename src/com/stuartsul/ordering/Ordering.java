package com.stuartsul.ordering;

public class Ordering {
	
	private int n;
	private int r;
	private int mode; // 1 = factorial, 2 = permutation, 3 = combination
	private int size;
	private int[][] ordering;

	public Ordering(int n, int r, int mode) {
		this.n = n;
		this.r = r;
		this.mode = mode;

		if (mode == 1 || mode == 2) {
			this.size = calculatePermutation(n, r);
			this.ordering = new int[this.size][r];
			permutation();
		} else if (mode == 3) {
			this.size = calculateCombination(n, r);
			this.ordering = new int[this.size][r];
			combination();
		}
	}

	public int[][] getOrdering() {
		return this.ordering;
	}

	public static int calculatePermutation(int n, int r) {
		int permutation = 1;

		for (int i = n; i > n - r; i--)
			permutation *= i;

		return permutation;
	}

	public static int calculateCombination(int n, int r) {
		int combination = calculatePermutation(n, r);

		for (int i = r; i > 1; i--)
			combination /= i;

		return combination;
	}

	private void permutation() {
		int shouldChange;
		int[] cycleCount = new int[this.r];
		boolean[] isChosen = new boolean[this.n];

		for (int i = 0, count = this.size; i < r; i++) {
			count /= this.n - i;
			cycleCount[i] = count;
			isChosen[i] = false;
		}

		for (int count = 0; count < this.size; count++) {
			for (int index = 0, number = 0; index < r; index++) {
				shouldChange = (count / cycleCount[index]) % (this.n - index);

				while (true) {
					if (!isChosen[number]) {
						if (shouldChange > 0) {
							number++;
							if (number >= n)
								number = 0;
							shouldChange--;
							continue;
						} else {
							this.ordering[count][index] = number + 1;
							isChosen[number] = true;
							break;
						}
					} else {
						number++;
						if (number >= n)
							number = 0;
					}
				}
			}

			for (int i = 0; i < n; i++)
				isChosen[i] = false;
		}
	}

	private void combination() {
		int changeIndex = -1;
		int[] set = new int[this.r];

		for (int i = 0; i < this.r; i++)
			set[i] = i + 1;

		for (int count = 0; count < this.size; count++) {
			for (int index = 0; index < this.r; index++) {
				if (index == changeIndex) {
					set[index]++;
					changeIndex = -1;
					for (int i = index + 1; i < this.r; i++)
						set[i] = set[i - 1] + 1;
				}

				this.ordering[count][index] = set[index];

				if (set[index] == n - r + index + 1 && changeIndex == -1)
					changeIndex = index - 1;
			}

			if (changeIndex == -1)
				changeIndex = this.r - 1;
		}
	}

	public void printOrdering() {
		String operation = "";

		if (mode == 1)
			operation = "factorial " + n + "!";
		else if (mode == 2)
			operation = "permutation " + n + "P" + r;
		else if (mode == 3)
			operation = "combination " + n + "C" + r;

		System.out.println("Printing " + operation + ":");

		for (int i = 0; i < this.size; i++) {
			System.out.print((i + 1) + ": (");
			for (int j = 0; j < this.r; j++) {
				System.out.print(this.ordering[i][j]);
				if (j < this.r - 1)
					System.out.print(", ");
				else
					System.out.println(")");
			}
		}
	}
	
}
