package domain;

import database.Root;

public class PerformActionController {

	private Root root;

	public PerformActionController(Root root) 
	{
		this.root = root;
	}
	
	public void performAction(int deviceID, int actionNumber)
	{
		Device device = root.findDevice(deviceID);
		
		String message = "";
		
		if (actionNumber == 1)
		{
			message = "Turn On";
		}
		else if (actionNumber == 2)
		{
			message = "Turn Off";
		}
		else
		{
			System.out.println("Invalid action!");
			return;
		}
		
		if (isDeviceActuator(deviceID))
		{
			String operationResponse = device.getDeviceInfo().getDeviceType().makeOperation(message);
			
			if (operationResponse.isEmpty() == false)
			{
				System.out.println(operationResponse);
			}
			else
			{
				System.out.println("Operation performed successfully!");
			}
		}
		else
		{
			System.out.println("No such actuator device!");
		}
	}
	
	private boolean isDeviceActuator(int deviceID)
	{
		boolean isActuator = false;
		
		Device device = root.findDevice(deviceID);
		
		if (device != null && device.getDeviceInfo().getDeviceType() instanceof Actuator)
		{
			isActuator = true;
		}
		
		return isActuator;
	}
	
}
