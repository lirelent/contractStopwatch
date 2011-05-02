/**
 * Copyright 2011 Ryan Metzger
 * This program distributed under GPLv3
 * see COPYING for more information
 */
package org.ryanmetzger.utils.contractStopwatch.contracts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JLabel;

/**
 * @author lirelent
 *
 */
public class Timer
{
    final private AtomicBoolean isRunning;
    final private Thread update;
    final private JLabel lTime;
    final private Contract contract;
    
    private long cumTime = 0l;
    private int concurrent;
    
    public Timer(Contract contract)
    {
        isRunning = new AtomicBoolean(false);
        
        update = new Thread(new Update());
        update.start();
        
        lTime = new JLabel(" [||] 0.00 hrs");
        this.contract = contract;
    }
    
    public void start(int concurrent)
    {
        this.concurrent = concurrent;
        isRunning.set(true);
        synchronized (isRunning)
        {
            isRunning.notifyAll();
        }
    }
    
    public void stop()
    {
        isRunning.set(false);
    }
    
    public JLabel getTime()
    {
        return lTime;
    }
    
    public static String getFormated(long millis)
    {
        int hours = (int) TimeUnit.MILLISECONDS.toHours(millis);
        double hourFraction = ((TimeUnit.MILLISECONDS.toMinutes(millis)
                                - (hours * 60))/60.0);
        
        return String.format("%.2f hrs", hours + hourFraction);
    }
    
    private class Update implements Runnable
    {
        private long lastTime;
        
        public void run()
        {
            while (true)
            {
                try
                {
                    synchronized (isRunning)
                    {
                        isRunning.wait();
                    }
                    lastTime = System.currentTimeMillis();
                    while (isRunning.get())
                    {
                        long curTime = System.currentTimeMillis();
                        cumTime += (curTime - lastTime)/concurrent;
                        contract.setTodayTime(cumTime);
                        lastTime = curTime;

                        lTime.setText(" [>] " + getFormated(cumTime));

                        Thread.sleep(1000);
                    }
                    lTime.setText(" [||] " + getFormated(cumTime));
                } catch (InterruptedException e)
                {
                    return;
                }
            }
        }
    }
}
