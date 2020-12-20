package database;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.Device;
import domain.DeviceInfo;
import domain.NetworkProtocol;
import domain.Room;

public class Root implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4236267893289729416L;
	public List<Device> initialDeviceList;
	public List<DeviceInfo> deviceInfoList;
	public List<NetworkProtocol> networkProtocolList;
	public List<Room> roomList;
	
	public Root() 
	{	
		deviceInfoList = new ArrayList<DeviceInfo>();
		networkProtocolList = new ArrayList<NetworkProtocol>();
		roomList = new ArrayList<Room>();
		initialDeviceList = new ArrayList<Device>();
	}
	
	public Device findDevice(int id) 
	{
		Device toFind = null;
		for (Device d : initialDeviceList) 
		{
			if (d.getDeviceID() == id) 
			{
				toFind = d;
				break;
			}
		}
		return toFind;
	}
	
	public Room findRoom(String name) 
	{
		Room toFind = null;
		for (Room r : roomList) 
		{
			if (r.getName().equals(name)) 
			{
				toFind = r;
				break;
			}
		}
		return toFind;
	}
	
	public NetworkProtocol findNetwork(String protocol) 
	{
		NetworkProtocol toFind = null;
		for (NetworkProtocol n : networkProtocolList) 
		{
			if (n.getProtocolName().equals(protocol)) 
			{
				toFind = n;
				break;
			}
		}
		return toFind;
	}
	
	public DeviceInfo findDeviceInfo(String name)
	{
		DeviceInfo toFind = null;
		for (DeviceInfo i: deviceInfoList) 
		{
			if (i.getName().equals(name)) 
			{
				toFind = i;
				break;
			}
		}
		return toFind;
	}
	
}
