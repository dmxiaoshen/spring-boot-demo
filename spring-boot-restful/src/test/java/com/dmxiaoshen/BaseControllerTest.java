package com.dmxiaoshen;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by hzhsg on 2017/11/9.
 */
public abstract class BaseControllerTest {

    protected  static final String URL = "http://localhost:8083/";

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
