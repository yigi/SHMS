package domain;

import java.util.List;
import database.*;

public class DefinitionManager 
{
	private Root root;
	
	public DefinitionManager(Root root) 
	{
		this.root = root;
	}
	
	public List<Device> getDeviceList() {
		return root.initialDeviceList;
	}
	
	public List<Room> getRoomList() {
		return root.roomList;
	}
	
	public List<DeviceInfo> getDeviceInfoList() {
		return root.deviceInfoList;
	}
	
	public List<NetworkProtocol> getProtocolList() {
		return root.networkProtocolList;
	}
	
	public void addRoomDefinition( Room r ) 
	{
		root.roomList.add(r);
	}
	
	public void addNetworkProtocolDefiniton( NetworkProtocol n)
	{
		root.networkProtocolList.add(n);
	}
	
	public void addDeviceInfoDefinition(DeviceInfo d) 
	{
		root.deviceInfoList.add(d);
	}
	public void removeRoomDefinition( Room r ) 
	{
		root.roomList.remove(r);
	}
	
	public void removeNetworkProtocolDefiniton( NetworkProtocol n)
	{
		root.networkProtocolList.remove(n);
	}
	
	public void removeDeviceInfoDefinition(DeviceInfo d) 
	{
		root.deviceInfoList.remove(d);
	}
	
	public Room findRoom(String name) 
	{
		Room toFind = null;
		for (Room r : root.roomList) 
		{
			if (r.getName().equals(name)) 
			{
				toFind = r;
				break;
			}
		}
		return toFind;
	}
}
