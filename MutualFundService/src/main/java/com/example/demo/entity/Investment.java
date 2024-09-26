package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author 844226 - mahesh
 *
 */

@ApiModel(description = "This is the Investment entity")
@Entity
public class Investment {
	
	@ApiModelProperty(value = "A unique key for each investment")
	@Id
	@GeneratedValue
	private int iId;
	
	@ApiModelProperty(value = "A MutualFundId  for  Investment")
	@NotNull(message = "MutualFundId cannot be null")
	@Range(min = 1,max = 3,message = "MutualFundId should be in 1 to 2 ")
	private int MutualFundId;
	
	@ApiModelProperty(value = "A accountNumber  for  Investment")
	@NotNull(message = "bankAccount should not be null")
	@Pattern(regexp = "(^[0-9]{10}$)",message = "bankAccount should contain 10 digits")
	private String accountNumber;
	
	@ApiModelProperty(value = "A amountToInvest  for  Investment")
	@NotNull(message = "enter amount To Invest")
	@Range(min = 10,max = 99,message = "amount should be in range of 01 to 99")
	private int amountToInvest;
	
	@JsonIgnore
	private Date timestamp;
	
	@Transient
	private Date timeStamp;
	
	@JsonIgnore
	private String pan;
	
	@Transient
	private MutualFund mutulFund;
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public MutualFund getMutulFund() {
		return mutulFund;
	}
	public void setMutulFund(MutualFund mutulFund) {
		this.mutulFund = mutulFund;
	}
	public int getMutualFundId() {
		return MutualFundId;
	}
	public void setMutualFundId(int mutualFundId) {
		MutualFundId = mutualFundId;
	}
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAmountToInvest() {
		return amountToInvest;
	}
	public void setAmountToInvest(int amountToInvest) {
		this.amountToInvest = amountToInvest;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	

}
