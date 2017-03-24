package com.qait.TATOC;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HrisTest {
	AppActions test;
	/*
	@BeforeClass
	public void getData(){
		test = new AppActions();
		test.LaunchHRIS();
		
	}
	*/
	@Test(priority=1)
	public void HrisLogin() throws IOException{
		test = new AppActions();
		test.LaunchHRIS();
		test.HrisLogin();
		
	}
}
