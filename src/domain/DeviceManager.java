package domain;

import java.util.List;

public class DeviceManager 
{
	
	private Room room;
	
	public DeviceManager(List<Device> list) 
	{
		room = new Room("My room",list);
	}
	
	public List<Device> getDeviceList() {
		return room.getDevices();
	}
	
	public void addDevice(int deviceID, DeviceInfo deviceInfo, Room room, NetworkProtocol protocol) 
	{
		Device d = new Device(deviceID, deviceInfo, room, protocol);
		getDeviceList().add(d);
	}
	
	public void removeDevice(Device d) {
		getDeviceList().remove(d);
	}
	
	public Device findDevice(int deviceID) {
		return room.findDevice(deviceID);
	}

}
