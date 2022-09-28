//Downloads a file of 1M from "http://ipv4.ikoula.testdebit.info/1M.iso"
//to rate an Internet speed

package ProgramFiles;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

public class NetTrafficControl {
	
	public static SpeedTestSocket testSocket = new SpeedTestSocket();
	public static SpeedTestReport speedRate;
	public static SpeedTestError speedTestError;
	public static double rateToInterval = 30.0;
	public static BigDecimal bDc;
	public final static Object muTex = new Object();

		
	public static class CheckTraffic extends Thread {
		
		public static double rateToInterval;

		public static void CheckTrafficRun() throws InterruptedException {
			
		System.out.println("The beginning of the CheckTrafficRun() method \n====================================\n");
				
		final Thread onCompletionThread = new CheckTraffic();
		final Thread onAwakeningThread = new myAwakening();
		
        // Starts multithreading's execution 
        onCompletionThread.start();
        onAwakeningThread.start();
        
        // Waiting for a termination of all the threads
        onCompletionThread.join();
        onAwakeningThread.join();
							
	try {
					
		TimeUnit.SECONDS.sleep( (int) NetTrafficControl.rateToInterval * 1 );
		
		testSocket.startDownload("http://ipv4.ikoula.testdebit.info/1M.iso");

		// add a listener to wait for speedtest completion and progress
		testSocket.addSpeedTestListener(new ISpeedTestListener() {

		    @Override
		    public void onCompletion(SpeedTestReport speedRate) {
		    	synchronized (muTex) {
			    	try {
			    		
			        // called when download/upload is complete
			        System.out.println("[COMPLETED] rate in octet/s : " + speedRate.getTransferRateOctet());
			        System.out.println("[COMPLETED] rate in bit/s   : " + speedRate.getTransferRateBit() + "\n");
			        
			        bDc = new BigDecimal(speedRate.getTransferRateBit().floatValue());			        
			        rateToInterval = (double) ((double) bDc.intValue() * 0.00001) / 2;
			        					
			        System.out.println("))))))))))))))))))) The INTERVAL: " + rateToInterval + " ((((((((((((((((((((((((((\n");
					System.out.println("[onCompletion] ended successfully \n========================\n");
					
					muTex.wait();

					} catch (Exception e) {
						System.out.println("onCompletion: A message From the Catch Block::: " + e.getMessage() + " \n======\n");
					}
			    }
		    }// eof OnCompletion

		    @Override
		    public void onError(SpeedTestError speedTestErr, String errMessage) {
		         // called when a download/upload error occur
		        System.out.println(errMessage + "\n");
		}

		    @Override
		    public void onProgress(float percent, SpeedTestReport speedRate) {
		        // called to notify download/upload progress
			        System.out.println("[PROGRESS] progress : " + percent + "%");
			        System.out.println("[PROGRESS] rate in octet/s : " + speedRate.getTransferRateOctet());
			        System.out.println("[PROGRESS] rate in bit/s   : " + speedRate.getTransferRateBit() + "\n");
		 }
			    
	}); // eofListener	 ////////////////////////////////////////////////////////////
			
		} catch (Exception e) {
			System.out.println("Try/Catch speedRate\n====================================\n");
			e.printStackTrace();
		}
		System.out.println("The end of the First Class, method CheckTrafficRun() \n====================================\n");
		}
		 
	} // eofmethod
	
	public static class myAwakening extends Thread {
		@Override
		public void run() {
		synchronized (muTex) {
			 try {
					TimeUnit.SECONDS.sleep( (long) ((long) CheckTraffic.rateToInterval / 1.5) );
					muTex.notifyAll();
					System.out.println("myAwakening: Waked up +++ ======\n");
				} catch (Exception e) {
					
					try {
						System.out.println("myAwakening: A message From the Catch Block : FIRST Failure ======\n");
						e.getMessage();
						TimeUnit.SECONDS.sleep( (long) ((long) CheckTraffic.rateToInterval / 1.5) );
						muTex.notifyAll();
					} catch (InterruptedException e1) {
						System.out.println("myAwakening: A message From the Catch Block : SECOND Failure ======\n");
						e1.getMessage();
					}
					
				}
			}
		System.out.println("The end of the Second Class, method run() *****************\n====================================\n");
		}
	}///eoclass

} // eofclass
