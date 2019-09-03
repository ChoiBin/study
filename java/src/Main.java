public class Main {
    public static void main(String[] args) {

        String s1 = "s1";
        String s2 = "s2";
        String s3 = new String("s3");
        String s4 = s3.intern();
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        if(s3 == s4){
            System.out.println("yes");
        }
        System.out.println("test");

    }
}
