package spyGui;


import common.Utilities;

import javax.swing.*;
import java.awt.*;

public class ShortcutsDialog extends JDialog {

    public ShortcutsDialog(JFrame parent) {

        this.setName("Shortcuts");
        this.setTitle("Keyboard shortcuts");
        this.setForeground(Color.BLACK);
        setIconImage(new ImageIcon(getClass().getResource("spy.png")).getImage());

        Container pane = this.getContentPane();
        setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

        InfoTable table = new InfoTable();
        pane.add(table);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        pack();
    }
}

class InfoTable extends JLabel {

    public InfoTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><table border=2 cellpadding=\"7\">");

        for (Object[] row : data) {
            sb.append("<tr>");
            for (Object value : row) {
                sb.append("<td>");
                sb.append(value);
                sb.append("</td>");

            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        setText(sb.toString());
    }

    Object[][] data = {
            {"Ctrl+Alt+R", "Re-Index components"},
            {"Ctrl+Alt+S", "Start/Stop component inspection"},
            {"Ctrl+Alt+C", "Copy highlighted component name"}
    };
}
