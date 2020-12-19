package domain;

import java.io.Serializable;

public class DeviceInfo implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 702509751873498363L;
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
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public DeviceType getDeviceType()
	{
		return type;
	}

}
