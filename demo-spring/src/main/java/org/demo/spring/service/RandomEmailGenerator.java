package org.demo.spring.service;

import org.springframework.stereotype.Service;

@Service
public class RandomEmailGenerator implements EmailGenerator {

	@Override
	public String generate() {
        return "feedback@yiibai.com";
    }

}
