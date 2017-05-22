package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() throws Exception {
		int CredS1= 700;
		
		try{
			double dInterestRate= RateBLL.getRate(CredS1);
			
			assertEquals(4,dInterestRate,0.01);
		}catch (RateException e){
			fail("Test Failed");
		}
	}
	
	@Test
	public void TestRatesAll(){
		ArrayList<RateDomainModel> ratesall;
		ratesall = RateDAL.getAllRates();
		
		for (RateDomainModel r: ratesall){
			try {
				double RRate = RateBLL.getRate(r.getiMinCreditScore());
				assertEquals(r.getdInterestRate(),RRate,0.01);
			} catch (RateException e) {
				// TODO Auto-generated catch block
				fail("Test RatesAll fails");
			}
		}
	}
	
	@Test (expected=RateException.class)
	public void TstWrongRate() throws RateException{
		double RRate= RateBLL.getRate(300);
	}
	
	@Test
	public void PaymentTest(){
		double pv= 300000;
		double fv=0;
		int NumOfPayments= 360;
		double Rte= 0.04/12;
		double pmt= RateBLL.getPayment(Rte, NumOfPayments, pv, fv, false);
		
		assertEquals(-1432.25,pmt,0.01);
}

}
