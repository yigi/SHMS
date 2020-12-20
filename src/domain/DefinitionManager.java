package domain;

import java.util.List;
import java.util.Scanner;

import database.*;

public class DefinitionManager 
{
	private Root root;
	
	private Scanner scanner;
	
	public DefinitionManager(Root root) 
	{
		this.root = root;
		this.scanner = new Scanner(System.in);
	}
	
	public List<Room> getRoomList()
	{
		return root.getRoomList();
	}
	
	public List<DeviceInfo> getDeviceInfoList()
	{
		return root.getDeviceInfoList();
	}
	
	public List<NetworkProtocol> getProtocolList()
	{
		return root.getNetworkProtocolList();
	}

	public void manageNetworkProtocol(int number)
	{
		switch(number)
		{
			case 1:
				System.out.print("Enter network protocol name: ");
				String protocolName = scanner.next();
				System.out.print("Enter connection parameter: ");
				String parameter = scanner.next();
				
				if (root.findNetwork(protocolName) == null)
				{
					NetworkProtocol n = new NetworkProtocol(protocolName, parameter);
					root.addNetworkProtocol(n);
					
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
				
				NetworkProtocol found = root.findNetwork(networkName);
				
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
				
				NetworkProtocol deletedNetwork = root.findNetwork(networkNameDeleted);
				
				if (deletedNetwork != null)
				{
					root.deleteNetworkProtocol(deletedNetwork);
					System.out.println("Network Protocol deleted!");
				}
				else
				{
					System.out.println("Network protocol not found!");
				}
				break;
			default:
				break;
		}
	}
	
	public void manageRooms(int definitonRoomInput)
	{
		switch (definitonRoomInput) 
		{
		// Add Room
		case 1:
			System.out.print("Enter room name: ");
			String roomName = scanner.next();
			Room roomToBeAdded = root.findRoom(roomName);
			if (roomToBeAdded == null)
			{
				roomToBeAdded = new Room(roomName);
				root.addRoom(roomToBeAdded);
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
			Room roomToBeUpdated = root.findRoom(roomNameUpdate);
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
			Room deletedRoom = root.findRoom(roomNameDelete);
			if (deletedRoom != null)
			{
				root.deleteRoom(deletedRoom);
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
	}
	
	public void manageDeviceTypes(int definitonDeviceTypeInput)
	{
		switch (definitonDeviceTypeInput) 
		{
		// Add Device Type
		case 1:
			System.out.print("Enter device type: ");
			int deviceTypeOption = getNextInput("Choose 1 for Sensor, 2 for Actuator");
			System.out.print("Enter device name to be added: ");
			String name = scanner.next();
			if (root.findDeviceInfo(name) != null)
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
					root.addDeviceInfo(d1);
					System.out.println("Lightning Sensor added");
					break;
				case 2:
					TemperatureSensor t = new TemperatureSensor();
					DeviceInfo d2 = new DeviceInfo(name, t);
					root.addDeviceInfo(d2);
					System.out.println("Temperature Sensor added");
					break;
				case 3:
					DoorCameraSensor d = new DoorCameraSensor();
					DeviceInfo d3 = new DeviceInfo(name, d);
					root.addDeviceInfo(d3);
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
					root.addDeviceInfo(d1);
					System.out.println("Smart Light added");
					break;
				case 2:
					Curtain c = new Curtain();
					DeviceInfo d2 = new DeviceInfo(name, c);
					root.addDeviceInfo(d2);
					System.out.println("Curtain added");
					break;
				case 3:
					AirConditioner a = new AirConditioner();
					DeviceInfo d3 = new DeviceInfo(name, a);
					root.addDeviceInfo(d3);
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
			
			DeviceInfo updatedInfo = root.findDeviceInfo(updatedName);
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
			
			DeviceInfo deletedInfo = root.findDeviceInfo(deletedName);
			if (deletedInfo != null)
			{
				root.deleteDeviceInfo(deletedInfo);
				System.out.println("Device type deleted successfully!");
			}
			else
			{
				System.out.println("No such device type!");
			}
			break;
		default:
			break;
		}
	}
	
	private int getNextInput(String text) 
	{
		System.out.println();
		System.out.print("> " + text + ": ");
		return scanner.nextInt();
	}
}
