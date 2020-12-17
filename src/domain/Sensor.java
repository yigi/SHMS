package domain;

public abstract class Sensor implements DeviceType
{
	public String makeOperation( String msg ) 
	{
		return null;
	}
	
	public abstract String readSensor();

}
