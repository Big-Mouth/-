package main.java.MatchGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class StartWindow extends JFrame{
	JFrame self = this;
	//人物姓名
	String PersonName;
	//难度
	int Level;
	// 人物头像
	private Image  [] personImage;
	private int imagenum;
	private Image textImage = Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/person.png"));
	public StartWindow() {
		//初始化
		setLayout(null);
		setSize(500,450);
		setTitle("开始前请完成相关信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		personImage = new Image[3];
		personImage[1] =  Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/person.png"));
		personImage[2] =  Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/person2.png"));
		
		imagenum = 1;
		Level = 1;
		
		//初始化组件
		InitComponent();
		
		
		
		setVisible(true);
		

	}
	public void InitComponent() {
		
		Point pos ;
		//继续按钮
		JButton continueButton = new JButton("继续");
		continueButton.setSize(100,30);
		continueButton.setLocation(getWidth()/2 - 50, getHeight() - 100);
		continueButton.setEnabled(false);
		continueButton.setVisible(true);
		add(continueButton);	

		
		/********头像选择*********/
		JLabel chooseHeadJLabel= new JLabel();
		chooseHeadJLabel.setText("请选择你的头像:");
		chooseHeadJLabel.setLocation(10, 20);
		chooseHeadJLabel.setSize(150,30);
		JRadioButton HeadButton1 = new JRadioButton("头像1");
		JRadioButton HeadButton2 = new JRadioButton("头像2");
		HeadButton1.setSize(70,30);
		HeadButton1.setLocation(10,130);
		HeadButton1.setSelected(true);
		HeadButton2.setSize(70,30);
		HeadButton2.setLocation(HeadButton1.getLocation().x + HeadButton1.getWidth() + 50,
				HeadButton1.getLocation().y);
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(HeadButton1);
		buttonGroup1.add(HeadButton2);
		add(chooseHeadJLabel);
		add(HeadButton1);
		add(HeadButton2);
		
		HeadButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				imagenum = 1;
			}
		});
		
		HeadButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				imagenum = 2;
			}
		});
		
		
		/********难度选择*********/
		JLabel levelLabel = new JLabel("难度选择:");
		levelLabel.setSize(150,30);
		levelLabel.setLocation(10,160);
		JRadioButton levelButton1 = new JRadioButton("初级");
		JRadioButton levelButton2 = new JRadioButton("高级");
		levelButton1.setSize(70,30);
		levelButton1.setLocation(10,200);
		levelButton2.setSize(70,30);
		levelButton2.setLocation(levelButton1.getLocation().x + levelButton1.getWidth(),
				levelButton1.getLocation().y);
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(levelButton1);
		buttonGroup2.add(levelButton2);
		add(levelLabel);
		add(levelButton1);
		add(levelButton2);
		
		levelButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Level = 1;
			}
		});
		
		levelButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Level = 2;
			}
		});
		
		
		/********昵称输入*********/
		JLabel cinNameJLabel = new JLabel();
		cinNameJLabel.setText("请输入玩家的昵称:");
		cinNameJLabel.setSize(150,30);
		cinNameJLabel.setLocation(10, 250);
		
		pos = cinNameJLabel.getLocation();
		JTextField cinNameField = new JTextField();
		cinNameField.setSize(190,30);
		cinNameField.setLocation(pos.x,pos.y + 25);
		
		pos = cinNameField.getLocation();
		JLabel nameWaringJLabel = new JLabel("*");
		nameWaringJLabel.setSize(160,30);
		nameWaringJLabel.setLocation(pos.x + cinNameField.getWidth() + 10, pos.y);
		nameWaringJLabel.setVisible(false);
		
		add(cinNameJLabel);
		add(cinNameField);
		add(nameWaringJLabel);
		

		//文本框输入事件
		cinNameField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				String textString = cinNameField.getText();
				boolean hasBlank = false;
				for(int i = 0; i < textString.length() ; i++)
					if(textString.charAt(i) == ' ') {
						hasBlank = true;
						break;
					}
						
				//有内容 且不为空格
				/*显示提示内容 不允许含有空格*/

			
				if(hasBlank || cinNameField.getText().length() == 0) {
					continueButton.setEnabled(false);
					
					if(hasBlank) {
						nameWaringJLabel.setText("昵称中请不要含有空格");
					}else {
						nameWaringJLabel.setText("*");
					}
					nameWaringJLabel.setVisible(true);

				} else {
					continueButton.setEnabled(true);
					nameWaringJLabel.setVisible(false);
				} 
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		//按钮按下事件
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//防止卡BUG 
				if(cinNameField.getText().length() == 0) {
					return ;
				}else {
					PersonName = cinNameField.getText();
					self.dispose();
				}
			}
		});
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		g.drawImage(personImage[1], 20,100,50,50, this);
		g.drawImage(personImage[2], 20 + 120,100,50,50, this);
		//g.drawImage(personImage[2], 50, 50, 100, 50, 50, 100, 100, 100, this);
	}
	
	public String getPersonName() {
		return PersonName;
	}
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
	public Image getPersonImage() {
		return personImage[imagenum];
	}
	
}


/*

 * */