package org.example.kunUz;

import org.example.kunuz.service.HttpTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KunUzApplicationTests {

	@Autowired
	private HttpTestService httpTestService;
	@Test
	void contextLoads() {
//		httpTestService.createTask();
//		httpTestService.taskGetAll();
//		httpTestService.getTaskById("b8e5efda-336d-45ab-94d4-33802c0c877b");
//		httpTestService.updateTask("b8e5efda-336d-45ab-94d4-33802c0c877b");
		httpTestService.executeGet("b8e5efda-336d-45ab-94d4-33802c0c877b");
	}

}
