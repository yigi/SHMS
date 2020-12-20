package ui;

import java.util.Scanner;

import domain.DeviceManager;
import domain.NetworkProtocol;
import domain.Room;
import domain.Sensor;
import domain.DefinitionManager;
import domain.Device;
import domain.DeviceInfo;

public class SHMSPanel 
{
	private Scanner scanner = new Scanner(System.in);
	private DeviceManager deviceManager;
	private DefinitionManager definitionManager;

	public SHMSPanel(DeviceManager deviceManager,DefinitionManager definitionManager) 
	{
		this.deviceManager = deviceManager;
		this.definitionManager = definitionManager;
	}

	public void show() 
	{
		System.out.println("Smart Home Management System");
		while (true) 
		{
			displayMenu();
			int input = getNextInput("Choose 1, 2 or 3");
			switch (input) 
			{
			// Manage Devices
			case 1:
				listDevices();
				displayManageDeviceMenu();

				int deviceInput = getNextInput("Choose 1, 2, 3 or 4)");

				switch (deviceInput) 
				{
				// Add Device
				case 1:
				case 2:
				case 3:
					deviceManager.manageDevices(deviceInput);
					break;
					//List devices
				case 4:
					listDevices();
					break;
				default:
					break;
				}
				break;
			// Manage Definitions
			case 2:
				listDefinitions();
				int definitonInput = getNextInput("Choose 1, 2 or 3)");
				switch (definitonInput) 
				{
				// Room
				case 1:
					listRooms();
					listRoomDefinitions();
					
					int definitonRoomInput = getNextInput("Choose 1, 2, 3 or 4)");
					
					switch (definitonRoomInput) 
					{
						case 1:
						case 2:
						case 3:
							definitionManager.manageRooms(definitonRoomInput);
							break;
						case 4:
							listRooms();
							break;
						default:
							break;
					}
					break;
				case 2:
					listDeviceTypes();
					listDeviceTypeDefinitions();
					
					int definitonDeviceTypeInput = getNextInput("Choose 1, 2, 3 or 4)");
					
					switch (definitonDeviceTypeInput) 
					{
						case 1:
						case 2:
						case 3:
							definitionManager.manageDeviceTypes(definitonDeviceTypeInput);
							break;
						case 4:
							listDeviceTypes();
							break;
						default:
							break;
					}
					break;
				// Protocol 
				case 3:
					handleNetworkProtocol();
					break;
				}
			case 3:
				System.out.println();
				System.out.printf("Returning Main Menu... ");
				System.out.println();
				break;
			default:
				displayMenu();
				break;
			}
			if (input == 3)
				break;
		}
	}
	
	private void handleNetworkProtocol()
	{
		listNetworkProtocols();
		listNetworkProtocolDefinitions();
		
		int number = getNextInput("Choose 1, 2, 3 or 4)");
		
		switch(number)
		{
			case 1:
			case 2:
			case 3:
				definitionManager.manageNetworkProtocol(number);
				break;
			case 4:
				listNetworkProtocols();
				break;
			default:
				break;
		}
		

	}
	
	private void listRooms()
	{
		System.out.printf("%n------------------ROOMS-------------------%n");
		System.out.printf("Room Name%n");

		if(definitionManager.getRoomList().isEmpty())
		{
			System.out.println("-----------------No Room On SHMS------------------");
		}
		else
		{
			for (Room r: definitionManager.getRoomList()) 
			{

				System.out.printf("%s%n", 
						r.getName());
			}
		}
	}
	
	private void displayMenu() 
	{
		System.out.println("\n1. Manage Devices\n" +
				"2. Manage Definitions\n" +
				"3. Save & Exit\n");
	}

	private void listNetworkProtocols()
	{
		System.out.printf("%n------------------NETWORK PROTOCOLS-------------------%n");
		System.out.printf("Protocol Name      Connection Parameter %n");

		if(definitionManager.getProtocolList().isEmpty())
		{
			System.out.println("-----------------No Network Protocol On SHMS------------------");
		}
		else
		{
			for (NetworkProtocol n: definitionManager.getProtocolList()) 
			{

				System.out.printf("%s                 %s%n", 
						n.getProtocolName(),
						n.getConnectionParameter());
			}
		}
	}
	
	private void listDeviceTypes() 
	{
		System.out.printf("%n------------------DEVICE TYPES-------------------%n");
		System.out.printf("Device name      Device Type %n");

		if(definitionManager.getDeviceInfoList().isEmpty())
		{
			System.out.println("-----------------No Device Type On SHMS------------------");
		}
		else
		{
			for (DeviceInfo i: definitionManager.getDeviceInfoList()) 
			{

				System.out.printf("%s                 %s%n", 
						i.getName(),
						i.getDeviceType() instanceof Sensor ? "Sensor" : "Actuator");
			}
		}
	}
	
	private void listDevices() 
	{
		System.out.printf("%n------------------DEVICES-------------------%n");
		System.out.printf("Device ID      Device Info       Protocol       Room %n");

		if( deviceManager.getDeviceList().isEmpty())
		{
			System.out.println("-----------------No Device On SHMS------------------");
		}
		else
		{
			for (Device d : deviceManager.getDeviceList()) 
			{

				System.out.printf("%d                %s                 %s          %s             %n", 
						d.getDeviceID(), 
						d.getDeviceInfo().getName(),
						d.getProtocol().getProtocolName(),
						d.getRoom().getName());
			}
		}
	}

	private void listDefinitions()
	{
		System.out.println("\n1. Room\n" + 
				"2. Device Type\n" + 
				"3. Network Protocol\n");	
	}

	private void listRoomDefinitions()
	{
		System.out.println("\n1. Add Room\n" + 
				"2. Update Room\n" + 
				"3. Delete Room\n" + 
				"4. Retrieve Rooms\n");	
	}

	private void listDeviceTypeDefinitions()
	{
		System.out.println("\n1. Add Type\n" + 
				"2. Update Type\n" + 
				"3. Delete Type\n" + 
				"4. Retrieve Types\n");	
	}

	private void listNetworkProtocolDefinitions()
	{
		System.out.println("\n1. Add Protocol\n" + 
				"2. Update Protocol\n" + 
				"3. Delete Protocol\n" + 
				"4. Retrieve Protocols\n");	
	}


	private void displayManageDeviceMenu() 
	{
		System.out.println("\n1. Add New Device\n" + 
				"2. Update Device\n" + 
				"3. Delete Device\n" +
				"4. Retrieve Devices\n");	
	}

	private int getNextInput(String text) 
	{
		System.out.println();
		System.out.print("> " + text + ": ");
		return scanner.nextInt();
	}
}
