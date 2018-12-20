package org.demo.spring;

import org.demo.spring.service.EmailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestNG + Spring集成测试	样例
 * https://www.yiibai.com/testng/testng-spring-integration.html
 * @author DHJT 2018年10月30日 上午11:24:23
 *
 */
@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class TestSpring extends AbstractTestNGSpringContextTests {

    @Autowired
    EmailGenerator emailGenerator;

    @Test()
    void testEmailGenerator() {

        String email = emailGenerator.generate();
        System.out.println(email);

        Assert.assertNotNull(email);
        Assert.assertEquals(email, "feedback@yiibai.com");
    }

}