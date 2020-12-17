package domain;

import java.util.List;

public class Room 
{
	private String name;
	private List<Device> devices;
	
	public Room(String name, List<Device> devices )
	{
		this.name = name;
		this.devices = devices;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public List<Device> getDevices() {
		return devices;
	}
	
	public Device findDevice(int id) 
	{
		Device toFind = null;
		for (Device d : devices) 
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
