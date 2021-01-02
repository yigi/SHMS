package domain;

public class SmartLight extends Actuator 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1434733762395128795L;

	public void turnOnDevice() 
	{
		System.out.println("SmartLight turned on \n");
	}

	public void turnOffDevice() 
	{
		System.out.println("SmartLight turned off \n");
	}
}
