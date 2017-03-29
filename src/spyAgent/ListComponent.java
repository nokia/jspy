/*
 * Copyright 2015 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see licence.txt file for details.
 */

package spyAgent;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JInternalFrame;


public class ListComponent {
    private java.util.List resultComponentList;
    Component srcComp;
    HierarchyMap compHieMap;

    private static abstract class ContainerIterator {

        public void iterate() {
            processComponent(container);
        }

        public abstract void operateOnComponent(Component component, int i);

        private void processComponent(Component component) {

            operateOnComponent(component, level);
            level++;
            if (component instanceof Container || component instanceof JInternalFrame) {
                Component subComponents[] = ((Container) component).getComponents();
                for (int i = 0; i < subComponents.length; i++) {
                    processComponent(subComponents[i]);
                    level--;
                }

            }
        }

        private int level;
        private Container container;

        public ContainerIterator(Container container) {
            this.container = container;
        }
    }

    private class ContainerIteratorForListing extends ContainerIterator {

        String componentClass = "";
        String componentName = "";
        HierarchyMap myHieMap;

        public ContainerIteratorForListing(Container container, HierarchyMap compHieMap) {
            super(container);
            myHieMap = compHieMap;
        }

        public void operateOnComponent(Component component, int level) {
            componentName = componentToString(component);
            componentClass = myHieMap.getInstance(component);
            CompMouseListner mouseListner = new CompMouseListner(myHieMap.index, level, myHieMap.winTitle, componentClass, myHieMap.props);

            component.addMouseListener(mouseListner);

        }

    }

    public ListComponent(HierarchyMap compHieMap) {
        resultComponentList = new ArrayList();
        this.compHieMap = compHieMap;
    }

    public String listComponentsInContext(Component cmp) {
        this.srcComp = cmp;
        (new ContainerIteratorForListing((Container) cmp, compHieMap)).iterate();
        return resultComponentList.toString();
    }

    private String componentToString(Component component) {
        String componentString = component.toString();
        int indexToStartOfDetails = componentString.indexOf('[');
        if (indexToStartOfDetails == -1)
            return componentString;
        else
            return componentString.substring(0, indexToStartOfDetails);
    }


}
