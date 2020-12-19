package domain;

import java.io.Serializable;

public class NetworkProtocol implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1025885173449975663L;
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
