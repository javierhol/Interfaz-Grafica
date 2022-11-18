import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Estudiante j= new Estudiante();
        j.setVisible(true);
        j.setContentPane(new Estudiante().panel);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.pack();
    }
}