package domain;

public class AirConditioner extends Actuator 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7229864771094674608L;

	public void turnOnDevice() 
	{
		System.out.println("Device turned on \n");
	}

	public void turnOffDevice() 
	{
		System.out.println("Device turned off \n");
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
}
