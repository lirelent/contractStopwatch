/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * entry point and main window for app
 */
public class App extends JFrame
{
    private static final long serialVersionUID = 1L;

    public App()
    {
        setTitle("Contract Stopwatch");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
