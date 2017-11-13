package com.dmxiaoshen;

import com.dmxiaoshen.entity.Student;
import com.dmxiaoshen.service.StudentService;
import org.assertj.core.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJdbctemplateApplicationTests {

	@Autowired
	private StudentService studentService;

	/**
	 * @Rollback标签是需要配合@Transcational标签共同使用的，
	 * 你可以理解为在Junit下对于加了Transactional标签的方法，会默认视为@Rollback(true)，
	 * 如果让这个方法不回滚，那么就要手动设置@Rollback(false)。
	 */
	@Test
	@Transactional
	public void testAddStudent(){
		Student student = new Student();
		student.setAge(16);
		student.setBirthday(DateUtil.parse("1997-04-12"));
		student.setDelFlag(false);
		student.setStatus("0");
		student.setInformation("小李-15");
		student.setName("小李");
		student.setRemarks("备注");
		student.setCreateBy(null);
		student.setUpdateBy(null);
		student.setCreateTime(new Date());
		student.setUpdateTime(student.getCreateTime());

		Assert.assertEquals(1,studentService.addStudent(student));
	}

	@Test
	public void testUpdateStudent(){
		Student student = new Student();
		student.setId("b4f6221c88554ec4a3baebb8ecd3b098");
		student.setAge(19);
		student.setName("小楠三");

		Assert.assertEquals(1,studentService.updateStudent(student));
	}

	@Test
	public void testGetStudentById(){
		Student student = studentService.getStudentById("b4f6221c88554ec4a3baebb8ecd3b098");
		Assert.assertNotNull(student);
	}

	@Test
	public void testGetStudentSimpleById(){
		Student student = studentService.getStudentSimpleById("b4f6221c88554ec4a3baebb8ecd3b098");
		Assert.assertNotNull(student);
	}

	@Test
	public void testGetStudentList(){
		List<Student> studentList = studentService.getStudentList();
		Assert.assertEquals(2, studentList.size());
	}

	@Test
	@Transactional
	public void testDeleteStudent(){
		Assert.assertEquals(1,studentService.deleteStudent("b4f6221c88554ec4a3baebb8ecd3b098"));
	}

}
