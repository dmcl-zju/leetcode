package dynamicProgramming;

/**
 *	 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

	示例:
		输入: [-2,1,-3,4,-1,2,1,-5,4],
		输出: 6
		解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * @author lin
 *
 */
public class MaxSubArray {
	
	
	//分治法解决
	public static int getMaxSubArray1(int[] arr) {
		if(null==arr || arr.length<1) {
			return 0;
		}
		return process(arr,0,arr.length-1);
		
	}
	
	public static int process(int[] arr,int left,int right) {
		//System.out.println("计算中。。。。。");
		//只剩下一个元素了
		if(left==right) {
			return arr[left];
		}
		
		int mid = (left+right)/2;
		int l = process(arr,0,mid);
		int r = process(arr,mid+1,right);
		int m = getMergeMax(arr,left,mid,right);
		//返回三者的最大值
		return Math.max(l, Math.max(r, m));
		
	}
	
	public static int  getMergeMax(int[] arr,int left,int mid,int right) {
		int leftMax = Integer.MIN_VALUE;
		int sum = 0;
		for(int i=mid;i>=0;i--) {
			sum += arr[i];
			if(sum>leftMax) {
				leftMax = sum;
			}
		}
		sum = 0;
		int rightMax = Integer.MIN_VALUE;
		for(int i=mid+1;i<=right;i++) {
			sum += arr[i];
			if(sum>rightMax) {
				rightMax = sum;
			}
		}
		return leftMax+rightMax;
	}
	

	//暴力递归方法
	public static int getMaxSubArray2(int[] nums) {
		if(null==nums || nums.length<1) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		for(int i=0;i<nums.length;i++) {
			max = Math.max(max, getMaxend(nums,i));
		}
		return max;
	}
	//获取以下标n结尾的所有子串最大和
	public static int getMaxend(int[] arr,int n) {
		if(n==0) {
			//System.out.println(n+"----"+arr[n]);
			return arr[n];
		}
		
		int res =Math.max(arr[n],arr[n]+getMaxend(arr,n-1)) ;
		//System.out.println(n+"----"+res);
		return res;
	}
	
	//暴力递归改到动态规划方法
	public static int getMaxSubArray3(int[] nums) {
        int[] dp = new int[nums.length];
       //先填第一个格子
        dp[0] = nums[0];
        int max=dp[0];
        for (int i=1;i<nums.length;i++) {
            //每个普通格子的依赖关系--但是这里要被调用
            dp[i] = Math.max(nums[i],dp[i-1]+nums[i]);
            //System.out.println("第"+i+"层最大值："+dp[i]);
            max = Math.max(dp[i],max);
        }
        return max;
    }
		
	//进一步优化方法---高频算法第二章
	public static int getMaxSubArray4(int[] nums) {
		if(null==nums || nums.length<1) {
			return 0;
		}
		int cur = 0;
		int max = nums[0];
		for(int i=0;i<nums.length;i++) {
			cur += nums[i];
			if(cur>max) {
				max = cur;
			}
			if(cur<0) {
				cur=0;
			}
		}
		return max;
	}

	
	
	
	
	public static void main(String[] args) {
		int[] arr= {-84,-87,-78,-16,-94,-36,-87,-93,-50,-22,-63,-28,-91,-60,-64,-27,
				-41,-27,-73,-37,-12,-69,-68,-30,-83,-31,-63,-24,-68,-36,-30,-3,-23,
				-59,-70,-68,-94,-57,-12,-43,-30,-74,-22,-20,-85,-38,-99,-25,-16,-71,
				-14,-27,-92,-81,-57,-74,-63,-71,-97,-82,-6,-26,-85,-28,-37,-6,-47,
				-30,-14,-58,-25,-96,-83,-46,-15,-68,-35,-65,-44,-51,-88,-9,-77,-79,
				-89,-85,-4,-52,-55,-100,-33,-61,-77,-69,-40,-13,-27,-87,-95,-40};
		
		int[] arr2 = {3,-20,2,2};
		//getMaxend(arr2, arr2.length-1);
		//printSubArray(arr);
		//System.out.println(getMaxSubArray(arr));
		System.out.println(getMaxSubArray3(arr));
		//System.out.println(getMaxSubArray2(arr));
		//System.out.println(getMaxSubArray1(arr));
	}
}
