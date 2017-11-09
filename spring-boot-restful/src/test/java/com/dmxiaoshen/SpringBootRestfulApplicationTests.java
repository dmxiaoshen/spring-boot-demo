package com.dmxiaoshen;

import com.dmxiaoshen.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestfulApplicationTests extends BaseControllerTest{

	@Test
	public void testBookController() throws Exception {
		RequestBuilder request = null;
		// 查询所有书籍
		request = get(URL+"/books/");
		mvc.perform(request).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(equalTo("[]")));

		// 增加一本书籍
		request = post(URL+"/books/")
				.param("id","1")
				.param("name","java编程技术")
				.param("price","99.8")
				.param("description","nice book");
		mvc.perform(request).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(equalTo("success")));

        //获取一本书籍
		request = get(URL+"/books/{id}","1");
		mvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string(equalTo("{" +
						"\"id\":\"1\"," +
						"\"name\":\"java编程技术\"," +
						"\"price\":99.8," +
						"\"description\":\"nice book\"" +
						"}")));

		// 修改一本书籍
		Book book = new Book();
		book.setId("1");
		book.setName("java编程技术");
		book.setPrice(new BigDecimal("58.9"));
		book.setDescription("cheap");
		request = put(URL+"/books/{id}",1).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(book));
		mvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		//获取一本书籍
		request = get(URL+"/books/{id}","1");
		mvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string(equalTo("{" +
						"\"id\":\"1\"," +
						"\"name\":\"java编程技术\"," +
						"\"price\":58.9," +
						"\"description\":\"cheap\"" +
						"}")));

		//删除一本书
		request = delete(URL+"/books/{id}","1");
		mvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));
	}

}
