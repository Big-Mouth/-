package main.java.MatchGame;

class Pos {
	
	public int x;
	public int y;
}

public class MyRobot {
	
	int MouseStartX;
	int MouseStartY;
	int MouseNowX;
	int MouseNowY;
	int MouseEndX;
	int MouseEndY;
	long ThinkTime;
	
	Pos [] Con;
	int [] ConTask;
	int Step;
	int [] NowCom;
	public MyRobot() {
		Init();
	}
	private void Init() {
		
		//1.5��˼��ʱ��
		ThinkTime = 1500;
		
		Con = new Pos [10];
		for(int i = 0; i<=7 ; i++)
			Con[i] = new Pos();
		
		ConTask = new int [10];
		NowCom = new int [10];

	}
	/***�����˵�����ƶ�***/
	public void MoveMouse(int offx,int offy) {
		MouseNowX += offx;
		MouseNowY += offy;
	}
	

	/******ģ����Իغ�******/
	public void Turn(MatchGame MG,int Level,Boolean [][] Choosen,int [][] PosX,int [][] PosY) {
		//���㵱ǰ����ǩ���
		NowCom  = CalCombination(Choosen);
		//�ҵ���һ����ô��
		Step = MG.getDp(NowCom);
		if(Step == 0) {
			RandomStep();
		} else {
			//���Ѷ���
			if(Level == 1) {
				int random = (int)(Math.random()*100.0);
				if(random <= 50) {
					RandomStep();
				}else {
					ConTask = MG.GetConTask(Step);
				}
			} else if(Level == 2) {
				ConTask = MG.GetConTask(Step);
			}
		}

		//������������ŵ�λ��
		InitMouse(PosX,PosY,MG);

	}
	/*****����������ǩ�������ǵĵ�һ����ǩ��ʼ��X,Y*****/

	
	/** ��ʼ��һ�µ���������ʼλ�úͽ���λ��***/
	public  void InitMouse(int [][] PosX,int [][] PosY,MatchGame MG) {
		int tempWight = (ConTask[3]) * (MG.getMatchWightBlank() + MG.getMatchWight() + 7) - MG.getMatchWightBlank()/2;

		MouseStartX = PosX[Con[ConTask[1]].x][Con[ConTask[1]].y] - 10;
		MouseStartY = PosY[Con[ConTask[1]].x][Con[ConTask[1]].y] + 20;
		
		MouseEndX = PosX[Con[ConTask[1]].x][Con[ConTask[1]].y] - 10 + tempWight;
		MouseEndY = PosY[Con[ConTask[1]].x][Con[ConTask[1]].y] + 20 ;
		
		MouseNowX = MouseStartX;
		MouseNowY = MouseStartY;
	}
	
	/********���㵱ǰ��ͼ�����*******/
	private int[] CalCombination(Boolean Choosen [][]) {
		int [] a = new int [8];
		for( int i = 1 ; i <= 7 ; i++) {
			Con[i].x = 0;
			Con[i].y = 0;
		}
		for(int i = 1; i <= 3 ; i++) {
			for(int j = 1; j <= 2*i+1 ; j++) {
				int c = 0;
				int startY = j;
				while( j <= 2*i+1 && Choosen[i][j] == false) {
					c++;
					j++;
				}
				if(c != 0) {
					a[c]++;
					
					Con[c].x = i;
					Con[c].y = startY;
				}
			}
		}
		return a;
	}
	/*******��������******/
	public int EraseMatch(Boolean [][] Choosen) {	
		for(int i = 1; i<= ConTask[3]; i++) {
			Choosen[Con[ConTask[1]].x][Con[ConTask[1]].y + ConTask[2] - 1 + i - 1] = true;
		}
		return ConTask[3];
	}
	/******�����һ��****/
	private void RandomStep() {
		
		for(int i = 1 ; i <= 7 ; i++) {
			if(Con[i].x != 0  && Con[i].y != 0) {
				ConTask[1] = i;
				ConTask[2] = 1;
				ConTask[3] = 1;
				break;
			}
		}
	}
	public int getMouseStartX() {
		return MouseStartX;
	}
	public void setMouseStartX(int mouseStartX) {
		MouseStartX = mouseStartX;
	}
	public int getMouseStartY() {
		return MouseStartY;
	}
	public void setMouseStartY(int mouseStartY) {
		MouseStartY = mouseStartY;
	}
	public int getMouseNowX() {
		return MouseNowX;
	}
	public void setMouseNowX(int mouseNowX) {
		MouseNowX = mouseNowX;
	}
	public int getMouseNowY() {
		return MouseNowY;
	}
	public void setMouseNowY(int mouseNowY) {
		MouseNowY = mouseNowY;
	}
	public int getMouseEndX() {
		return MouseEndX;
	}
	public void setMouseEndX(int mouseEndX) {
		MouseEndX = mouseEndX;
	}
	public int getMouseEndY() {
		return MouseEndY;
	}
	public void setMouseEndY(int mouseEndY) {
		MouseEndY = mouseEndY;
	}
	public long getThinkTime() {
		return ThinkTime;
	}
	public void setThinkTime(long thinkTime) {
		ThinkTime = thinkTime;
	}
	public Pos[] getCon() {
		return Con;
	}
	public void setCon(Pos[] con) {
		Con = con;
	}
	public int[] getConTask() {
		return ConTask;
	}
	public void setConTask(int[] conTask) {
		ConTask = conTask;
	}
	public int getStep() {
		return Step;
	}
	public void setStep(int step) {
		Step = step;
	}
	
	
	
	
}
