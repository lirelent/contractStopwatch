/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch.contracts;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

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
    
    private final Set<Contract> contracts =
                            new HashSet<Contract>();
    private final Set<Contract> activeContracts =
                            new HashSet<Contract>();
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
    
    /**
     * add contract into manager
     * @param contract
     */
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
    
    /**
     * stop work on all active contracts and start work on
     * contract
     * @param contract
     */
    public void switchContract(Contract contract)
    {
        synchronized (guard)
        {
            for (Contract activeContract : activeContracts)
            {
                activeContract.stop();
            }
            activeContracts.clear();
            activeContracts.add(contract);
        }
    }
    
    /**
     * add contract to what is currently being worked
     * @param contract
     */
    public void multitaskContract(Contract contract)
    {
        synchronized (guard)
        {
            activeContracts.add(contract);
            for (Contract activeContract : activeContracts)
            {
                activeContract.setConcurrency(activeContracts.size());
            }
        }
    }
    
    public void stopContract(Contract contract)
    {
        synchronized (guard)
        {
            activeContracts.remove(contract);
            for (Contract activeContract : activeContracts)
            {
                activeContract.setConcurrency(activeContracts.size());
            }
        }
    }
    
    public void pauseAll()
    {
        synchronized (guard)
        {
            for (Contract activeContract : activeContracts)
            {
                activeContract.stop();
            }
            activeContracts.clear();
        }
    }
    
    public void clear()
    {
        synchronized (guard)
        {
            for (Contract activeContract : activeContracts)
            {
                activeContract.stop();
            }
            activeContracts.clear();
            contracts.clear();
            removeAll();
            revalidate();
            repaint();
        }
    }
    
    public long getCurrentCumTime()
    {
        long output = 0l;
        synchronized (guard)
        {
            for (Contract activeContract : activeContracts)
            {
                output += activeContract.getTodayTime();
            }
        }
        return output;
    }
}
