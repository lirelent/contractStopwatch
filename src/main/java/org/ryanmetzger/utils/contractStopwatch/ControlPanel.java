/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.ryanmetzger.utils.contractStopwatch.contracts.AddDialog;

/**
 * @author lirelent
 *
 */
public class ControlPanel extends JPanel
{
    private static final long serialVersionUID = -4618639574546815941L;
    
    private final JButton bAdd;
    
    public ControlPanel()
    {
        // create controls
        bAdd = new JButton("+");
        
        // display controls
        add(bAdd);

        // connect controls
        bAdd.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                AddDialog.newDialog();
            }
        });
    }
}
