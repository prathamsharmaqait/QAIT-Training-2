package com.qait.TATOC;


import org.testng.annotations.Test;

import com.qait.TATOC.AppActions;

/**
 * Unit test for simple App.
 */

public class TATOCTest
{
	AppActions test = new AppActions();
	
	@Test(priority=1)
	public void launchUrl(){
		test.LaunchWebsite();
	}
	
	@Test(priority=2)
	public void repaint_Box2_until_color_matches_with_Box1(){
	//test.testFrameSwitch();
		test.repaintBox2();
	}
	
	@Test(priority=3)
	public void DragAndDrop(){
		test.dragDrop();
	}
	
	@Test(priority=4)
	public void PopUpWindows(){
		test.PopupWindows();
	}

	@Test(priority=5)
	public void Cookies(){
		test.CookieHandling();
	}
	
	@Test(priority=6)
	public void GriGateTest(){
		test.GridGate();
	}
}
