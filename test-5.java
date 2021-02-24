//二维数组

public class andian{
	public static void saddle(int m[][], int a, int b) {
	//矩阵求鞍点
		int prow=0, pco1=0;
		for(int i=0; i<a; i++) {
			int temp1=0, temp2=0;
			for(int j=0; j<b-1 ;j++) {
				if(m[i][j+1] < m[i][j]) {//找行最大值
					temp1 = m[i][j];
					prow = i;
					pcol = j;
					}
				}
			for(int k=0; k<a-1; k++) {
				if(m[k+1][pcol] > m[k][pco1]) {
					temp2 = m[k][pco1];
				}
			}
			if(temp1 == temp2) {
				System . out . println("鞍点为:"+"m["+prow+"]["+pcol+"]");
			}
		}
	}
	public static void main(String[] args) {
	// T0DO Auto-generated method stub
		int a[][] = {{1,9,3},{4,6,5},{7,8,6}};
		andian. saddle(a, 3, 3);
		}
	}


//二维字符串

import java.util.Scanner;

public class huiwen {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext("###")) {
        	String data = sc.next();
            if (isPalin(data)) {
            	System.out.println("yes");
            } else {
            	System.out.println("no");
            }
        }
        sc.close();
	}

	public static boolean isPalin(String data) {
		int len = data.length();
		for (int i = 0; i < len/2; i++) {
			if (data.charAt(i) != data.charAt(len-1-i)) {
				return false;
			}
		}
		return true;
	}