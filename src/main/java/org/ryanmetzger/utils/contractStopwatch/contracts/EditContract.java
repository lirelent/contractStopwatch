/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch.contracts;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author lirelent
 *
 */
public class EditContract extends JPanel
{
    private static final long serialVersionUID = -5340375202748835103L;

    private final JLabel descTitle = new JLabel("Description");
    private final JLabel projTitle = new JLabel("Project");
    private final JLabel taskTitle = new JLabel("Task");
    
    private final JTextField desc = new JTextField();
    private final JTextField proj = new JTextField();
    private final JTextField task = new JTextField();
    
    private final Contract contract;
    
    public EditContract(Contract contract)
    {
        this.contract = contract;
        desc.setText(contract.getDesc());
        proj.setText(contract.getProject());
        task.setText(contract.getTask());
        init();
    }
    
    public EditContract()
    {
        this.contract = new Contract();
        init();
    }
    
    public Contract getContract()
    {
        contract.setFields(desc.getText(),
                proj.getText(), task.getText());
        return contract;
    }
    
    private void init()
    {
        setLayout(new GridLayout(2,3));
        add(descTitle);
        add(projTitle);
        add(taskTitle);
        add(desc);
        add(proj);
        add(task);
    }
    
    public static void editContract(final Contract contract)
    {
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run()
            {
                final JFrame frame = new JFrame();
                JPanel main = new JPanel();
                frame.add(main);
                
                final EditContract edit =
                    new EditContract(contract);
                main.add(edit);
                
                JButton bOk = new JButton("ok");
                main.add(bOk);
                bOk.addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e)
                    {
                        edit.getContract();
                        frame.dispose();
                    }
                });
                
                frame.getRootPane().setDefaultButton(bOk);
                
                frame.setTitle("Edit Contract");
                frame.setSize(250, 140);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
