package domain;

import java.util.List;

public class Home 
{
	private List<Room> rooms;
	
	public Home(List<Room> rooms)
	{
		this.rooms = rooms;
	}

	public List<Room> getRooms() 
	{
		return rooms;
	}
}
