//流程控制语句

import java.util.Scanner;

public class dengyaosanjiaoxing 
{
	public static int[] printYangHui(int col)
	{
		int space=(col+1)*col/2;
		int[] a=new int[space+1];
		a[0]=space;
		a[1]=1;
		a[2]=1;
		a[3]=1;
		int count=2;
		for(int i=3;i<=col;i++)
		{
			count++;
			int flag=i*(i-1)/2;
			for(int j=1;j<=count;j++)
			{
				if(j==1||j==count)
				{
					a[flag+j]=1;
				}
				else
				{
					a[flag+j]=a[(i-2)*(i-1)/2+(j-1)] + a[(i-2)*(i-1)/2+j];
				}
			}
		}
		return a;
	}
	
	
	
	public static void main(String[] args)
	{
		Scanner reader=new Scanner(System.in);
		int col=reader.nextInt();
		int[] a=printYangHui(col);
		
		int f=1;
 
		int count=0;
		int cp=col;
		for(int i=1;i<=col;i++)
		{
			for(int k=1;k<=cp;k++)
			{
				System.out.print(" ");
			}
			cp--;
			count++;
			int flag=(i)*(i-1)/2;
			for(int j=1;j<=count;j++)
			{
				System.out.print(" "+a[flag+j]);
			}
			System.out.println();
		}
			