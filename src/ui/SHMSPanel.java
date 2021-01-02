package ui;

import java.util.Scanner;

import domain.ManageDeviceController;
import domain.MonitorHomeController;
import domain.NetworkProtocol;
import domain.PerformActionController;
import domain.Room;
import domain.Sensor;
import domain.ManageDefinitionController;
import domain.Device;
import domain.DeviceInfo;

public class SHMSPanel 
{
	private Scanner scanner = new Scanner(System.in);
	private ManageDeviceController deviceController;
	private ManageDefinitionController definitionController;
	private MonitorHomeController homeController;
	private PerformActionController actionController;

	public SHMSPanel(ManageDeviceController deviceController,ManageDefinitionController definitionController, MonitorHomeController homeController, PerformActionController actionController) 
	{
		this.deviceController = deviceController;
		this.definitionController = definitionController;
		this.homeController = homeController;
		this.actionController = actionController;
	}

	public void show() 
	{
		System.out.println("Smart Home Management System");
		while (true) 
		{
			displayMenu();
			int input = getNextInput("Choose 1, 2, 3, 4 or 5");
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
					deviceController.manageDevices(deviceInput);
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
							definitionController.manageRooms(definitonRoomInput);
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
							definitionController.manageDeviceTypes(definitonDeviceTypeInput);
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
			break;
			// Monitor Home
			case 3:
				handleMonitorHome();
				break;
			// Perform Action
			case 4:
				handlePerformAction();
				break;
			case 5:
				System.out.println();
				System.out.printf("Returning Main Menu... ");
				System.out.println();
				break;
			default:
				displayMenu();
				break;
			}
			if (input == 5)
			{
				System.out.println("Exiting application...");
				break;
			}
		}
	}
	
	private void handlePerformAction()
	{
		listActuatorDeviceIDs();

		int deviceID = getNextInput("Choose Device ID to perform action: ");

		listActionTypes();
		
		int actionNumber = getNextInput("Choose 1 or 2: ");
		
		actionController.performAction(deviceID, actionNumber);
	}
	
	private void listActionTypes()
	{
		System.out.println("1) Turn On");
		System.out.println("2) Turn Off");
	}
	
	private void handleMonitorHome()
	{
		listDeviceIDs();
		
		int deviceID = getNextInput("Choose Device ID to monitor, enter 0 to monitor all: ");
		
		if ( deviceID == 0 )
		{
			homeController.monitorAllDevices();
		}
		else
		{
			homeController.monitorDevice(deviceID);
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
				definitionController.manageNetworkProtocol(number);
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

		if(definitionController.getRoomList().isEmpty())
		{
			System.out.println("-----------------No Room On SHMS------------------");
		}
		else
		{
			for (Room r: definitionController.getRoomList()) 
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
				"3. Monitor Home\n" +
				"4. Perform Action\n" +
				"5. Save & Exit\n");
	}

	private void listNetworkProtocols()
	{
		System.out.printf("%n------------------NETWORK PROTOCOLS-------------------%n");
		System.out.printf("Protocol Name      Connection Parameter %n");

		if(definitionController.getProtocolList().isEmpty())
		{
			System.out.println("-----------------No Network Protocol On SHMS------------------");
		}
		else
		{
			for (NetworkProtocol n: definitionController.getProtocolList()) 
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

		if(definitionController.getDeviceInfoList().isEmpty())
		{
			System.out.println("-----------------No Device Type On SHMS------------------");
		}
		else
		{
			for (DeviceInfo i: definitionController.getDeviceInfoList()) 
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

		if( deviceController.getDeviceList().isEmpty())
		{
			System.out.println("-----------------No Device On SHMS------------------");
		}
		else
		{
			for (Device d : deviceController.getDeviceList()) 
			{

				System.out.printf("%d                %s                 %s          %s             %n", 
						d.getDeviceID(), 
						d.getDeviceInfo().getName(),
						d.getProtocol().getProtocolName(),
						d.getRoom().getName());
			}
		}
	}
	
	private void listDeviceIDs() 
	{
		System.out.printf("%n------------------DEVICE IDS-------------------%n");
		System.out.printf("Device ID%n");

		if( deviceController.getDeviceList().isEmpty())
		{
			System.out.println("-----------------No Device On SHMS------------------");
		}
		else
		{
			for (Device d : deviceController.getDeviceList()) 
			{

				System.out.printf("%d%n", 
						d.getDeviceID());
			}
		}
	}
	
	private void listActuatorDeviceIDs() 
	{
		System.out.printf("%n------------------ACTUATOR DEVICE IDS-------------------%n");
		System.out.printf("Device ID%n");

		if( deviceController.getActuatorDeviceList().isEmpty())
		{
			System.out.println("-----------------No Actuator Device On SHMS------------------");
		}
		else
		{
			for (Device d : deviceController.getActuatorDeviceList()) 
			{

				System.out.printf("%d%n", 
						d.getDeviceID());
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
