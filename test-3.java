//静态方法1

public class Smith {	
	
	public static void main(String[] args) {
		int[] num1 = new int[20];
		for (int i = 1; i < 400; i++) {
			if (isPrime(i))
				continue;
			int k = 0;
			int sum1 = 0;
			int sum2 = 0;
			for (int j = 2; j <= i; ++j) {
				if (i % j == 0) {
					if (isPrime(j)) { 
						int[] tempArr1 = intToNumArray(j);
						for (int m = 0; m < tempArr1.length; m++)
							num1[k++] = tempArr1[m];
					int temp = j * j;
						while (i % temp == 0) {
							for (int m = 0; m < tempArr1.length; m++)
								num1[k++] = tempArr1[m];
							temp *= j;
						}
					}
				}
			}
			for (int p = 0; p < k; p++)
				
				sum1 += num1[p];
 
			int[] tempArr2 = intToNumArray(i);
			for (int p = 0; p < tempArr2.length; p++)
				
				sum2 += tempArr2[p];
 
			if (sum1 == sum2) {
				System.out.print(i+" ");
			}
		}
 
	}
	public static int[] intToNumArray(int n) {
		String str = Integer.toString(n); 
		int[] nums = new int[str.length()];
		for (int i = 0; i < str.length(); i++)
			nums[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
		return nums; 
	}
 
	public static boolean isPrime(int n) { 
		if (n == 2)
			return true;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}