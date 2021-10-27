import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class demo {
    //从txt中读取文本
    public static String readFromTxt(String str) {
        String result = "";//保存最后字符串结果
        try {
            BufferedReader br = new BufferedReader(new FileReader(str));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                s = s.replace("\r", "");
                s = s.replace("\t", "");
                s = s.replace("\n", "");
                s = s.replace("(", "");
                s = s.replace(")", "");
                s = s.replace("{", "");
                s = s.replace("}", "");
                s = s.replace(" ", "");//清除所有空格制表符回车便于比较相似度
                result = result + s;
            }
            br.close();//关闭缓冲流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        //获取文件中字符串
        String str1 = readFromTxt("CopyOfBubbleSort.txt");
        String str2 = readFromTxt("BubbleSort.txt");

        int lenOfStr1, lenOfStr2;
        lenOfStr1 = str1.length();
        lenOfStr2 = str2.length();
        String minStr = (lenOfStr1 >= lenOfStr2) ? str2 : str1;
        String maxStr = (lenOfStr1 <= lenOfStr2) ? str2 : str1;
        System.out.println(minStr);
        System.out.println(maxStr);

        if (str1.equals(str2)) {
            System.out.println("\n相似度为：1");
        } else {
            ArrayList lList = new ArrayList();
            int maxLength = 1;//保存最大相同子序列长度
            for (int i = 0; i < minStr.length() - maxLength * 2; i++) {
                String subStr = minStr.substring(i, i + maxLength);

                if (maxStr.contains(subStr)) {
                    String str = null;
                    int len = maxLength;
                    do {
                        len++;
                        str = minStr.substring(i, i + len);
                    } while (maxStr.contains(str));
                    if (len-- >= maxLength) {
                        lList.add(str.substring(0, str.length() - 1));
                        maxLength = len;
                    }
                }
            }
            System.out.println("MaxLength: " + maxLength + "\n");

            for (Object s : lList) {
                if (s.toString().length() == maxLength) {
                    System.out.print("最大相同子串是： " + s);
                    System.out.println("\n相似度为：" + String.format("%.3f", maxLength * 1.0 / maxStr.length()));
                }

            }

        }
    }

}

