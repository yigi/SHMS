package ui;

import java.util.Scanner;

import domain.DeviceManager;
import domain.DoorCameraSensor;
import domain.LightingSensor;
import domain.NetworkProtocol;
import domain.Room;
import domain.Sensor;
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
					System.out.print("Enter new device ID: ");
					int deviceId = scanner.nextInt();
					
					if (deviceManager.findDevice(deviceId) != null)
					{
						System.out.println("There is already a device with same ID!");
						break;
					}
					
					listDeviceTypes();
					
					System.out.print("Enter new device type name: ");
					String infoName = scanner.next();
					
					DeviceInfo info = definitionManager.findDeviceInfo(infoName);

					if (info == null)
					{
						System.out.println("There is no such device type!");
						break;
					}
					
					listRooms();
					
					System.out.print("Enter room: ");
					String newRoom = scanner.next();
					
					Room room = definitionManager.findRoom(newRoom);
					
					if (room == null)
					{
						System.out.println("There is no such room!");
						break;
					}
					
					listNetworkProtocols();
					
					System.out.print("Enter protocol: ");
					String newProtocol = scanner.next();
					
					NetworkProtocol protocol = definitionManager.findNetwork(newProtocol);
					
					if (protocol == null)
					{
						System.out.println("There is no such network protocol!");
						break;
					}
					else
					{
						System.out.print("Enter the protocol parameter: ");
						String parameter = scanner.next();
						
						if (protocol.getConnectionParameter().equals(parameter) == false)
						{
							System.out.println("Network protocol parameter mismatch!");
							break;
						}
					}

					deviceManager.addDevice(deviceId, info, room, protocol);
					System.out.println("Device successfully added!");
					break;
					// Update Device
				case 2:
					////
					listDevices();
					System.out.print("Enter the id of device you would like to update: ");
					int updatedId = scanner.nextInt();
					
					Device updatedDevice = deviceManager.findDevice(updatedId);
					
					if ( updatedDevice != null )
					{
						System.out.println("Enter the option you would like to update: ");
						int option = getNextInput("Choose 1 for Device Info, 2 for Network Protocol, 3 for Room");
						
						switch(option)
						{
						case 1:
							listDeviceTypes();
							System.out.print("Enter updated device type name: ");
							String updatedInfoName = scanner.next();
							
							DeviceInfo updatedInfo = definitionManager.findDeviceInfo(updatedInfoName);
							
							if (updatedInfo != null)
							{
								updatedDevice.setDeviceInfo(updatedInfo);
								System.out.println("Device type of the device updated!");
							}
							else
							{
								System.out.println("No such device type!");
							}
							
							break;
						case 2:
							listNetworkProtocols();
							System.out.print("Enter updated network protocol name: ");
							String updatedProtocolName = scanner.next();
							
							NetworkProtocol updatedProtocol = definitionManager.findNetwork(updatedProtocolName);
							
							if (updatedProtocol != null)
							{
								updatedDevice.setProtocol(updatedProtocol);
								System.out.println("Network protocol of the device updated!");
							}
							else
							{
								System.out.println("No such network protocol!");
							}
							
							break;
						case 3:
							listRooms();
							System.out.print("Enter updated room name: ");
							String updatedRoomName = scanner.next();
							
							Room updatedRoom = definitionManager.findRoom(updatedRoomName);
							
							if (updatedRoom != null)
							{
								updatedDevice.setRoom(updatedRoom);
								System.out.println("Room of the device updated!");
							}
							else
							{
								System.out.println("No such room!");
							}
							break;
						default:
							System.out.println("Invalid option!");
							break;
						}
					}
					else
					{
						System.out.println("Device not found!");
					}
					
					////
					
					break;
					// Delete Device
				case 3:
					listDevices();
					
					System.out.print("Enter the id of device you would like to delete: ");
					int deletedId = scanner.nextInt();
					
					Device deletedDevice = deviceManager.findDevice(deletedId);
					
					if (deletedDevice != null)
					{
						deviceManager.removeDevice(deletedDevice);
						System.out.println("Device deleted successfully!");
					}
					else
					{
						System.out.println("Device not found!");
					}
					
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
					listRoomDefinitions();
					int definitonRoomInput = getNextInput("Choose 1, 2, 3 or 4)");
					switch (definitonRoomInput) 
					{
					// Add Room
					case 1:
						System.out.print("Enter room name: ");
						String roomName = scanner.next();
						Room roomToBeAdded = definitionManager.findRoom(roomName);
						if (roomToBeAdded == null)
						{
							roomToBeAdded = new Room(roomName);
							definitionManager.addRoomDefinition(roomToBeAdded);
							System.out.printf("\n Room %s added: \n",roomName);
						}
						else
						{
							System.out.println("There is already a room named: " + roomName + "!");
						}
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
					case 4:
						listRooms();
						break;
					default:
						break;
					}
					break;
				case 2:
					listDeviceTypeDefinitions();
					int definitonDeviceTypeInput = getNextInput("Choose 1, 2, 3 or 4)");
					switch (definitonDeviceTypeInput) 
					{
					// Add Device Type
					case 1:
						System.out.print("Enter device type: ");
						int deviceTypeOption = getNextInput("Choose 1 for Sensor, 2 for Actuator");
						System.out.print("Enter device name to be added: ");
						String name = scanner.next();
						if (definitionManager.findDeviceInfo(name) != null)
						{
							System.out.printf("Already a device with name: %s defined!\n", name);
							break;
						}
						
						switch (deviceTypeOption) 
						{
						// Sensor
						case 1:
							int sensorOption = getNextInput("Choose 1 for Lightning, 2 for Temperature, 3 for Door Camera");
							switch (sensorOption) 
							{
							case 1:
								LightingSensor l = new LightingSensor();
								DeviceInfo d1 = new DeviceInfo(name, l);
								definitionManager.addDeviceInfoDefinition(d1);
								System.out.println("Lightning Sensor added");
								break;
							case 2:
								TemperatureSensor t = new TemperatureSensor();
								DeviceInfo d2 = new DeviceInfo(name, t);
								definitionManager.addDeviceInfoDefinition(d2);
								System.out.println("Temperature Sensor added");
								break;
							case 3:
								DoorCameraSensor d = new DoorCameraSensor();
								DeviceInfo d3 = new DeviceInfo(name, d);
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
								DeviceInfo d1 = new DeviceInfo(name, s);
								definitionManager.addDeviceInfoDefinition(d1);
								System.out.println("Smart Light added");
								break;
							case 2:
								Curtain c = new Curtain();
								DeviceInfo d2 = new DeviceInfo(name, c);
								definitionManager.addDeviceInfoDefinition(d2);
								System.out.println("Curtain added");
								break;
							case 3:
								AirConditioner a = new AirConditioner();
								DeviceInfo d3 = new DeviceInfo(name, a);
								definitionManager.addDeviceInfoDefinition(d3);
								System.out.println("Air Conditioner added");
								break;
							}
							break;
						}
						break;
					//Update Device Type
					case 2:
						System.out.print("Enter device name to be updated: ");
						String updatedName = scanner.next();
						
						DeviceInfo updatedInfo = definitionManager.findDeviceInfo(updatedName);
						if (updatedInfo != null)
						{
							System.out.print("Enter new device name: ");
							String newName = scanner.next();
							
							updatedInfo.setName(newName);
							System.out.println("Device name updated successfully!");
						}
						else
						{
							System.out.println("No such device type!");
						}
						break;
					// Delete Device Type
					case 3:					
						System.out.print("Enter device name to be deleted: ");
						String deletedName = scanner.next();
						
						DeviceInfo deletedInfo = definitionManager.findDeviceInfo(deletedName);
						if (deletedInfo != null)
						{
							definitionManager.removeDeviceInfoDefinition(deletedInfo);
							System.out.println("Device type deleted successfully!");
						}
						else
						{
							System.out.println("No such device type!");
						}
						break;
					// Retrieve Device Type
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
		listNetworkProtocolDefinitions();
		
		int number = getNextInput("Choose 1, 2, 3 or 4)");
		
		switch(number)
		{
			case 1:
				System.out.print("Enter network protocol name: ");
				String protocolName = scanner.next();
				System.out.print("Enter connection parameter: ");
				String parameter = scanner.next();

				
				if (definitionManager.findNetwork(protocolName) == null)
				{
					NetworkProtocol n = new NetworkProtocol(protocolName, parameter);
					definitionManager.addNetworkProtocolDefiniton(n);
					
					System.out.printf("Network protocol %s , parameter %s added \n ", protocolName,parameter);
				}
				else
				{
					System.out.println("Network protocol already defined!");
				}
				break;
			case 2:
				// update
				System.out.print("Enter the name of network protocol you would like to update: ");
				String networkName = scanner.next();
				
				NetworkProtocol found = definitionManager.findNetwork(networkName);
				
				if ( found != null )
				{
					System.out.println("Enter the option you would like to update: ");
					int option = getNextInput("Choose 1 for Protocol Name, 2 for Parameter update");
					
					switch(option)
					{
					case 1:
						System.out.print("Enter updated network protocol name: ");
						String updatedProtocolName = scanner.next();
						
						found.setProtocolName(updatedProtocolName);
						
						System.out.println("Network protocol name updated!");
						break;
					case 2:
						System.out.print("Enter updated connection parameter: ");
						String updatedConnectionParameter = scanner.next();
						
						found.setConnectionParameter(updatedConnectionParameter);
						
						System.out.println("Connection parameter updated!");
						break;
					default:
						System.out.println("Invalid number!");
						break;
					}
				}
				else
				{
					System.out.println("Network protocol not found!");
				}
				
				break;
			case 3:
				System.out.print("Enter the name of network protocol you would like to delete: ");
				String networkNameDeleted = scanner.next();
				
				NetworkProtocol deletedNetwork = definitionManager.findNetwork(networkNameDeleted);
				
				if (deletedNetwork != null)
				{
					definitionManager.removeNetworkProtocolDefiniton(deletedNetwork);
					System.out.println("Network Protocol deleted!");
				}
				else
				{
					System.out.println("Network protocol not found!");
				}
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
