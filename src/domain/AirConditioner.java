package domain;

public class AirConditioner extends Actuator 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7229864771094674608L;

	public void turnOnDevice() 
	{
		System.out.println("Air Conditioner turned on \n");
	}

	public void turnOffDevice() 
	{
		System.out.println("Air Conditioner turned off \n");
	}
}
