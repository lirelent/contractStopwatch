/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.ryanmetzger.utils.contractStopwatch.contracts.ContractsManager;

/**
 * entry point and main window for app
 */
public class App extends JFrame
{
    private static final long serialVersionUID = -8197953707733275303L;
    
    private final JPanel mainPanel = new JPanel();
    private final ControlPanel controls;
    
    public App()
    {
        // setup main window
        setTitle("Contract Stopwatch");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // setup contents
        controls = new ControlPanel();
        ContractsManager manager = ContractsManager.getReference();
        
        // add contents
        getContentPane().add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(controls, BorderLayout.PAGE_START);
        mainPanel.add(new JScrollPane(manager), BorderLayout.CENTER);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                new App().setVisible(true);
            }
        });
    }
}
