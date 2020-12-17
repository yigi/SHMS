package ui;

import java.util.Scanner;

import domain.DeviceManager;

public class SHMSPanel 
{
	private Scanner scanner = new Scanner(System.in);
	private DeviceManager deviceManager;
	
	public SHMSPanel(DeviceManager deviceManager) 
	{
		this.deviceManager = deviceManager;
	}
	
	public void show() 
	{
		System.out.println("Smart Home Management System");
		System.out.println();
		while (true) 
		{
			displayMenu();
			int input = getNextInput("Choose 1, 2 or 3");
			switch (input) 
			{
			// buraya user interface
			}
		}
	}
	private void displayMenu() 
	{
		System.out.println("\n1. Manage Definitions\n" +
						   "2. Manage Devices\n" +
						   "3. Exit\n");
	}
	
	private int getNextInput(String text) 
	{
		System.out.println();
		System.out.print("> " + text + ": ");
		return scanner.nextInt();
	}

}
