package spyGui;

import common.Utilities;

import javax.swing.*;
import java.util.ArrayList;

public class CommandComboBox extends JComboBox {
    private ArrayList<String> commands;

    public CommandComboBox() {
        setRenderer(new ComboToolTipRenderer());
        commands = Utilities.getCommandHistory();
        setItemsInCombo(commands);
    }

    public void addCommand(String cmd) {
        int cmdIndex = commands.indexOf(cmd);
        if (cmdIndex != -1) {
            commands.remove(cmdIndex);
        }
        commands.add(0, cmd);
        setItemsInCombo(commands);
        Utilities.writeCommandHistory(commands.toArray());
    }

    private void setItemsInCombo(ArrayList<String> cmds) {
        removeAllItems();
        addItem("");
        setSelectedIndex(0);
        for (String cmd : cmds) {
            addItem(cmd);
        }
    }
}
