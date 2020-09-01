import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        method("Welcome human!");
    }
    public static void method(String string){
        List<String> slowa = Arrays.asList(string.split(" "));
        char a = 'a';
        int length = string.length();
        for(String slowo : slowa){
            String newString = "";
            for(int i=slowo.length() -1 ; i >= 0; i--){
                a = slowo.charAt(i);
                newString += a;
            }
            System.out.print(newString + " ");
        }


    }
}
