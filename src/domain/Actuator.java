package domain;

public abstract class Actuator implements DeviceType
{
	protected boolean isOn = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2414136192329154568L;
	public String makeOperation( String msg ) 
	{
		String response = "";
		
		if(msg.equals("Turn On"))
		{
			if (isOn == false)
			{
				turnOnDevice();
				isOn = true;
			}
			else
			{
				response = "Device is already on!";
			}
		}
		else if(msg.equals("Turn Off"))
		{
			if (isOn)
			{
				turnOffDevice();
				isOn = false;
			}
			else
			{
				response = "Device is already off!";
			}
		}
		else if (msg.equals("Monitor"))
		{
			response = getStatus();
		}
		
		return response;
	}
	
	public abstract void turnOnDevice();
	public abstract void turnOffDevice();
	
	public String getStatus()
	{
		String status = "";
		if (isOn)
		{
			status = "On";
		}
		else
		{
			status = "Off";
		}
		
		return status;
	}
}
