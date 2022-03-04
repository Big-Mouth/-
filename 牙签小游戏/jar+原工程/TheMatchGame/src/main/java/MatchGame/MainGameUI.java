package main.java.MatchGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class MainGameUI extends JFrame{
	private static final long serialVersionUID = 1L;
	//变量
	//游戏布局开始的地方
	int startX;
	int startY;
	int mid;
	
	int MouseStartX;
	int MouseStartY;
	int MouseNowX;
	int MouseNowY;
	int MouseEndX;
	int MouseEndY;
	
	boolean MouseDown;
	
	Boolean [][] Choosen; // 是否被选中
	boolean isWin;
	
	int restMatch = 0;
	JFrame tempF = new JFrame();
	boolean turn; // 谁的回合
	int Level = 0;  //难度默认为1
	int count;
	int [][]PosX;
	int [][]PosY;
	//玩家选中的火柴棍的坐标队列
	Pos []ChoosenQueue;
	MatchGame MG ;
	MyRobot robot;
	Graphics Pen;
	Image img;
	Image back;
	BGM bgm;
	Thread thread;
	StartWindow startWindow;
	/***图片素材加载***/
	//头像素材
	private Image robotImage = Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/robot.png"));
	private Image personImage;// = Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/person.png"));
	private Image choseImage = Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/chose.png"));
	
	//构造函数
	public MainGameUI() {
		//开始前先填写信息 打开开始界面
		init(); //初始化游戏
		startWindow = new StartWindow();
	//	setEnabled(false);
		startWindow.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				//获取用户填写的信息
				//...
				//dispose(); //暂时关闭主窗口
				personImage = startWindow.getPersonImage();
				Level = startWindow.getLevel();
				requestFocus();
			//	setEnabled(true);
				setVisible(true);
				//setExtendedState(JFrame.NORMAL);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//setEnabled(false);
		
		//
	//	bgm = new BGM(); //播放背景音乐
	}
	public void init() {
		//初始化界面窗口
		createWindows();
	}
	public void createWindows() {
		
		//初始化变量
		MG =  new MatchGame();
		startX = 150;
		startY = 30;
		mid = 100;  
		Choosen = new Boolean [8][8];

		PosX = new int[8][8];
		PosY = new int[8][8];
		ChoosenQueue = new  Pos[26];
		
		if(Level == 0)
			Level = 1;
		
		robot = new MyRobot();
		
		//初始化

		for(int i = 0; i <= 25 ; i++)
			ChoosenQueue[i] = new Pos();
		
		for(Boolean [] arr : Choosen) {
			Arrays.fill(arr, false);
		}
		//计算每个火柴的X,Y
		int offX = 0,offY = 0,off = 80; //XY的偏移量
    	for(int i = 1;i <= 3 ; i++) {
    		offX = -1 * (i+1)*off/2;
    		offY += 80;
    		for(int j = 1; j<= i*2+1 ;j++) {
    			PosX[i][j] = startX+ mid + offX;
    			PosY[i][j] = startY + offY;
				offX += off;
    		}
    	}
    	restMatch = 15;
    	//先是人的回合
    	turn = true;

    	tempF = this;
    	
		/**************添加菜单栏******************/
		JMenuBar bar = new JMenuBar();
		
		JMenu menu1 = new JMenu("难度选择");
		JMenu menu2 = new JMenu("游戏设置");
		
		JMenuItem again = new JMenuItem("重来");	
		JMenuItem rule = new JMenuItem("规则说明");
		menu2.add(again);
		menu2.add(rule);
		bar.add(menu1);
		bar.add(menu2);
		setJMenuBar(bar);
		
		ButtonGroup buttongroup = new ButtonGroup();
		
		JRadioButtonMenuItem radioItem_1 = new JRadioButtonMenuItem("初级");
		menu1.add(radioItem_1);
		radioItem_1.setSelected(true);
		JRadioButtonMenuItem radioItem_2 = new JRadioButtonMenuItem("高级");
		menu1.add(radioItem_2);
		
		buttongroup.add(radioItem_1);
		buttongroup.add(radioItem_2);
		
		//添加切换难度事件
		radioItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Level = 1;
				thread.stop();
				reset();
				
			}
		});
		
		radioItem_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Level = 2;
				thread.stop();
				reset();
			}
		});
		
		//添加重来事件
		again.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
				
			}
		});
		//添加规则说明事件
		rule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog(tempF,"游戏规则",true);
				JLabel text = new JLabel();
				text.setText("<html><body>游戏规则说明:<br>" + "游戏名称:三行牙签<br>" + "模式:人机对战<br>"
				 + "玩法:玩家可以引导一根线条划去任意连续的牙签(连续的定义:牙签在同一行且相邻).<br>"
				 + "玩家先手，一人一回合，谁划掉最后一根牙签，谁就输了。<html><body>");
				dialog.setSize(300, 300);
				dialog.setLocationRelativeTo(null);
				dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/logo.png")));
				
				dialog.setLayout(new BorderLayout());
				dialog.add(text,BorderLayout.NORTH);
				dialog.setVisible(true);
				
			}
		});

		/*****************初始化窗口****************/
		
		this.getContentPane().setBackground(Color.WHITE);
		setTitle("三行牙签人机对战 v1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setResizable(false);
		setVisible(true);
		
		//初始化画笔 火柴颜色
		img = createImage(10,50);
		Pen = img.getGraphics();
		Pen.setColor(Color.orange);
		Pen.fillRect(0,0,MG.getMatchWight(),MG.getMatchHight());
		Pen.setColor(Color.red);
		Pen.fillRect(0,0,MG.getMatchRedWight(),MG.getMatchRedHight());
		
		//鼠标添加按下事件
		addMouseListener( new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				/*if(e.getButton() == e.BUTTON3)
					repaint();*/
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(turn == false)
					return;
				MouseStartX = e.getX();
				MouseStartY = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(turn == false)
					return;
				
				MouseDown = false;
				MouseEndX = e.getX();
				MouseEndY = e.getY();
				
				//判断哪些火柴被选中
				
				boolean flag = true; //判断消除的都是在同一行,且连续

				int tail = 0; //队尾
				for(int i = 1; i<= 3 && flag == true; i++) {
					for(int j = 1; j <= i*2+1 && flag == true; j++) {
						if(Choosen[i][j] == true) //被选中就不再选
							continue;
						if(true == IsXJ(i,j)) {
							ChoosenQueue[++tail].x = i;
							ChoosenQueue[tail].y = j;
						}
					}
				}
				
				//判断队列里的元素是否都是在同一行 且连续
				//以队头为目标
				for(int i = 1; i <= tail /*&& flag == true*/ ; i++) {
					if(ChoosenQueue[i].x != ChoosenQueue[1].x ||
							ChoosenQueue[i].y != ChoosenQueue[1].y + i - 1)
						flag = false;
				}
				
				//要么就是本轮没选中
				if(tail == 0)
					flag = false;
				
				if(flag == true) {
					
					for(int i = 1 ; i <= tail ; i++) {
						Choosen[ChoosenQueue[i].x][ChoosenQueue[i].y] = true;
					}
					restMatch -= tail;
					
					/********接下来是电脑的回合***********/
					
					if(restMatch == 1)
						LoseWinAndAgain(true);
					
					turn = false;
					
					robot.Turn(MG,Level,Choosen, PosX, PosY);
					
				}
					
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if(turn == false)
					return;
				MouseDown = true;
				MouseNowX = e.getX();
				MouseNowY = e.getY();
			//	repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		/*********添加线程***********/
		/*********************/
		/*********************/
		//机器人的逻辑线程
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
					//机器人的回合
					if(turn == false) {
						count += 50;
						//机器人的回合结束
						if(count >= robot.getThinkTime()) {
							//消除牙签
							restMatch -= robot.EraseMatch(Choosen);
							count = 0;
							turn = true;
							continue;
						}
						else {
							//机器人的回合内应该干的事情
							//移动鼠标
							double speed = (robot.getMouseEndX() - robot.getMouseNowX())/(robot.getThinkTime() * 1.0) * 80.0;
							//System.out.println("机器人当前画笔速度为:" + speed);
							if(robot.getMouseNowX() <= robot.getMouseEndX()) {
								robot.MoveMouse((int)speed,0);
							}
						}
					}
					
					
				}
			}
		};
		
		//逻辑线程开始
		thread.start();
	}
	/**重新开始**/
	
	public void reset() {
		init();
	}
	
	/**************界面绘制****************/
	public void paint(Graphics g) {
		
		super.paint(g);
		
		//画人物头像
		if(turn == false) {
			g.drawImage(choseImage, 890 - 80 - 20 - 80,130 - 10,80 + 10,80 + 10, this);
		}
		else {
			g.drawImage(choseImage, 890 - 80 - 20 - 80,130 + 80 + 30,80,80, this);
		}
		g.drawImage(robotImage, 800 - 80 - 20,130,80,80, this);
		g.drawImage(personImage, 800 - 80 - 20,130 + 80 + 30,80,80, this);

		//画三行牙签
    	for(int i = 1;i <= 3 ; i++) {
    		for(int j = 1; j<= 2*i+1 ;j++) {
    			if(Choosen[i][j] == true)
    				continue;
    			g.drawImage(img,PosX[i][j],PosY[i][j],this);
    		}
    	}
    	
    	//人的回合处理的事件
    	if(turn) {
    		if(restMatch == 1) {
    			LoseWinAndAgain(false); //输了，再重来
    		}
        	//画引导线
        	if(true == MouseDown)
        		g.drawLine(MouseStartX, MouseStartY, MouseNowX, MouseNowY);
    	}
    	else {
    		//电脑画引导线
    		g.drawLine(robot.getMouseStartX(), robot.getMouseStartY(), robot.getMouseNowX(), robot.getMouseNowY());
    	}
	}
	
	
	/**********重来窗口****************/
	private void LoseWinAndAgain(boolean isWin) {
		thread.stop();
		
		JDialog diglog = new JDialog(tempF, "你赢了！！");
		if(!isWin) {
			diglog.setTitle("你输了！");
		}
		
		diglog.setLayout(new BorderLayout());
		diglog.setLocationRelativeTo(null);
		diglog.setSize(300,300);
		diglog.setVisible(true);
		Button againButton = new Button("重来");
		Button cannclButton = new Button("不玩了");
		againButton.setSize(50,50);
		cannclButton.setSize(50,50);
		diglog.add(againButton,BorderLayout.NORTH);
		diglog.add(cannclButton,BorderLayout.SOUTH);
		againButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.print("重来");
				diglog.dispose();
				reset();
			}
		});
		
		cannclButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 System.exit(0);
			}
		});
	}
	
	/***********计算几何的处理***************/
	/***********计算几何的处理***************/
	
	/**** 判断 第几个火柴棍 跟 引导线 是否相交****/
	private boolean IsXJ(int i,int j) {
		//判火柴棍四个边框是否与鼠标直线相交
		boolean ok1 = false,ok2 = false;
		ok1 = java.awt.geom.Line2D.linesIntersect(MouseStartX,MouseStartY,MouseEndX,MouseEndY,
				PosX[i][j],PosY[i][j],PosX[i][j] ,PosY[i][j] + MG.getMatchHight());
		ok2 = java.awt.geom.Line2D.linesIntersect(MouseStartX,MouseStartY,MouseEndX,MouseEndY,
				PosX[i][j],PosY[i][j],PosX[i][j] + MG.getMatchRedWight() ,PosY[i][j] + MG.getMatchHight());
		
		return ok1 && ok2;
	}
	
	/**** 计算两点之间的距离 ****/
	private double GetDisFormTowPoint(int n,int m,int x,int y) {
			return Math.sqrt((n-x)*(n-x)+(m-y)*(m-y));
	}
	
	
	
	
}