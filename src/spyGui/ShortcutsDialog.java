package spyGui;


import common.Utilities;

import javax.swing.*;
import java.awt.*;

public class ShortcutsDialog extends JDialog {

    public ShortcutsDialog(JFrame parent) {
        super();

        this.setName("Shortcuts");
        this.setTitle("Keyboard shortcuts");
        this.setForeground(Color.BLACK);
        setIconImage(new ImageIcon(getClass().getResource("spy.png")).getImage());

        setSize(350, 200);
        Container pane = this.getContentPane();
        setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        JLabel label1 = new JLabel("<html>"
                + "<center>Ctrl+Alt+R - Re-Index components</center>" + "<html/>", JLabel.CENTER);
        JLabel label2 = new JLabel("<html>"
                + "<center>Ctrl+Alt+C - Copy highlighted component name</center>" + "<html/>", JLabel.CENTER);
        JLabel label3 = new JLabel("<html>"
                + "<center>Ctrl+Alt+S - Start/Stop component inspection</center>" + "<html/>", JLabel.CENTER);


        pane.add(label1);
        pane.add(label2);
        pane.add(label3);

        Utilities.centerContainerComponents(pane);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(parent);
        setVisible(true);

        this.validate();
    }
}
