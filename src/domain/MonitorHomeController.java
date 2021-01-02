package domain;

import java.util.List;

import database.*;

public class MonitorHomeController 
{
	private Root root;

	public MonitorHomeController(Root root) 
	{
		this.root = root;
	}
	
	public void monitorAllDevices()
	{
		// list all sensors and actuators with device id and status
		List<Device> devices = root.getDeviceList();
		
		for (Device dev: devices)
		{
			monitorDevice(dev.getDeviceID());
			System.out.println("--------------");
		}
		
	}
	
	public void monitorDevice(int deviceID)
	{		
		Device device = root.findDevice(deviceID);
		
		if (device != null)
		{
			String monitorStatus = device.getDeviceInfo().getDeviceType().makeOperation("Monitor");
			
			System.out.println("Device ID: " + deviceID);
			System.out.println("Status: " + monitorStatus);
		}
		else
		{
			System.out.println("No such device!");
		}
	}

}
