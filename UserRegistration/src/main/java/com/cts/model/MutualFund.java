package com.cts.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class MutualFund {

	
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
