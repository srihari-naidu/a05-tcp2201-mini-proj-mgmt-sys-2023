import javax.swing.*;

import Controller.LoginController;
import View.LoginView;

public class App extends JFrame {
    public App() {}

    public static void main(String[] args) {
        new LoginController(new LoginView());
    }
}