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
}
