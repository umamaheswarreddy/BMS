package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Investment;

@Repository
public interface InvestMentRepository extends JpaRepository<Investment, Integer> {
	    @Query(value = "SELECT * FROM Investment t where t.pan = :pan AND t.i_Id = :iId",nativeQuery = true)
        Investment findByPanANDIId(@Param("pan") String pan,@Param("iId") int iId);
	    
	 //   Investment findByPanANDiId(@Param("pan") String pan,@Param("iId")int iId);
	    
//	   @Query(value = "select SUM(amountToInvest) from  Investment t where t.account_number =:accountNumber",nativeQuery = true) 
//	   Integer findSUMByACCOUNTNumber(@Param("accountNumber")String accountNumber);
}
