package ui;

import java.util.Scanner;

import domain.DeviceManager;
import domain.DoorCameraSensor;
import domain.LightingSensor;
import domain.NetworkProtocol;
import domain.Room;
import domain.SmartLight;
import domain.TemperatureSensor;
import domain.AirConditioner;
import domain.Curtain;
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
					Device d = new Device();
					System.out.print("Enter new device ID: ");
					int newID = scanner.nextInt();
					System.out.print("Enter new device info: ");
					String newInfo = scanner.next();
					System.out.print("Enter room: ");
					String newRoom = scanner.next();
					System.out.print("Enter protocol: ");
					String newProtocol = scanner.next();

					//					d.setDeviceID(newID);
					//					d.setdeviceInfo(newInfo);
					//					d.setProtocol(newProtocol);
					//					d.setRoom(newRoom);
					//					deviceManager.addDevice(d);
					break;
					// Update Device
				case 2:
					break;
					// Delete Device
				case 3:
					break;
					// Main Menu
				case 4:
					break;
				default:
					break;
				}
				// Manage Definitions
				break;
			case 2:
				listDefinitions();
				int definitonInput = getNextInput("Choose 1, 2 or 3)");
				switch (definitonInput) 
				{
				// Room
				case 1:
					listRoomDefinitions();
					int definitonRoomInput = getNextInput("Choose 1, 2 or 3)");
					switch (definitonRoomInput) 
					{
					// Add Room
					case 1:
						System.out.print("Enter room name: ");
						String roomName = scanner.next();
						Room room = new Room(roomName);
						definitionManager.addRoomDefinition(room);
						System.out.printf("\n Room %s added: \n",roomName);
						break;
					case 2:
						System.out.print("Enter room name to update: ");
						String roomNameUpdate = scanner.next();
						Room roomToBeUpdated = definitionManager.findRoom(roomNameUpdate);
						if (roomToBeUpdated != null)
						{
							System.out.print("Enter new room name to update: \n");
							String newRoomName = scanner.next();
							roomToBeUpdated.setName(newRoomName);
							System.out.printf("Room name updated as %s: \n",newRoomName);
						}
						else
						{
							System.out.println("There is no such room: ");
						}
						break;
					case 3:
						System.out.print("Enter room name to delete: \n");
						String roomNameDelete = scanner.next();
						Room deletedRoom = definitionManager.findRoom(roomNameDelete);
						if (deletedRoom != null)
						{
							definitionManager.removeRoomDefinition(deletedRoom);
							System.out.printf("Room %s is deleted: \n",roomNameDelete);
						}
						else
						{
							System.out.printf("Room %s not found: \n",roomNameDelete);
						}
						break;
					default:
						break;
					}
					break;
				case 2:
					listDeviceTypeDefinitions();
					int definitonDeviceTypeInput = getNextInput("Choose 1, 2 or 3)");
					switch (definitonDeviceTypeInput) 
					{
					// Add Device Type
					case 1:
						System.out.print("Enter device type: ");
						int deviceTypeOption = getNextInput("Choose 1 for Sensor, 2 for Actuator");
						switch (deviceTypeOption) 
						{
						// Sensor
						case 1:
							int sensorOption = getNextInput("Choose 1 for Lightning, 2 for Temperature, 3 for Door Camera");
							switch (sensorOption) 
							{
							case 1:
								LightingSensor l = new LightingSensor();
								DeviceInfo d1 = new DeviceInfo("Lightning Sensor", l);
								definitionManager.addDeviceInfoDefinition(d1);
								System.out.println("Lightning Sensor added");
								break;
							case 2:
								TemperatureSensor t = new TemperatureSensor();
								DeviceInfo d2 = new DeviceInfo("Temperature Sensor", t);
								definitionManager.addDeviceInfoDefinition(d2);
								System.out.println("Temperature Sensor added");
								break;
							case 3:
								DoorCameraSensor d = new DoorCameraSensor();
								DeviceInfo d3 = new DeviceInfo("Door Camera Sensor", d);
								definitionManager.addDeviceInfoDefinition(d3);
								System.out.println("Door Camera Sensor added");
								break;
							}
							break;
						// Actuator
						case 2:
							int actuatorOption = getNextInput("Choose 1 for Smart Light, 2 for Curtain, 3 for Air Conditioner");
							switch (actuatorOption) 
							{
							case 1:
								SmartLight s = new SmartLight();
								DeviceInfo d1 = new DeviceInfo("Smart Light", s);
								definitionManager.addDeviceInfoDefinition(d1);
								System.out.println("Smart Light added");
								break;
							case 2:
								Curtain c = new Curtain();
								DeviceInfo d2 = new DeviceInfo("Curtain", c);
								definitionManager.addDeviceInfoDefinition(d2);
								System.out.println("Curtain added");
								break;
							case 3:
								AirConditioner a = new AirConditioner();
								DeviceInfo d3 = new DeviceInfo("Air Conditioner", a);
								definitionManager.addDeviceInfoDefinition(d3);
								System.out.println("Air Conditioner added");
								break;
							}
							break;

						default:
							break;
						}
						break;
					case 3:
						listNetworkProtocolDefinitions();
						System.out.print("Enter network protocol name: ");
						String protocolName = scanner.next();
						System.out.print("Enter connection parameter: ");
						String parameter = scanner.next();
						
						NetworkProtocol n = new NetworkProtocol(protocolName, parameter);
						definitionManager.addNetworkProtocolDefiniton(n);
						
					default:
						break;
					}
					break;
				case 3:
					System.out.println();
					System.out.printf("Saved. Returning Menu... ");
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
	}
	private void displayMenu() 
	{
		System.out.println("\n1. Manage Devices\n" +
				"2. Manage Definitions\n" +
				"3. Exit\n");
	}

	private void listDevices() 
	{
		System.out.printf("%n------------------MANAGE DEVICES-------------------%n");
		System.out.printf("Device ID      Device Info       Protocol       Room %n");

		if( deviceManager.getDeviceList().isEmpty())
		{
			System.out.println("-----------------No Device On SHMS------------------");
		}
		else
		{
			for (Device d : deviceManager.getDeviceList()) 
			{

				System.out.printf(" %d        %d                 %d                %d             %n", 
						d.getDeviceID(), 
						d.getDeviceInfo(),
						d.getProtocol(),
						d.getRoom());
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
				"2. Update\n" + 
				"3. Deleted\n");	
	}

	private void listDeviceTypeDefinitions()
	{
		System.out.println("\n1. Add Type\n" + 
				"2. Update\n" + 
				"3. Deleted\n");	
	}

	private void listNetworkProtocolDefinitions()
	{
		System.out.println("\n1. Add Protocol\n" + 
				"2. Update\n" + 
				"3. Deleted\n");	
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
