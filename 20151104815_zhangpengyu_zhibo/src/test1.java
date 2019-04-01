import java.io.File;
import java.io.IOException;

public class test1{
    public static void main(String[] args) {
        try {
            java.awt.Desktop.getDesktop().open(new File("D:\\Èí¼þ\\Red5\\red5.bat"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}