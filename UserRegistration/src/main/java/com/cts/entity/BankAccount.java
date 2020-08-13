package com.cts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is the BankAccount entity")
@Entity
public class BankAccount {

	@ApiModelProperty(value = "A unique key for each USER")
	@Id
	@GeneratedValue
	private int bid;
	
	@ApiModelProperty(value = "bankAccount of the user")
	@Column(unique = true)
	@NotNull(message = "bankAccount should not be null")
	@Pattern(regexp = "(^[0-9]{10}$)",message = "bankAccount should contain 10 digits")
	private String bankAccount;
	
	@ApiModelProperty(value = "ifscCode of the user")
	@NotNull(message = "ifscCode name should not be null")
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$",message = "ifscCode is an 11-digit alpha-numeric code used to uniquely identify bank.")
	private String ifscCode;
	
	@ApiModelProperty(value = "bankName of the user")
	@NotNull(message = "bank name should not be null")
	@Pattern(regexp = "(^[a-zA-Z]+$)",message = "bankName should be in alphabets")
	private String bankName;
	
	@ApiModelProperty(value = "micrCode of the user")
	@NotNull(message = "micrCode name should not be null")
	@Pattern(regexp = "(^[0-9]{9}$)",message = "MICR code is a 9-digit code that uniquely identifies the bank and branch")
	private String micrCode;
	
	@ApiModelProperty(value = "PAN of the user")
    @JsonIgnore
	private String pan;
	
	
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	
}
