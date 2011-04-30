/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch.contracts;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
}
