package org.example.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panel;

    public LoginForm() {
        setTitle("Inicio de Sesión");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (verifyCredentials(username, password)) {
                    dispose();
                    openMainApp();
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean verifyCredentials(String username, String password) {
        return username.equals("postgres") && password.equals("mysecretpassword");
    }
    private void openMainApp() {
        SwingUtilities.invokeLater(() -> new InscripcionApp());
    }
}
