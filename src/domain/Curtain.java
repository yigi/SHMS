package domain;

public class Curtain extends Actuator 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3176845278447503561L;

	public void turnOnDevice() 
	{
		System.out.println("Curtain turned on \n");
	}

	public void turnOffDevice() 
	{
		System.out.println("Curtain turned off \n");
	}
}
