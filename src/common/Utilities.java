package common;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Utilities {
    public static void centerContainerComponents(Container c) {
        for (Component component : c.getComponents()) {
            JComponent jComponent = (JComponent) component;
            jComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    public static void copyStringToClipboard(String str) {
        StringSelection selection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    public static ArrayList<String> getCommandHistory() {
        ArrayList<String> commands = new ArrayList<>();

        if (!Files.exists(Paths.get(Constants.COMMAND_HISTORY_FILENAME))) {
            return commands;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.COMMAND_HISTORY_FILENAME))) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                commands.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands;
    }

    public static void writeCommandHistory(Object[] cmd) {
        try (PrintStream out = new PrintStream(Constants.COMMAND_HISTORY_FILENAME)) {
            for (Object com : cmd) {
                out.println(com);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
