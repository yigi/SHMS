package domain;

import java.util.List;
import database.*;

public class DeviceManager 
{
	private Root root;
	
	public DeviceManager(Root root) 
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
	
	public void addDevice(int deviceID, DeviceInfo deviceInfo, Room room, NetworkProtocol protocol) 
	{
		Device d = new Device(deviceID, deviceInfo, room, protocol);
		getDeviceList().add(d);
	}
	
	public void removeDevice(Device d) {
		getDeviceList().remove(d);
	}
	
	public Device findDevice(int id) 
	{
		Device toFind = null;
		for (Device d : root.initialDeviceList) 
		{
			if (d.getDeviceID() == id) 
			{
				toFind = d;
				break;
			}
		}
		return toFind;
	}
}
