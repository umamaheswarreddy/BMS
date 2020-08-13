package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DemoController;
import com.example.demo.entity.Investment;
import com.example.demo.entity.MutualFund;
import com.example.demo.exceptions.InvestmentNotFoundException;
import com.example.demo.repository.InvestMentRepository;
import com.example.demo.repository.MutualFundRepository;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 844226
 * @apiNote InvestMentControoler used to invest money and retrive all
 *          transactions
 */
@RestController
public class InvestMentController {

	@Autowired
	private InvestMentRepository repo;

	@Autowired
	private MutualFundRepository dao;

	@Autowired
	private UserRegistrationServiceProxy proxy;

	@Autowired
	DemoController controller;

	/**
	 * 
	 * @param investment - investment details
	 */
	@ApiOperation(value = "Add a investment", consumes = "application/json", notes = "Hit this URL to insert a new investment details")
	@PostMapping("/invest")
	public ResponseEntity<?> addInvest(@Valid @RequestBody Investment investment) {
		String userName = controller.currentUserName();
		investment.setPan(userName);
		investment.setTimestamp(new Date());
		repo.save(investment);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	/**
	 * 
	 * @param pan - pancard
	 * @return list of investments
	 */
	@ApiOperation(value = "Retrieve a Investment  details based on pan", produces = "A Investment details if it exists", notes = "Hit this URL to get a Investment details")
	@GetMapping("/getTransactions/{pan}")
	public List<Investment> GetAllTransactionsByPan(@PathVariable String pan) {
		List<Investment> investment = repo.findAllByPan(pan);
		if(!controller.currentUserName().equals(pan)) {
			throw new InvestmentNotFoundException("you are not allowed to see others transaction details");
		}
		else if (investment.isEmpty()) {
			throw new InvestmentNotFoundException("no transactions to show pls invest in mutual funds");
		} else {
			for (Investment invest : investment) {
				invest.setTimeStamp(invest.getTimestamp());
				MutualFund Mf = dao.findById(invest.getMutualFundId()).orElse(null);
				invest.setMutulFund(Mf);
			}
			return investment;
		}
	}

//	@GetMapping("/getTransaction/{iId}")
//	public Investment findByInvestmentId(@PathVariable int iId){
//		Investment investment = repo.findById(iId).orElse(null);
//		MutualFund Mf = dao.findById(investment.getMutualFundId()).orElse(null);
//		investment.setMutulFund(Mf);
//		
//		return investment;
//	}

	/**
	 * 
	 * @param pan- pan
	 * @param iId  - investmentId
	 * @return list of transactions
	 */
	@ApiOperation(value = "Retrieve a Investment  details based on pan and investmentId", produces = "A Investment details if it exists", notes = "Hit this URL to get a Investment details")
	@GetMapping("/getTransaction/pan/{pan}/id/{iId}")
	public Investment findByInvestmentid(@PathVariable String pan, @PathVariable int iId) {
		Investment investment = repo.findByPanANDIId(pan, iId).orElse(null);
		if (investment.equals(null)) {
			throw new InvestmentNotFoundException("no transactions to show on this pan and investmentid");
		} else {
			MutualFund Mf = dao.findById(investment.getMutualFundId()).orElse(null);
			investment.setMutulFund(Mf);
			return investment;
		}
	}

	/**
	 * 
	 * @param pan          - pan
	 * @param MutualFundId - MutualFundId
	 * @return list of transactions
	 */
	@ApiOperation(value = "Retrieve a Investment  details based on pan and mutualFundid", produces = "A Investment details if it exists", notes = "Hit this URL to get a Investment details")
	@GetMapping("/getTransaction/pan/{pan}/mutualFundid/{MutualFundId}")
	public List<Investment> findInvestmentByPanANDMutualFundId(@PathVariable String pan,
			@PathVariable int MutualFundId) {
		List<Investment> investments = repo.findAllByPanANDMutualFundId(pan, MutualFundId);
		if (investments.isEmpty()) {
			throw new InvestmentNotFoundException("no investments to show for entered pan and mutualfundId ");
		} else {
			for (Investment invest : investments) {
				invest.setTimeStamp(invest.getTimestamp());
				MutualFund mf = dao.findById(MutualFundId).orElse(null);
				invest.setMutulFund(mf);
			}
			return investments;
		}
	}

	/**
	 * 
	 * @param MutualFundId - MutualFundId
	 * @return list of investment details based on mutualFundid
	 */
	@ApiOperation(value = "Retrieve a Investment  details based on mutualFundid", produces = "A Investment details if it exists", notes = "Hit this URL to get a Investment details based on mutualFundid")
	@GetMapping("/getAllTransaction/MutualFundId/{MutualFundId}")
	public List<Investment> findAllTransactionDetailsForParticularFund(@PathVariable int MutualFundId) {

		List<Investment> investments = repo.findAllByMutualFundId(MutualFundId);
		if (investments.isEmpty()) {
			throw new InvestmentNotFoundException("please enter valid MutualFundId");
		} else {
			for (Investment invest : investments) {
				invest.setTimeStamp(invest.getTimestamp());
				MutualFund mf = dao.findById(MutualFundId).orElse(null);
				invest.setMutulFund(mf);
			}
			return investments;
		}
	}

}
