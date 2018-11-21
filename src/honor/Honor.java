package honor;
/**
 * @author jiaoxijie
 * @version 1.0
 * @date 2018.10.28
 */
import java.awt.Container;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import honor.HonorTest;

public class Honor{
	
	static char c = ' ';
    static Character [] Role = new Character [10];
    static BattleField Field = new BattleField();
	static JFrame jf = new JFrame();
	static Container con = jf.getContentPane();
    static JTextArea jta = new JTextArea();
    /**
     * mapthread线程，地图展示模块
     */
	static class mapthread implements Runnable{
		@Override
		public void run() {
			String s;
			int i, j;
			System.out.println(Thread.currentThread().getName());
			while(true)
			{
				synchronized (this)
				{
					System.out.println(Thread.currentThread().getName());
					jta.setText("");
			        for (i = 0; i < 20; i++) {
			        	for(j=0; j<20; j++)
			        	{
			        		s = BattleField.A[i][j] + " ";
			        		jta.append(s);
			        		jta.append(" ");
				        }
			        	jta.append("\n");
			        }
			            JPanel jp = new JPanel();
				        jp.setBounds(0, 0, 1000, 1000);
				        jp.setLayout(null);
				        jp.add(jta);
				        con.add(jp);
				        jf.setVisible(true);
			            try {
							Thread.sleep(500);
							}
						catch(InterruptedException e) {
			            }
				}
					
		}
	}
	}
    static class machinethread implements Runnable{
    	Random rand = new Random();
		@Override
		public synchronized void run() {
			int i, x, y;
			for(i = 0; i < 10; i++)
			{
				x = Role[i].x;
				y = Role[i].y;
				if(x != 0)
					if(BattleField.A[y][x-1] == '*')
					{
						if(rand.nextInt(2) == 1)
							BattleField.A[y][x-1] = '~';
					}
				if(y != 0)
					if(BattleField.A[y-1][x] == '*')
					{
						if(rand.nextInt(2) == 1)
							BattleField.A[y-1][x] = '~';
					}
				if(y != 19)
					if(BattleField.A[y+1][x] == '*')
					{
						if(rand.nextInt(2) == 1)
							BattleField.A[y+1][x] = '~';
					}
				if(x != 19)
					if(BattleField.A[y][x+1] == '*')
					{
						if(rand.nextInt(2) == 1)
							BattleField.A[y][x+1] = '~';
					}
			}
		}		
    }
    
    
    static class processthread implements Runnable{
		int num, l, i;
		@Override
		public synchronized void run(){
			FileInputStream in = null;
			try {
				in = new FileInputStream("/Users/jiaoxijie/eclipse-workspace/"
						+ "Honor/src/honor/honor.txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
					try {
						while((c = (char)in.read()) != 'Q')
						{
							num = in.read()-47;
							l = in.read() - 48;
							if(l == 1)
							{
								Role[num-1].move(num, c);
								System.out.println("There is a hero moving");
							}
							else
							{
								if(l == 2)
									((typeone) Role[num-1]).skill(num, c);
								else
									((typetwo) Role[num-1]).skill(num, c);
								System.out.println("There is a hero using a skill");
							}
							try {
								Thread.sleep(2000);
							}catch(Exception e) {}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			}	
		}
    
	public static void main(String[] args){
		Random rand = new Random();
	    jf.getContentPane().setLayout(null);
	    jf.setBounds(100, 100, 330, 350);
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jta.setBounds(0, 0, 330, 350);
		int i, k, m;
		BattleField.Initiate();
		for(i = 0; i < 10; i++)
		{
			k = rand.nextInt(1) + 1;
			if(k == 1)
				Role[i] = new typeone();
			else 
				Role[i] = new typetwo();
			Role[i].Name = (char)(i + 65);
		}
		for(i = 0; i < 10; i++)
		{
			do
			{
				m = rand.nextInt(20);
				k = rand.nextInt(20);
			} while(BattleField.A[k][m] != '0');
			Role[i].x = m;
			Role[i].y = k;
			BattleField.A[k][m] = Role[i].Name;
		}
		mapthread map = new mapthread();
		machinethread machine = new machinethread();
		processthread process = new processthread();
		Thread th1 = new Thread(map);
		Thread th2 = new Thread(machine);
		Thread th3 = new Thread(process);
		th1.start();
		th2.start();
		th3.start();
	}
}


