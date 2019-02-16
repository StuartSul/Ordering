package com.stuartsul.ordering;

public class Main {

	public static void main(String[] args) {
		if (args.length == 1 && args[0].equals("cli")) OrderingCLI.cli();
		else OrderingGUI.gui();
	}

}
