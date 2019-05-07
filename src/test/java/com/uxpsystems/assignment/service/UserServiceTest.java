package com.uxpsystems.assignment.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.dao.UserRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	
	private MockMvc mockMvc;
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userService)
                .build();
	}

	
	@Test
    public void testGetAllUsers() throws Exception {

		Mockito.when(userRepository.findAll()).thenReturn(getUserList());
        userService.getAllUsers();
       
        Mockito.verify(userRepository).findAll();
    }
	
	@Test
	public void testGetUserById() throws Exception {
		
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getUser()));
		
		assertThat(userService.getUser(10L).getUserName()).isEqualTo("Name");
		
		
	}
	
	private List<User> getUserList() {
		User user = new User(new Long("10"),"Name","password","Activated");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		
		return userList;
	}
	
	private User getUser() {
		User user = new User(new Long("10"),"Name","password","Activated");
		
		
		return user;
	}
}
