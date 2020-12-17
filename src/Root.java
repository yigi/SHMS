import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.Device;

public class Root implements Serializable 
{
	public List<Device> initialDeviceList;
	
	public Root() 
	{
		initialDeviceList = new ArrayList<Device>();
	}
}
