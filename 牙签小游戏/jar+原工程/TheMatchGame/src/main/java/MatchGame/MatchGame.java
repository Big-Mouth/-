package main.java.MatchGame;

public class MatchGame {
	//�浱ǰ��Ϸ��ͼ��0 1 ������ǩ���ڻ����Ѿ�������
	private int [][] GameMap = new int[8][8];
	//��ά����
	//��һά����1����������ǩ��
	//�ڶ�ά����2����������ǩ�� �Դ����� ��ʼ״̬��dp[1][1][1][1][1]
	//���Լ���� ��Ϸ���״̬������2000
	private int [][][][][] dp = new int [30][20][10][10][5];
	//����Ƿ�Ӯ
	private boolean isWin;
	//��� �� 
	private int matchHight = 50;
	private int matchWight = 10;
	//��ɫͷ �� ��
	private int matchRedHight = 10;
	private int matchRedWight = 10;
	//����
	private int matchWightBlank = 80;
	
	
	public int getMatchWightBlank() {
		return matchWightBlank;
	}
	public void setMatchWightBlank(int matchWightBlank) {
		this.matchWightBlank = matchWightBlank;
	}
	public MatchGame() {
		init();
	}
	public void init() {
		//��ʼ��һ��GameMap
		//�ʼ������ǩ
		int i,j,k,l,m;
		for(i = 1; i <= 3 ; i++) {
			for(j = 1 ; j <= 2*i+1 ; j++) {
				GameMap[i][j] = 1;
			}
		}
		//��ʼ�� dp
		//�ʼ����-1  
		for(i = 0; i <= 29 ; i++)
			for(j = 0; j <= 19 ; j++)
				for(k = 0; k <= 9 ; k++)
					for(l = 0; l <= 9 ; l++)
						for(m = 0; m <= 4 ; m++)
							dp[i][j][k][l][m] = -1;
		
		//System.out.println(dp[1][1][1][1][1] + " 1 1 1 1 1Ϊ ");
		dfs(1,1,1,1,1);
		//System.out.println( dp[1][0][1][1][1] +" gg");;
	}
	//getter and setter
	public int[][] getGameMap() {
		return GameMap;
	}
	public void setGameMap(int[][] gameMap) {
		GameMap = gameMap;
	}
	public int getMatchHight() {
		return matchHight;
	}
	public void setMatchHight(int matchHight) {
		this.matchHight = matchHight;
	}
	public int getMatchWight() {
		return matchWight;
	}
	public void setMatchWight(int matchWight) {
		this.matchWight = matchWight;
	}

	public int getMatchRedHight() {
		return matchRedHight;
	}

	public void setMatchRedHight(int matchRedHight) {
		this.matchRedHight = matchRedHight;
	}
	public int getMatchRedWight() {
		return matchRedWight;
	}

	public void setMatchRedWight(int matchRedWight) {
		this.matchRedWight = matchRedWight;
	}
	
	/****************************�㷨����**************************/
	//����dp
	private int dfs(int a1,int a2,int a3,int a4,int a5) {
		    Integer  x = dp[a1][a2][a3][a4][a5];
		    if(x != -1)
		    {
		    	 return x;
		    }
		    if(a1 + a2 + a3 + a4 + a5  == 0)
		        return 1;
		    boolean flag = false;
		    if(a1 != 0)
		    {
		    	if(dfs(a1-1,a2,a3,a4,a5) == 0)
		    		flag = true;
		        if(flag)
		            return x = 1;
		    }
		    if(a2 != 0)
		    {
		    	if(dfs(a1,a2-1,a3,a4,a5) == 0)
		    		flag = true;
		        if(flag)
		            return x = 2;
		        if(dfs(a1+1,a2-1,a3,a4,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 3;
		    }
		    if(a3 != 0)
		    {
		    	if(dfs(a1,a2,a3-1,a4,a5) == 0)
		    		flag = true;
		        if(flag)
		            return x = 4;
		        if(dfs(a1,a2+1,a3 - 1,a4,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 5;
		        if(dfs(a1+1,a2,a3 - 1,a4,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 6;
		        if(dfs(a1+2,a2,a3 - 1,a4,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 7;
		    }
		    if(a4 != 0)
		    {
		    	if(dfs(a1,a2,a3,a4-1,a5) == 0)
		    		flag = true;
		        if(flag)
		            return x = 8;
		        if(dfs(a1,a2,a3+1,a4-1,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 9;
		        if(dfs(a1+1,a2+1,a3,a4-1,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 10;
		        if(dfs(a1+2,a2,a3,a4-1,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 11;
		        if(dfs(a1+1,a2,a3,a4-1,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 12;
		        if(dfs(a1,a2+1,a3,a4-1,a5) == 0)
		        	flag = true;
		        if(flag)
		            return x = 13;
		    }
		    if(a5 != 0)
		    {
		    	if(dfs(a1,a2,a3,a4,a5-1) == 0)
		    		flag = true;
		        if(flag)
		            return x = 14;
		        if(dfs(a1,a2,a3,a4+1,a5-1) == 0)
		        	flag = true;
		        if(flag)
		            return x = 15;
		        if(dfs(a1,a2,a3+1,a4,a5-1) == 0)
		        	flag = true;
		        if(flag)
		            return x = 16;
		        if(dfs(a1,a2+1,a3,a4,a5-1) == 0)
		        	flag = true;
		        if(flag)
		            return x = 17;
		        if(dfs(a1+1,a2,a3,a4,a5-1) == 0)
		        	flag = true;
		        if(flag)
		            return x = 18;
		        if(dfs(a1+1,a2,a3+1,a4,a5-1) == 0)
		        flag = true;
		        if(flag)
		            return x = 19;
		        if(dfs(a1,a2+2,a3,a4,a5-1) == 0)
		        	flag = true;
		        if(flag)
		            return x = 20;
		        if(dfs(a1+2,a2,a3,a4,a5-1) == 0)
		        	flag = true;
		        if(flag)
		            return x = 21;

		        if(dfs(a1+1,a2+1,a3,a4,a5-1) == 0)
		        	flag = true;
		        if(flag)
		            return x = 22;
		    }
		    return x = 0;
		}
	/**�õ�������**/
	public int getDp(int [] a) {
		return dfs(a[1],a[2],a[3],a[4],a[5]);
	}
	/***���� �õ����巽��***/
	public int [] GetConTask(int step) {
		
		int [] ConTask = new int [4];
		if(step == 1) {
			ConTask[1] = 1; // ���
			ConTask[2] = 1; // ��ʼλ��
			ConTask[3] = 1; // ����
		}
		else if(step == 2) {
			ConTask[1] = 2;
			ConTask[2] = 1;
			ConTask[3] = 2;
		}
		else if(step == 3) {
			ConTask[1] = 2;
			ConTask[2] = 1;
			ConTask[3] = 1;
		}
		else if(step == 4) {
			ConTask[1] = 3;
			ConTask[2] = 1;
			ConTask[3] = 3;
		}
		else if(step == 5) {
			ConTask[1] = 3;
			ConTask[2] = 1;
			ConTask[3] = 1;
		}
		else if(step == 6) {
			ConTask[1] = 3;
			ConTask[2] = 1;
			ConTask[3] = 2;
		}
		else if(step == 7) {
			ConTask[1] = 3;
			ConTask[2] = 2;
			ConTask[3] = 1;
		}
		else if(step == 8) {
			ConTask[1] = 4;
			ConTask[2] = 1;
			ConTask[3] = 4;
		}
		else if(step == 9) {
			ConTask[1] = 4;
			ConTask[2] = 1;
			ConTask[3] = 1;
		}
		else if(step == 10) {
			ConTask[1] = 4;
			ConTask[2] = 2;
			ConTask[3] = 1;
		}
		else if(step == 11) {
			ConTask[1] = 4;
			ConTask[2] = 2;
			ConTask[3] = 2;
		}
		else if(step == 12) {
			ConTask[1] = 4;
			ConTask[2] = 1;
			ConTask[3] = 3;
		}
		else if(step == 13) {
			ConTask[1] = 4;
			ConTask[2] = 1;
			ConTask[3] = 2;
		}
		else if(step == 14) {
			ConTask[1] = 5;
			ConTask[2] = 1;
			ConTask[3] = 5;
		}
		else if(step == 15){
			ConTask[1] = 5;
			ConTask[2] = 1;
			ConTask[3] = 1;
		}
		else if(step == 16) {
			ConTask[1] = 5;
			ConTask[2] = 1;
			ConTask[3] = 2;
		}
		else if(step == 17) {
			ConTask[1] = 5;
			ConTask[2] = 1;
			ConTask[3] = 3;

		}
		else if(step == 18) {
			ConTask[1] = 5;
			ConTask[2] = 1;
			ConTask[3] = 4;
		}
		else if(step == 19) {
			ConTask[1] = 5;
			ConTask[2] = 2;
			ConTask[3] = 1;
		}
		else if(step == 20) {
			ConTask[1] = 5;
			ConTask[2] = 3;
			ConTask[3] = 1;
		}
		else if(step == 21) {
			ConTask[1] = 5;
			ConTask[2] = 2;
			ConTask[3] = 3;
		}
		else if(step == 22) {
			ConTask[1] = 5;
			ConTask[2] = 2;
			ConTask[3] = 2;
		}	
		else {
			//�������������ⲿ����
		}
		return ConTask;
	}
}
