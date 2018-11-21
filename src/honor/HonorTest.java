package honor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import honor.Honor.mapthread;


class HonorTest {
	@Test
	void test_barrier() {
		Random rand = new Random();
		int i;
		for(i = 0; i < 10; i++)
		{
			BattleField A = new BattleField();
			assertEquals(true ,(A.get_x() != '*'));
		}
	}
	@Test
	void test_x() {
		Random rand = new Random();
		int i;
		for(i = 0; i < 10; i++)
		{
			BattleField A = new BattleField();
			assertEquals(true ,(A.get_x() < 20));
		}
	}
	@Test
	void test_y() {
		Random rand = new Random();
		int i;
		for(i = 0; i < 10; i++)
		{
			BattleField A = new BattleField();
			assertEquals(true ,(A.get_y() < 20));
		}
	}
	
	
	@Test
	void test_Mp() {
		Random rand = new Random();
		int i;
		char c;
		for(i = 0; i < 10; i++)
		{
			typeone A = new typeone();
			A.skill(i+1, 's');
			assertEquals(90 ,A.ret_Mp());
		}
	}
	
	@Test
	void test_Hp() {
		Random rand = new Random();
		int i;
		for(i = 0; i < 10; i++)
		{
			typeone A = new typeone();
			A.skill(i+1, 's');
			assertEquals(100 ,A.ret_Hp());
		}
	}
	
	@Test
	void test_Boundary() {
		Random rand = new Random();
		int i;
		char c;
		for(i = 0; i < 10; i++)
		{
			typeone A = new typeone();
			c = (char)(rand.nextInt(10)+65);
			A.skill(i+1, 's');
			assertEquals(true,(A.ret_x() < 20));
			assertEquals(true,(A.ret_y() < 20));
		}
	}

	@Test
	public void test() throws InterruptedException {
		for (int i = 0; i < 10; i ++) {
			long milliSecond = System.currentTimeMillis();
            System.out.println("i = "  + i + ", milliSecond= " + milliSecond);
            mapthread map = new mapthread();
    		Thread th = new Thread(map);
			th.start();
			Thread.sleep(100);  //增加观察效果
	}
	Thread.sleep(5000);
	}
}
