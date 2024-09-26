package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MutualFund {

	@Id
	@GeneratedValue
	private int mId;
	private String MutualFundName;
	
	
	
	

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getMutualFundName() {
		return MutualFundName;
	}

	public void setMutualFundName(String mutualFundName) {
		MutualFundName = mutualFundName;
	}

	
	
	
	
	
}
