package org.dhjt.JarTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dhjt.JarTest.App;

public class AppNgTest {

	@Test()
    public void testEmailGenerator() {

        App app = new App();
        String email = app.generate();

        Assert.assertNotNull(email);
        Assert.assertEquals(email, "feedback@yiibai.com");
    }

	@Test(expectedExceptions = ArithmeticException.class)
	public void divisionWithException() {
		int i = 1/0;
	}

	@Test(enabled=false)
	public void divisionWithException1() {
		System.out.println("Method is not ready yet");
	}

	@Test(timeOut = 1000)
	public void infinity() {
		while (true);
	}
}
