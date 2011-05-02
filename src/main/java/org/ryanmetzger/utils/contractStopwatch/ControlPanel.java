/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ryanmetzger.utils.contractStopwatch.contracts.AddDialog;
import org.ryanmetzger.utils.contractStopwatch.contracts.ContractsManager;
import org.ryanmetzger.utils.contractStopwatch.contracts.Timer;

/**
 * @author lirelent
 *
 */
public class ControlPanel extends JPanel
{
    private static final long serialVersionUID = -4618639574546815941L;
    
    private final JLabel lToday;

    public ControlPanel()
    {
        // create overall feedback
        lToday = new JLabel("Total 0.00 hrs");
        Thread updater = new Thread(new Update());
        updater.start();
        
        // display feedback
        add(lToday);
        
        // create controls
        JButton bAdd = new JButton("+");
        JButton bPauseAll = new JButton("|| All");
        JButton bNewDay = new JButton("new day");
        
        // display controls
        add(bAdd);
        add(bPauseAll);
        add(bNewDay);

        // connect controls
        bAdd.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                AddDialog.newDialog();
            }
        });
        bPauseAll.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ContractsManager.getReference().pauseAll();
            }
        });
        bNewDay.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ContractsManager.getReference().clear();
                AddDialog.newDialog();
            }
        });
    }
    
    private class Update implements Runnable
    {

        public void run()
        {
            try
            {
                while (true)
                {
                    long result = ContractsManager.getReference()
                            .getCurrentCumTime();
                    lToday.setText("Total "+ Timer.getFormated(result));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e)
            {
                return;
            }
        }

    }
}
