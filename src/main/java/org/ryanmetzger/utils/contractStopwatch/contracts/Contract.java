/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch.contracts;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author lirelent
 *
 */
public class Contract extends JPanel implements ActionListener
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
    private final Timer timer;
    private final JLabel label;
    private double todayTime;
    
    public Contract()
    {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timer = new Timer(this);
        label = new JLabel();
        add(label);
        add(timer.getTime());
        
        JButton cSwitch = new JButton("switch");
        cSwitch.setActionCommand("switch");
        add(cSwitch);
        cSwitch.addActionListener(this);
        
        JButton play = new JButton(">");
        play.setActionCommand(">");
        add(play);
        play.addActionListener(this);
        
        JButton pause = new JButton("||");
        pause.setActionCommand("||");
        add(pause);
        pause.addActionListener(this);
        
        JButton edit = new JButton("edit");
        edit.setActionCommand("edit");
        add(edit);
        edit.addActionListener(this);
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

    public void stop()
    {
        timer.stop();
    }

    public void setConcurrency(int size)
    {
        timer.start(size);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "switch")
        {
            ContractsManager.getReference().switchContract(this);
            timer.start(1);
        }
        else if(e.getActionCommand() == ">")
        {
            timer.start(1);
            ContractsManager.getReference().multitaskContract(this);
        }
        else if(e.getActionCommand() == "||")
        {
            ContractsManager.getReference().stopContract(this);
            timer.stop();
        }
        else if(e.getActionCommand() == "edit")
        {
            EditContract.editContract(this);
        }
    }

    /**
     * @return the todayTime
     */
    public double getTodayTime()
    {
        return todayTime;
    }

    /**
     * @param todayTime the todayTime to set
     */
    public void setTodayTime(double todayTime)
    {
        this.todayTime = todayTime;
    }
}
