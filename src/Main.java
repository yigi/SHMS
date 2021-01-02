import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import database.Root;
import domain.ManageDefinitionController;
import domain.ManageDeviceController;
import domain.MonitorHomeController;
import domain.PerformActionController;
import ui.SHMSPanel;

public class Main {
	
	private static final String fileName = "objects.dat";
	private static Root root;
	
	public static void main(String[] args) {
		

		try 
		{
			loadRoot();
		} 
		catch (EOFException eof) 
		{
			root = new Root();
		} 
		catch (FileNotFoundException fnf) 
		{
			System.out.printf("File <%s> is not found...%n%n", fileName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

		ManageDeviceController deviceController = new ManageDeviceController(root);
		ManageDefinitionController definitionController = new ManageDefinitionController(root);
		MonitorHomeController homeController = new MonitorHomeController(root);
		PerformActionController actionController = new PerformActionController(root);
		SHMSPanel panel = new SHMSPanel(deviceController, definitionController, homeController, actionController);
		
		panel.show();
		

		try 
		{
			saveRoot();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	private static void loadRoot() throws Exception 
	{
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName));
		try 
		{
			root = (Root)stream.readObject();
		} 
		finally 
		{
			stream.close();
		}
	}
	
	private static void saveRoot() throws Exception 
	{
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName));
		try 
		{
			stream.writeObject(root);
		} 
		finally {
			stream.close();
		}
	}

}