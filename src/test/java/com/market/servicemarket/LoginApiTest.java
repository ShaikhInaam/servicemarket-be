package com.market.servicemarket;

import com.market.servicemarket.business.impl.LoginBusinessImpl;
import com.market.servicemarket.controller.LoginController;
import com.market.servicemarket.entity.UserEntity;
import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.service.base.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
//@WebMvcTest(value = LoginController.class)
//@WithMockUser
public class LoginApiTest extends AbstractTest{

    @Autowired
    private MockMvc mockMvc;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

    @Autowired
    LoginBusinessImpl loginService;

//        @Test
//        public void createStudentCourse() throws Exception {
//            LoginRequest mockCourse = new LoginRequest("1", "Smallest Number");
//
//            // studentService.addCourse to respond back with mockCourse
//            Mockito.when(
//                    loginService.authenticateUser(
//                            Mockito.any(LoginRequest.class))).thenReturn(mockCourse);
//
//            // Send course as body to /students/Student1/courses
//            RequestBuilder requestBuilder = MockMvcRequestBuilders
//                    .post("/students/Student1/courses")
//                    .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
//                    .contentType(MediaType.APPLICATION_JSON);
//
//            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//            MockHttpServletResponse response = result.getResponse();
//
//            assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//
//            assertEquals("http://localhost/students/Student1/courses/1",
//                    response.getHeader(HttpHeaders.LOCATION));
//
//        }

/*      @Test
    public void ResponseServer() throws Exception {
      String uri = "auth/login";
     LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("inaam");
        loginRequest.setPassword("inaam123");

        String inputJson = super.mapToJson(loginRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(content, "Created");
    }*/



}
