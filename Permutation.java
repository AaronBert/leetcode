/**
 *剑指 Offer 38. 字符串的排列
 *https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 **/
import java.util.*;


public class Permutation{
  
  /**
   *input  example:"12345"
   *output example:
   *12345	12354	12435	12453	12534	12543	13254	13425	13452	13524	13542	14253	14325	14352	14523	14532	15243	15324	15342	15423	15432	
   *21543	23154	23415	23451	23514	23541	24153	24315	24351	24513	24531	25143	25314	25341	25413	25431	
   *31542	32154	32415	32451	32514	32541	34152	34215	34251	34512	34521	35142	35214	35241	35412	35421	
   *41532	42153	42315	42351	42513	42531	43152	43215	43251	43512	43521	45132	45213	45231	45312	45321	
   *51432	52143	52314	52341	52413	52431	53142	53214	53241	53412	53421	54132	54213	54231	54312	54321	
   *[Finished in 427ms]
   **/
	public static void main(String[] args) {
		Permutation permutation = new Permutation();
		String[] ans = permutation.permutation("12345");
		for(String str : ans){
			System.out.print(str + '\t');
		}
		System.out.println();
	}


	public Permutation(){
		
	}

	/**
	 * 对字符串进行排列组合
	 * 可推广到对数字的排列组合
	 * 输入：s = "abc"
	 * 输出：["abc","acb","bac","bca","cab","cba"] 
	 * 1 <= s 的长度 <= 8
	 **/
	public String[] permutation(String s) {

		//可变数组的形式声明
        List<String> ret = new ArrayList<String>();

        //target string transform to char array
        char[] arr = s.toCharArray();

        // order by dictionary
        Arrays.sort(arr);


        //调用获取下一个字符串，并保存到ret中
        do{
        	ret.add(new String(arr));
        }while(nextPermutation(arr));

        //将List类型转化为数组
        int size = ret.size();
        String[] retArr = new String[size];
        for(int i = 0; i < size; i++){
        	retArr[i] = ret.get(i);
        }


        return retArr;
    }

    /**
     * 下一个排列组合字符串
     * 得到当前字符串的第一个排列，然后我们不断计算当前
     * 字符串的字典序中下一个更大的排列，知道不存在更大的排列为止即可。
     * */
    public boolean nextPermutation(char[] arr){

    	int i = arr.length - 2;

    	//从后向前查找第一个顺序对(i, i+1)，满足a[i] < a[i+1]，从而确定a[i]
      //此时[i+1, n)必然是下降序列
    	while(i >= 0 && arr[i] >= arr[i+1] ){
    		i --;
    	}

    	//如果在查找a[i]的过程中走到了起始位置[0]，那么返回错误
    	if(i < 0){
    		return false;
    	}

    	int j = arr.length - 1;

    	//从后向前寻找一个比a[i]大的数a[j]
    	while(j >= 0 && arr[i] >= arr[j]){
    		j --;
    	}

    	//交换a[i] 与 a[j]
    	swap(arr, i, j);
      
      //此时[i+1, n)必为降序，因此对其反转使其成为升序
    	//反转 位置i 之后的字符串
    	reverse(arr, i + 1);

    	return true;
    }

    /**
     * 俩个字符串之间进行交换
     * 
     * Before
     * [0, 1, ..., i, ..., j, ..., length-1]
     * 
     * After
     * [0, 1, ..., j, ..., i, ..., length-1]
     **/
    private void swap(char[] arr, int i, int j){
    	char temp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = temp;
    }



    /**
     * 将start(left)到right之间的字符串反转
     * 
     * Before
     * [ 0, 1, ..., start(left), 'f', 'g', ..., 't', 'q', middle, 'y', 'u', ..., 'a', 'b', right(length - 1) ]
     * 
     * After 
     * [ 0, 1, ..., right(length - 1), 'b', 'a', ..., 'u', 'y', middle, 'q', 't', ..., 'g', 'f', start(left) ]
     **/
    private void reverse(char[] arr, int start){
    	int left = start, right = arr.length - 1;
    	while(left < right){
    		swap(arr, left, right);
    		left ++;
    	}
    }
}

