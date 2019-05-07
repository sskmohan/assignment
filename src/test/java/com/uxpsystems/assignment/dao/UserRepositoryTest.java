package com.uxpsystems.assignment.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testSave() {
		User user = getUser();
		testEntityManager.persist(user);
		User userFromDB = userRepository.findByUserName("Name");
		System.out.println(userFromDB.toString());
		assertThat(userFromDB).isEqualTo(user);
	}

	private User getUser() {
		User user = new User(new Long("10"),"Name","password","Activated");
		
		return user;
	}
}
