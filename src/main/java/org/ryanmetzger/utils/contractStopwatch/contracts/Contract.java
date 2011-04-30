/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch.contracts;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author lirelent
 *
 */
public class Contract extends JPanel
{
    private static final long serialVersionUID = -1343399824907047895L;
    
    /**
     * description of this contract
     */
    private String desc;
    /**
     * project charge code
     */
    private String project;
    /**
     * task charge code
     */
    private String task;
    private final JLabel label;
    
    public Contract()
    {
        label = new JLabel();
        add(label);
    }
    
    public void setFields(String desc, String project, String task)
    {
        this.desc = desc;
        this.project = project;
        this.task = task;
        label.setText(this.toString());
    }
    
    @Override
    public String toString()
    {
        return new String(desc + " [" + project + ":" + task + "]");
    }

    /**
     * @return the {@link #desc}
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @return the {@link #project}
     */
    public String getProject()
    {
        return project;
    }

    /**
     * @return the {@link #task}
     */
    public String getTask()
    {
        return task;
    }
}
