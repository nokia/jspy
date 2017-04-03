package spyGui;


import javax.swing.*;
import java.awt.*;

public class ComboToolTipRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (-1 < index && null != value) {
            if (list.getSelectedValue() != null) {
                list.setToolTipText(list.getSelectedValue().toString());
            }
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}