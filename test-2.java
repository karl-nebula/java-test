//流程控制2

import java.util.Scanner;
public class jinzita {

	public static void main(String[] args)
	{
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入一个数：");
		int n =sc.nextInt();
		int x=1;
		for(int i=1;i<=n;i++)
		{
			for(x=1;x<=n-i;x++) {
				System.out.print(" ");
			}
			for(x=1;x<i;x++){
				System.out.print(x+" ");
			}
			for(int y=i;y<=i;--y){
				System.out.print(y+" ");
						if(y==1)
							break;
			}
		System.out.println();
	}
}

}