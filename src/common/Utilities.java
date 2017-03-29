package common;

import javax.swing.*;
import java.awt.*;

public class Utilities {
    public static void centerContainerComponents(Container c) {
        for (Component component : c.getComponents()) {
            JComponent jComponent = (JComponent) component;
            jComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }
}
