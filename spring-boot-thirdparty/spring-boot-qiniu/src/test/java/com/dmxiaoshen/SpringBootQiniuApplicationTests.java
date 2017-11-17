package com.dmxiaoshen;

import com.dmxiaoshen.constants.QiniuConstants;
import com.dmxiaoshen.utils.QiniuUtil;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootQiniuApplicationTests {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testQiniuConstants(){
		logger.info(QiniuConstants.BUCKET);
		logger.info(QiniuConstants.ACCESS_KEY);
		logger.info(QiniuConstants.SECRET_KEY);
		logger.info(QiniuConstants.PREFIX_URL);
		logger.info(QiniuConstants.TOKEN_EXPIRY+"");
		logger.info(QiniuConstants.POLICY);
	}

	@Test
	public void testUploadFile(){
		File f = new File("E:/xiaowanzi.jpeg");
		try {
			String url = QiniuUtil.uploadFile(f, UUID.randomUUID().toString().replaceAll("-",""));
			logger.info("URL={}",url);
		} catch (QiniuException e) {
			logger.error("七牛上传文件异常",e);
		}
	}

}
