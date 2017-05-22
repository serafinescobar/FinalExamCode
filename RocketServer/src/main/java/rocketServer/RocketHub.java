package rocketServer;

import java.io.IOException;

import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			//	TODO - RocketHub.messageReceived

			//	You will have to:
			//	Determine the rate with the given credit score (call RateBLL.getRate)
			//		If exception, show error message, stop processing
			//		If no exception, continue
			//	Determine if payment, call RateBLL.getPayment
			//	
			//	you should update lq, and then send lq back to the caller(s)
			double Rate = 0.0;
			try {
				lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
			} catch (Exception rateException) {
				rateException.printStackTrace();
			} finally {
				double r = (lq.getdRate()*.01) / 12;
				double n = lq.getiTerm() * 12;
				double p = lq.getdAmount();
				double f = 0;
				boolean t = false;

				double Pymnt = rocketBase.RateBLL.getPayment(r, n, p, f, t);
				lq.setdPayment(Pymnt);
}
			sendToAll(lq);
		}
	}
}
