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
    public static final String __PARANAMER_DATA = "\nlistComponentsInContext \n";
    private java.util.List resultComponentList;
	Component srcComp;
	HierarchyMap compHieMap;

	private static abstract class ContainerIterator
    {

        public void iterate()
        {
			processComponent(container);
        }

        public abstract void operateOnComponent(Component component, int i);

        private void processComponent(Component component)
        {
			
            operateOnComponent(component, level);
            level++;
            if(component instanceof Container ||component instanceof JInternalFrame)
            {
                Component subComponents[] = ((Container)component).getComponents();
                for(int i = 0; i < subComponents.length; i++)
                {
                    processComponent(subComponents[i]);
                    level--;
                }

            }
        }

        public static final String __PARANAMER_DATA = "\n<init> java.awt.Container container \n\niterate \n\noperateOnComponent java.awt.Component,int component,level \n";
        private int level;
        private Container container;

        public ContainerIterator(Container container)
        {
            this.container = container;
        }
    }
	private class ContainerIteratorForListing extends ContainerIterator
    {		

		String componentClass="";
		String componentName="";
		HierarchyMap myHieMap;
		
        public ContainerIteratorForListing(Container container,HierarchyMap compHieMap) {
			super(container);
			// TODO Auto-generated constructor stub

			myHieMap= compHieMap;
		}

		public void operateOnComponent(Component component, int level)
        {
			//Comminicator.writeToServer(component.toString());
            componentName = componentToString(component);
			componentClass=myHieMap.getInstance(component);			
			CompMouseListner mouseListner=new CompMouseListner(myHieMap.index,level,myHieMap.winTitle,componentClass, myHieMap.props);
			
			component.addMouseListener(mouseListner);

        }


		
		
    }
	
	 	public ListComponent(HierarchyMap compHieMap)
	    {
	        resultComponentList = new ArrayList();
			this.compHieMap=compHieMap;
	    }

	    public String listComponentsInContext(Component cmp)
	    {
			this.srcComp=cmp;
	        (new ContainerIteratorForListing((Container)cmp,compHieMap)).iterate();
	        return resultComponentList.toString();
	    }

	    private void printSpacesToFormatOutputAsTree(int level)
	    {
	        for(int i = 0; i < level; i++)
	            System.out.print("\t");

	    }

	    private String componentToString(Component component)
	    {
	        String componentString = component.toString();
	        int indexToStartOfDetails = componentString.indexOf('[');
	        if(indexToStartOfDetails == -1)
	            return componentString;
	        else
	            return componentString.substring(0, indexToStartOfDetails);
	    }

	
}
