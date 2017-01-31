/**
 * Created by bonar on 1/26/2017.
 */

public class ComplicatedPermutations {

    public ComplicatedPermutations() {
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

    public static String decpypt(int[] mkey, String[] mtable)
    {
        char[][] t = new char[mtable.length][mkey.length];
        String data = new String("");

        for(int i = 0; i < mtable.length; i++)
            data += mtable[i];

        for(int i = 0; i < mtable.length; i++)
            for(int j = 0; j < mkey.length; j++)
                if(mtable[i].charAt(j) == '\0')
                    t[i][j] = '\0';
                else
                    t[i][j] = ' ';

        String result = "";

        for(int i = 0, di = 0; i < mkey.length; i++)
        {
            for(int j = 0; j < mkey.length; j++)
                if(i == mkey[j])
                {
                    for(int k = 0; k < mtable.length; k++)
                    {
                        if(t[k][j] == '\0')
                        {
                            continue;
                        }

                        while(data.charAt(di) == '\0' && di < data.length())
                            di++;
                        if(di >= data.length())
                            return null;

                        t[k][j] = data.charAt(di);
                        di++;

                        /*if(mtable[k].charAt(j) != '\0') {
                            result += mtable[k].charAt(j);
                        }*/
                    }
                    break;
                }
        }

        for(int i = 0; i < t.length; i++)
            for(int j = 0; j < t[i].length; j++)
                result += t[i][j];

        return result;
    }

    public static void main(String[] args)
    {
        int[] mkey = {1, 5, 0, 2, 7, 9, 4, 8, 3, 6};
        String[] mtable = {"для\0 мудро", "\0сти не\0т ", "ничего н\0е",
                            "на\0вистнее", " \0хитрости"};
        String[] mtable2 = {"ятч\0хднн и", "\0евирте\0ту", "е толсиа\0о",
                            " е\0еи  гит", "д\0ннсмноср"};

        System.out.println(encrypt(mkey, mtable));
        System.out.println(decpypt(mkey, mtable2));
    }

}
