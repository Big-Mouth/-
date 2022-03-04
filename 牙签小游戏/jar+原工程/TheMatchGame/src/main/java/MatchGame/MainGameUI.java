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
	//����
	//��Ϸ���ֿ�ʼ�ĵط�
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
	
	Boolean [][] Choosen; // �Ƿ�ѡ��
	boolean isWin;
	
	int restMatch = 0;
	JFrame tempF = new JFrame();
	boolean turn; // ˭�Ļغ�
	int Level = 0;  //�Ѷ�Ĭ��Ϊ1
	int count;
	int [][]PosX;
	int [][]PosY;
	//���ѡ�еĻ������������
	Pos []ChoosenQueue;
	MatchGame MG ;
	MyRobot robot;
	Graphics Pen;
	Image img;
	Image back;
	BGM bgm;
	Thread thread;
	StartWindow startWindow;
	/***ͼƬ�زļ���***/
	//ͷ���ز�
	private Image robotImage = Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/robot.png"));
	private Image personImage;// = Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/person.png"));
	private Image choseImage = Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/chose.png"));
	
	//���캯��
	public MainGameUI() {
		//��ʼǰ����д��Ϣ �򿪿�ʼ����
		init(); //��ʼ����Ϸ
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
				//��ȡ�û���д����Ϣ
				//...
				//dispose(); //��ʱ�ر�������
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
	//	bgm = new BGM(); //���ű�������
	}
	public void init() {
		//��ʼ�����洰��
		createWindows();
	}
	public void createWindows() {
		
		//��ʼ������
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
		
		//��ʼ��

		for(int i = 0; i <= 25 ; i++)
			ChoosenQueue[i] = new Pos();
		
		for(Boolean [] arr : Choosen) {
			Arrays.fill(arr, false);
		}
		//����ÿ������X,Y
		int offX = 0,offY = 0,off = 80; //XY��ƫ����
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
    	//�����˵Ļغ�
    	turn = true;

    	tempF = this;
    	
		/**************��Ӳ˵���******************/
		JMenuBar bar = new JMenuBar();
		
		JMenu menu1 = new JMenu("�Ѷ�ѡ��");
		JMenu menu2 = new JMenu("��Ϸ����");
		
		JMenuItem again = new JMenuItem("����");	
		JMenuItem rule = new JMenuItem("����˵��");
		menu2.add(again);
		menu2.add(rule);
		bar.add(menu1);
		bar.add(menu2);
		setJMenuBar(bar);
		
		ButtonGroup buttongroup = new ButtonGroup();
		
		JRadioButtonMenuItem radioItem_1 = new JRadioButtonMenuItem("����");
		menu1.add(radioItem_1);
		radioItem_1.setSelected(true);
		JRadioButtonMenuItem radioItem_2 = new JRadioButtonMenuItem("�߼�");
		menu1.add(radioItem_2);
		
		buttongroup.add(radioItem_1);
		buttongroup.add(radioItem_2);
		
		//����л��Ѷ��¼�
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
		
		//��������¼�
		again.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
				
			}
		});
		//��ӹ���˵���¼�
		rule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog(tempF,"��Ϸ����",true);
				JLabel text = new JLabel();
				text.setText("<html><body>��Ϸ����˵��:<br>" + "��Ϸ����:������ǩ<br>" + "ģʽ:�˻���ս<br>"
				 + "�淨:��ҿ�������һ��������ȥ������������ǩ(�����Ķ���:��ǩ��ͬһ��������).<br>"
				 + "������֣�һ��һ�غϣ�˭�������һ����ǩ��˭�����ˡ�<html><body>");
				dialog.setSize(300, 300);
				dialog.setLocationRelativeTo(null);
				dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/logo.png")));
				
				dialog.setLayout(new BorderLayout());
				dialog.add(text,BorderLayout.NORTH);
				dialog.setVisible(true);
				
			}
		});

		/*****************��ʼ������****************/
		
		this.getContentPane().setBackground(Color.WHITE);
		setTitle("������ǩ�˻���ս v1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setResizable(false);
		setVisible(true);
		
		//��ʼ������ �����ɫ
		img = createImage(10,50);
		Pen = img.getGraphics();
		Pen.setColor(Color.orange);
		Pen.fillRect(0,0,MG.getMatchWight(),MG.getMatchHight());
		Pen.setColor(Color.red);
		Pen.fillRect(0,0,MG.getMatchRedWight(),MG.getMatchRedHight());
		
		//�����Ӱ����¼�
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
				
				//�ж���Щ���ѡ��
				
				boolean flag = true; //�ж������Ķ�����ͬһ��,������

				int tail = 0; //��β
				for(int i = 1; i<= 3 && flag == true; i++) {
					for(int j = 1; j <= i*2+1 && flag == true; j++) {
						if(Choosen[i][j] == true) //��ѡ�оͲ���ѡ
							continue;
						if(true == IsXJ(i,j)) {
							ChoosenQueue[++tail].x = i;
							ChoosenQueue[tail].y = j;
						}
					}
				}
				
				//�ж϶������Ԫ���Ƿ�����ͬһ�� ������
				//�Զ�ͷΪĿ��
				for(int i = 1; i <= tail /*&& flag == true*/ ; i++) {
					if(ChoosenQueue[i].x != ChoosenQueue[1].x ||
							ChoosenQueue[i].y != ChoosenQueue[1].y + i - 1)
						flag = false;
				}
				
				//Ҫô���Ǳ���ûѡ��
				if(tail == 0)
					flag = false;
				
				if(flag == true) {
					
					for(int i = 1 ; i <= tail ; i++) {
						Choosen[ChoosenQueue[i].x][ChoosenQueue[i].y] = true;
					}
					restMatch -= tail;
					
					/********�������ǵ��ԵĻغ�***********/
					
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
		
		/*********����߳�***********/
		/*********************/
		/*********************/
		//�����˵��߼��߳�
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
					//�����˵Ļغ�
					if(turn == false) {
						count += 50;
						//�����˵ĻغϽ���
						if(count >= robot.getThinkTime()) {
							//������ǩ
							restMatch -= robot.EraseMatch(Choosen);
							count = 0;
							turn = true;
							continue;
						}
						else {
							//�����˵Ļغ���Ӧ�øɵ�����
							//�ƶ����
							double speed = (robot.getMouseEndX() - robot.getMouseNowX())/(robot.getThinkTime() * 1.0) * 80.0;
							//System.out.println("�����˵�ǰ�����ٶ�Ϊ:" + speed);
							if(robot.getMouseNowX() <= robot.getMouseEndX()) {
								robot.MoveMouse((int)speed,0);
							}
						}
					}
					
					
				}
			}
		};
		
		//�߼��߳̿�ʼ
		thread.start();
	}
	/**���¿�ʼ**/
	
	public void reset() {
		init();
	}
	
	/**************�������****************/
	public void paint(Graphics g) {
		
		super.paint(g);
		
		//������ͷ��
		if(turn == false) {
			g.drawImage(choseImage, 890 - 80 - 20 - 80,130 - 10,80 + 10,80 + 10, this);
		}
		else {
			g.drawImage(choseImage, 890 - 80 - 20 - 80,130 + 80 + 30,80,80, this);
		}
		g.drawImage(robotImage, 800 - 80 - 20,130,80,80, this);
		g.drawImage(personImage, 800 - 80 - 20,130 + 80 + 30,80,80, this);

		//��������ǩ
    	for(int i = 1;i <= 3 ; i++) {
    		for(int j = 1; j<= 2*i+1 ;j++) {
    			if(Choosen[i][j] == true)
    				continue;
    			g.drawImage(img,PosX[i][j],PosY[i][j],this);
    		}
    	}
    	
    	//�˵Ļغϴ�����¼�
    	if(turn) {
    		if(restMatch == 1) {
    			LoseWinAndAgain(false); //���ˣ�������
    		}
        	//��������
        	if(true == MouseDown)
        		g.drawLine(MouseStartX, MouseStartY, MouseNowX, MouseNowY);
    	}
    	else {
    		//���Ի�������
    		g.drawLine(robot.getMouseStartX(), robot.getMouseStartY(), robot.getMouseNowX(), robot.getMouseNowY());
    	}
	}
	
	
	/**********��������****************/
	private void LoseWinAndAgain(boolean isWin) {
		thread.stop();
		
		JDialog diglog = new JDialog(tempF, "��Ӯ�ˣ���");
		if(!isWin) {
			diglog.setTitle("�����ˣ�");
		}
		
		diglog.setLayout(new BorderLayout());
		diglog.setLocationRelativeTo(null);
		diglog.setSize(300,300);
		diglog.setVisible(true);
		Button againButton = new Button("����");
		Button cannclButton = new Button("������");
		againButton.setSize(50,50);
		cannclButton.setSize(50,50);
		diglog.add(againButton,BorderLayout.NORTH);
		diglog.add(cannclButton,BorderLayout.SOUTH);
		againButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.print("����");
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
	
	/***********���㼸�εĴ���***************/
	/***********���㼸�εĴ���***************/
	
	/**** �ж� �ڼ������� �� ������ �Ƿ��ཻ****/
	private boolean IsXJ(int i,int j) {
		//�л����ĸ��߿��Ƿ������ֱ���ཻ
		boolean ok1 = false,ok2 = false;
		ok1 = java.awt.geom.Line2D.linesIntersect(MouseStartX,MouseStartY,MouseEndX,MouseEndY,
				PosX[i][j],PosY[i][j],PosX[i][j] ,PosY[i][j] + MG.getMatchHight());
		ok2 = java.awt.geom.Line2D.linesIntersect(MouseStartX,MouseStartY,MouseEndX,MouseEndY,
				PosX[i][j],PosY[i][j],PosX[i][j] + MG.getMatchRedWight() ,PosY[i][j] + MG.getMatchHight());
		
		return ok1 && ok2;
	}
	
	/**** ��������֮��ľ��� ****/
	private double GetDisFormTowPoint(int n,int m,int x,int y) {
			return Math.sqrt((n-x)*(n-x)+(m-y)*(m-y));
	}
	
	
	
	
}