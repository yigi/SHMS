import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import database.Root;
import domain.DefinitionManager;
import domain.DeviceManager;
import ui.SHMSPanel;

public class Main {
	
	private static final String fileName = "objects2.dat";
	private static Root root;
	
	public static void main(String[] args) {
		

		try 
		{
			loadRoot();
		} 
		catch (EOFException eof) 
		{
			System.out.printf("File is empty <%s>...%n", fileName);
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
		

		DeviceManager deviceManager = new DeviceManager(root);
		DefinitionManager definitionManager = new DefinitionManager(root);
		SHMSPanel panel = new SHMSPanel(deviceManager, definitionManager);
		
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