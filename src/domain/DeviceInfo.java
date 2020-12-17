package domain;

public class DeviceInfo 
{
	private String name;
	private DeviceType type;
	
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
