package org.exquisitus.server.domainmodel;

import java.math.BigDecimal;
import java.sql.Date;

public class Item {

	private String name;
	private String description;
	private BigDecimal initialPrice;
	private BigDecimal reservePrice;
	
	private Date startDate;
	private Date endDate;
	private Date approvaleDateTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}
	public BigDecimal getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getApprovaleDateTime() {
		return approvaleDateTime;
	}
	public void setApprovaleDateTime(Date approvaleDateTime) {
		this.approvaleDateTime = approvaleDateTime;
	}
}
