package honor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import honor.Honor.mapthread;


class HonorTest {
	int i;
	@Test
	public void test() throws InterruptedException {
		mapthread map = new mapthread();
		Thread th = new Thread(map);
		for (i = 0; i < 10; i ++) {
			long milliSecond = System.currentTimeMillis();
            System.out.println("i = "  + i + ", milliSecond= " + milliSecond);
			th.start();
			Thread.sleep(100);  //增加观察效果
	}
	Thread.sleep(5000);
	}
}
