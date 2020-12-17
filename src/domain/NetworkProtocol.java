package domain;

public class NetworkProtocol 
{
	private String protocolName;
	private String connectionParameter;
	
	public NetworkProtocol(String protocolName, String connectionParameter)
	{
		this.protocolName = protocolName;
		this.connectionParameter = connectionParameter;
	}
	
	public String getProtocolName()
	{
		return protocolName;
	}
	
	public void setProtocolName(String name)
	{
		this.protocolName = name;
	}
	
	public String getConnectionParameter()
	{
		return connectionParameter;
	}
	
	public void setConnectionParameter(String param)
	{
		this.connectionParameter = param;
	}
	
	
}
