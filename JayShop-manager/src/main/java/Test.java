/**
 * Created by Administrator on 2016/10/26.
 */
public class Test {
    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2.intern() == s3);
    }
}
