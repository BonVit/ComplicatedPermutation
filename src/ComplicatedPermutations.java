/**
 * Created by bonar on 1/26/2017.
 */

public class ComplicatedPermutations {

    private int[] mkey;
    private String mtable;

    private String mlastResuslt;

    public ComplicatedPermutations(int[] mkey, String mtable){
        this.mkey = mkey;
        this.mtable = mtable;
    }

    public static String encrypt(int[] mkey, String[] mtable)
    {
        String result = "";
        for(int i = 0; i < mkey.length; i++)
        {
            for(int j = 0; j < mkey.length; j++)
                if(i == mkey[j])
                {
                    for(int k = 0; k < mtable.length; k++)
                    {
                        if(mtable[k].charAt(j) != '\0')
                            result += mtable[k].charAt(j);
                    }
                    break;
                }
        }

        return result;
    }

    public static void main(String[] args)
    {
        int[] mkey = {1, 5, 0, 2, 7, 9, 4, 8, 3, 6};
        String[] mtable = {"для\0 мудро", "\0сти не\0т ", "ничего н\0е",
                            "на\0вистнее", " \0хитрости"};
        System.out.println(encrypt(mkey, mtable));
    }

}
