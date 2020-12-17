package domain;

import java.io.Serializable;

public class Device implements Serializable 
{
	private int deviceID;
	private DeviceInfo deviceInfo;
	private Room room;
	private NetworkProtocol protocol;
	
	public Device(int deviceID, DeviceInfo deviceInfo, Room room, NetworkProtocol protocol) 
	{
		this.deviceID = deviceID;
		this.deviceInfo = deviceInfo;
		this.room = room;
		this.protocol = protocol;
	}

	public int getDeviceID() 
	{
		return deviceID;
	}

	public void setDeviceID(int id) 
	{
		this.deviceID = id;
	}

	public void setProductName(DeviceInfo info) {
		this.deviceInfo = info;
	}
	
	public void setRoom(Room room) 
	{
		this.room = room;
	}

	public Room getRoom() 
	{
		return room;
	}
	
	public void setProtocol(NetworkProtocol protocol) 
	{
		this.protocol = protocol;
	}
	
	public NetworkProtocol getProtocol() 
	{
		return protocol;
	}
	
	public String performAction(String msg)
	{
		return null;
	}

	
}