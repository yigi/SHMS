package domain;

public abstract class Actuator implements DeviceType
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2414136192329154568L;
	public String makeOperation( String msg ) 
	{
		return null;
	}
	
	public abstract void turnOnDevice();
	public abstract void turnOffDevice();
}
