package domain;

public abstract class Actuator implements DeviceType
{
	public String makeOperation( String msg ) 
	{
		return null;
	}
	
	public abstract void turnOnDevice();
	public abstract void turnOffDevice();
}
