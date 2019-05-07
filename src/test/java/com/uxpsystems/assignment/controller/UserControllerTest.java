package com.uxpsystems.assignment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.dao.UserRepository;
import com.uxpsystems.assignment.service.UserService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	UserService userService; 
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserController userController;
	
	@Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();				
    }

	@Test
    public void testGetAllUsers() throws Exception {

		Mockito.when(userService.getAllUsers()).thenReturn(getUserList());
        mockMvc.perform(get("/Assignment/users/getAllUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
       
        Mockito.verify(userService).getAllUsers();
    }
	

	@Test
	public void testAddUser() throws Exception {
		
		String userJ = "{\n" + 
				"        \"id\": 1,\n" + 
				"        \"userName\": \"Sai\",\n" + 
				"        \"password\": \"Secret1\",\n" + 
				"        \"status\": \"Activated\"\n" + 
				"    }";
		
		
		mockMvc.perform(post("/Assignment/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(userJ))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testGetuserById() throws Exception {
		
		Mockito.when(userService.getUser(Mockito.anyLong())).thenReturn(getUser());
		
		mockMvc.perform(get("/Assignment/users/findById/10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(10)))
		.andExpect(jsonPath("$.userName", Matchers.is("Name")))
		.andExpect(jsonPath("$.status", Matchers.is("Activate")));
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
