package com.library.jdbc.service.pojo;

import java.util.Objects;

public class CustomInfo {
	private String id;
	private String infoType;
	private String customInfoValue;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getCustomInfoValue() {
		return customInfoValue;
	}
	public void setCustomInfoValue(String customInfoValue) {
		this.customInfoValue = customInfoValue;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(customInfoValue, id, infoType);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomInfo other = (CustomInfo) obj;
		return Objects.equals(customInfoValue, other.customInfoValue) && Objects.equals(id, other.id)
				&& Objects.equals(infoType, other.infoType);
	}
	
	
}
