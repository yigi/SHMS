package ui;

import java.util.Scanner;

import domain.DeviceManager;
import domain.Device;

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
			case 1:
				listDevices();
				displayManageDeviceMenu();
				
				int deviceInput = getNextInput("Choose 1, 2, 3 or 4)");
				
				switch (deviceInput) 
				{
				// Add Device
				case 1:
					System.out.println();
					System.out.print("Enter new device ID: ");
					int newID = scanner.nextInt();
					System.out.print("Enter new device info: ");
					String newInfo = scanner.next();
					System.out.print("Enter room: ");
					int newRoom = scanner.nextInt();
					System.out.print("Enter protocol: ");
					int newProtocol = scanner.nextInt();

					//buradan ekleyebiliriz device bu sekilde
					deviceManager.addDevice(newID, newInfo, newRoom, newProtocol);
					break;
					
				// update existing product
				}
			}
		}
	}
	private void displayMenu() 
	{
		System.out.println("\n1. Manage Devices\n" +
						   "2. Manage Definitions\n" +
						   "3. Exit\n");
	}
	
	private void listDevices() 
	{
		System.out.printf("%n-----MANAGE DEVICES------%n");
		System.out.printf("Device ID         Device Info       Protocol   Room%n");
		
		for (Device d : deviceManager.getDeviceList()) 
		{
			System.out.printf(" %d        %d                 %d                %d             %n", 
					d.getDeviceID(), 
					d.getDeviceInfo(),
					d.getProtocol(),
					d.getRoom());
		}
	}
	
	private void displayManageDeviceMenu() 
	{
		System.out.println("\n1. Add New Device\n" + 
				"2. Update Device\n" + 
				"3. Delete Device\n" +
				"4. Main Menu\n");	
	}
	
	private int getNextInput(String text) 
	{
		System.out.println();
		System.out.print("> " + text + ": ");
		return scanner.nextInt();
	}

}
