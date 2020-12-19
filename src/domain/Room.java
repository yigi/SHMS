package domain;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable 
{
	private String name;
	private List<Device> devices;
	
	public Room(String name)
	{
		this.name = name;
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
