package com.nts.intern.school;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nts.intern.school.person.dao.StudentDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSchoolApplicationTests {
	
	@Autowired
	StudentDao studentDao;
	
	@Test
	public void contextLoads() {
		studentDao.deleteStudent(1);
	}

}
