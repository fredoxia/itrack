package itrack.dao.VO;

import java.io.Serializable;

import itrack.dao.entity.SQLServer.ClientsMS;

public class ClientJinSuanVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5239663675189434084L;
	private int clientId ;
	private String clientName;
	private String regionName;
	private String phoneNum ;
	
	public ClientJinSuanVO(ClientsMS clientsMS){
		this.clientId = clientsMS.getClient_id();
		this.clientName = clientsMS.getName();
		String regionName = clientsMS.getRegion().getName();
		String address = clientsMS.getAddress();
		StringBuffer thisAddress = new StringBuffer("");
		
		if (!regionName.trim().equals(""))
			thisAddress.append(regionName + " |");
		if (!address.trim().equals(""))
			thisAddress.append(" " + address);

		this.regionName = thisAddress.toString();
		String phoneNum = clientsMS.getPhoneNum();
		if (phoneNum == null || phoneNum.trim().equals(""))
			phoneNum = "-";
		this.phoneNum = phoneNum;
	}
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
}
