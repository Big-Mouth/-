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
	//��������
	String PersonName;
	//�Ѷ�
	int Level;
	// ����ͷ��
	private Image  [] personImage;
	private int imagenum;
	private Image textImage = Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/person.png"));
	public StartWindow() {
		//��ʼ��
		setLayout(null);
		setSize(500,450);
		setTitle("��ʼǰ����������Ϣ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		personImage = new Image[3];
		personImage[1] =  Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/person.png"));
		personImage[2] =  Toolkit.getDefaultToolkit().getImage(MainGameUI.class.getResource("/images/person2.png"));
		
		imagenum = 1;
		Level = 1;
		
		//��ʼ�����
		InitComponent();
		
		
		
		setVisible(true);
		

	}
	public void InitComponent() {
		
		Point pos ;
		//������ť
		JButton continueButton = new JButton("����");
		continueButton.setSize(100,30);
		continueButton.setLocation(getWidth()/2 - 50, getHeight() - 100);
		continueButton.setEnabled(false);
		continueButton.setVisible(true);
		add(continueButton);	

		
		/********ͷ��ѡ��*********/
		JLabel chooseHeadJLabel= new JLabel();
		chooseHeadJLabel.setText("��ѡ�����ͷ��:");
		chooseHeadJLabel.setLocation(10, 20);
		chooseHeadJLabel.setSize(150,30);
		JRadioButton HeadButton1 = new JRadioButton("ͷ��1");
		JRadioButton HeadButton2 = new JRadioButton("ͷ��2");
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
		
		
		/********�Ѷ�ѡ��*********/
		JLabel levelLabel = new JLabel("�Ѷ�ѡ��:");
		levelLabel.setSize(150,30);
		levelLabel.setLocation(10,160);
		JRadioButton levelButton1 = new JRadioButton("����");
		JRadioButton levelButton2 = new JRadioButton("�߼�");
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
		
		
		/********�ǳ�����*********/
		JLabel cinNameJLabel = new JLabel();
		cinNameJLabel.setText("��������ҵ��ǳ�:");
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
		

		//�ı��������¼�
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
						
				//������ �Ҳ�Ϊ�ո�
				/*��ʾ��ʾ���� �������пո�*/

			
				if(hasBlank || cinNameField.getText().length() == 0) {
					continueButton.setEnabled(false);
					
					if(hasBlank) {
						nameWaringJLabel.setText("�ǳ����벻Ҫ���пո�");
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
		
		//��ť�����¼�
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��ֹ��BUG 
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