package leavemanagementsystem;
import java.awt.EventQueue;

public class Application {
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    GUI.menuPage.setVisible(true);
                } catch (Exception e) {

                }
            }
        });
    }
}