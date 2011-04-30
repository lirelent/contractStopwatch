/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch.contracts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author lirelent
 *
 */
public class AddDialog extends JFrame
{
    private static final long serialVersionUID = -2627538155764478430L;

    private ContractsManager manager = ContractsManager.getReference();
    
    private AddDialog()
    {
        // setup
        setTitle("Add Contract");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // main panel
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        add(main);
        
        // config
        final EditContract config = new EditContract();
        add(config, BorderLayout.PAGE_START);
        
        // ok button
        JButton bOk = new JButton("ok");
        main.add(bOk, BorderLayout.PAGE_END);
        bOk.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                manager.addContract(config.getContract());
                dispose();
            }
        });
        
        // let the enter key do the talking
        getRootPane().setDefaultButton(bOk);
    }
    
    public static void newDialog()
    {
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run()
            {
                new AddDialog().setVisible(true);
            }
        });
    }
}
