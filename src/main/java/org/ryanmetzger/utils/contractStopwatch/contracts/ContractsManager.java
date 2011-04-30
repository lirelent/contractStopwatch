/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch.contracts;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author lirelent
 *
 */
public class ContractsManager extends JPanel
{
    private static final long serialVersionUID = 5440899633425811703L;
    
    private static ContractsManager reference = null;
    private static final int ITEM_HEIGHT = 50;
    
    private final List<Contract> contracts =
                            new ArrayList<Contract>();
    private final Object guard = new Object();
    private int height;
    
    private ContractsManager()
    {
        setLayout(new GridLayout(0, 1, 1, 1));
        height = ITEM_HEIGHT;
        setPreferredSize(new Dimension(0, height));
    }
    
    public static ContractsManager getReference()
    {
        if( reference == null )
        {
            reference = new ContractsManager();
        }
        return reference;
    }
    
    public void addContract(Contract contract)
    {
        synchronized (guard)
        {
            // add contract
            add(contract);
            contracts.add(contract);
            
            // resize
            height += ITEM_HEIGHT;
            setPreferredSize(new Dimension(0, height));
            
            // repaint
            revalidate();
            repaint();
        }
    }
}
