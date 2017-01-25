package com.dish.test;

import org.testng.annotations.Test;

public class TestNGSuite {
	@Test
	public void f()
	{
		System.out.println("This is frist");
	}

	@Test(timeOut=1000)
	public void f1() throws InterruptedException
	{
		Thread.sleep(2000);
		System.out.println("This is second");
	}

}
