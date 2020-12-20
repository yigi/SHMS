package domain;

import java.util.List;
import java.util.Scanner;

import database.*;

public class DeviceManager 
{
	private Root root;
	private Scanner scanner;
	
	public DeviceManager(Root root) 
	{
		this.root = root;
		this.scanner = new Scanner(System.in);
	}
	
	public List<Device> getDeviceList()
	{
		return root.getDeviceList();
	}
	
	public void manageDevices(int deviceInput)
	{
		switch (deviceInput) 
		{
		// Add Device
		case 1:
			System.out.print("Enter new device ID: ");
			int deviceId = scanner.nextInt();
			
			if (root.findDevice(deviceId) != null)
			{
				System.out.println("There is already a device with same ID!");
				break;
			}
			
			System.out.print("Enter new device type name: ");
			String infoName = scanner.next();
			
			DeviceInfo info = root.findDeviceInfo(infoName);

			if (info == null)
			{
				System.out.println("There is no such device type!");
				break;
			}
			
			System.out.print("Enter room: ");
			String newRoom = scanner.next();
			
			Room room = root.findRoom(newRoom);
			
			if (room == null)
			{
				System.out.println("There is no such room!");
				break;
			}
			
			System.out.print("Enter protocol: ");
			String newProtocol = scanner.next();
			
			NetworkProtocol protocol = root.findNetwork(newProtocol);
			
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
			
			Device dev = new Device(deviceId, info, room, protocol);
			root.addDevice(dev);
			
			System.out.println("Device successfully added!");
			break;
			// Update Device
		case 2:
			////
			System.out.print("Enter the id of device you would like to update: ");
			int updatedId = scanner.nextInt();
			
			Device updatedDevice = root.findDevice(updatedId);
			
			if ( updatedDevice != null )
			{
				System.out.println("Enter the option you would like to update: ");
				int option = getNextInput("Choose 1 for Device Info, 2 for Network Protocol, 3 for Room");
				
				switch(option)
				{
				case 1:
					System.out.print("Enter updated device type name: ");
					String updatedInfoName = scanner.next();
					
					DeviceInfo updatedInfo = root.findDeviceInfo(updatedInfoName);
					
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
					System.out.print("Enter updated network protocol name: ");
					String updatedProtocolName = scanner.next();
					
					NetworkProtocol updatedProtocol = root.findNetwork(updatedProtocolName);
					
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
					System.out.print("Enter updated room name: ");
					String updatedRoomName = scanner.next();
					
					Room updatedRoom = root.findRoom(updatedRoomName);
					
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
			
			break;
			// Delete Device
		case 3:			
			System.out.print("Enter the id of device you would like to delete: ");
			int deletedId = scanner.nextInt();
			
			Device deletedDevice = root.findDevice(deletedId);
			
			if (deletedDevice != null)
			{
				root.deleteDevice(deletedDevice);
				System.out.println("Device deleted successfully!");
			}
			else
			{
				System.out.println("Device not found!");
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
