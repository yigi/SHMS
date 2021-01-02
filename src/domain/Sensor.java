package domain;

public abstract class Sensor implements DeviceType
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3347853242493809367L;

	public String makeOperation( String msg ) 
	{
		//return null;
		
		if ( msg.equals("Monitor") )
		{
			return readSensor();
		}
		
		return "";
	}
	
	public abstract String readSensor();
}
