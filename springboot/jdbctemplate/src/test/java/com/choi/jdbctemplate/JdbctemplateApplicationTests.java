package com.choi.jdbctemplate;

import com.choi.jdbctemplate.bean.User;
import com.choi.jdbctemplate.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbctemplateApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		User user = new User();
		user.setUsername("choibin");
		user.setAddress("China");
		userService.addUser(user);
	}

}
