import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import static java.awt.Dialog.ModalityType.APPLICATION_MODAL;

/**
 * LoginDialog is an extension of Dialog that checks for username and password before allowing the user to log in.
 *
 * Created by Lok Man Chu
 */
public class LoginDialog extends JDialog {

    private JLabel usernameLabel = new JLabel("Username: ");
    private JLabel passLabel = new JLabel("Password: ");
    private JTextField username = new JTextField(15);
    private JPasswordField password = new JPasswordField(15);
    private JButton loginButton = new JButton("Login");
    private JLabel errorText = new JLabel("");
    private Component errorTextFiller;
    private Boolean loggedIn = false;
    private Boolean debug;


    /**
     * Private constructor for LoginDialog. Use static LoginDialog.show to create.
     * @param parent JFrame of program
     */
    private LoginDialog(JFrame parent,Boolean debug)
    {
        super(SwingUtilities.getWindowAncestor(parent));
        this.debug = debug;
        createGUI();
    }

    /**
     * Creates LoginDialog
     * @param parent JFrame of program
     */
    public static void show(JFrame parent)
    {
        new LoginDialog(parent,false);
    }

    /**
     * Debug Dialog. Automatically fills in username and password for testing
     * @param parent
     * @param debug
     */
    public static void show(JFrame parent, Boolean debug)
    {
        new LoginDialog(parent,debug);
    }

    /**
     * Checks if the user has imputed the correct information before allowing access
     * to the TaskBoard. If incorrect information is entered, the user will be notified.
     */
    private void verifyLoginInfo()
    {
        if (username.getText().equals("admin") && Arrays.toString(password.getPassword()).equals("[a, d, m, i, n]"))
        {
            this.dispose();
            loggedIn = true;
        }
        else
        {
            errorTextFiller.setVisible(false);
            errorText.setForeground(Color.RED);
            if (!"admin".equals(username.getText()))
            {
                errorText.setText("Incorrect username");
            }
            else if (!"[a, d, m, i, n]".equals(password.getPassword()))
            {
                errorText.setText("Incorrect password");
            }
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Creates the GUI for LoginDialog
     *
     * Jobs covered:
     * - Sets Dialog settings for modality and resizing
     * - Adds all elements to Dialog
     * - Assigns ActionListeners to all buttons
     */
    private void createGUI()
    {
        setTitle("Login");
        this.setResizable(false);
        this.setModal(true);
        this.setModalityType(APPLICATION_MODAL);

        errorText.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorTextFiller = Box.createRigidArea(new Dimension(0,16));

        JPanel parent = new JPanel();
        parent.setLayout(new BoxLayout(parent,BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel();
        p1.add(usernameLabel);
        p1.add(username);
        JPanel p2 = new JPanel();
        p2.add(passLabel);
        p2.add(password);
        JPanel pButton = new JPanel();
        pButton.add(loginButton);
        parent.add(p1);
        parent.add(p2);
        parent.add(errorTextFiller);
        parent.add(errorText);
        parent.add(pButton);

        loginButton.addActionListener(e->{
            verifyLoginInfo();
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!loggedIn)
                {
                    System.exit(0);
                }
            }
        });
        loginButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyCode())
                {
                    verifyLoginInfo();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.add(parent);

        if (debug){
            username.setText("admin");
            password.setText("admin");
        }

        pack();
        this.setLocationRelativeTo(null);
        //this.setSize(new Dimension(280,140));
        setVisible(true);
    }
}
