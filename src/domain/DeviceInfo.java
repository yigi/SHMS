package domain;

import java.io.Serializable;

public class DeviceInfo implements Serializable 
{
	private String name;
	private DeviceType type;//Sensor Actuator
	
	public DeviceInfo( String name, DeviceType type)
	{
		this.name = name;
		this.type = type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public DeviceType getDeviceType()
	{
		return type;
	}

}
