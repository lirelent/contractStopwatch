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
    
    private long cumTime = 0l;
    private int concurrent;
    
    public Timer()
    {
        isRunning = new AtomicBoolean(false);
        
        update = new Thread(new Update());
        update.start();
        
        lTime = new JLabel(" [||] 0.00");
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
                    int hours = 0;
                    double hourFraction = 0.0;
                    while (isRunning.get())
                    {
                        long curTime = System.currentTimeMillis();
                        cumTime += (curTime - lastTime)/concurrent;
                        lastTime = curTime;

                        hours = (int) TimeUnit.MILLISECONDS.toHours(cumTime);
                        hourFraction = ((TimeUnit.MILLISECONDS.toMinutes(cumTime)
                                                - (hours * 60))/60.0);
                        lTime.setText(" [>] " + String.format("%.2f", 
                                hours + hourFraction));

                        Thread.sleep(1000);
                    }
                    lTime.setText(" [||] " + String.format("%.2f", 
                            hours + hourFraction));
                } catch (InterruptedException e)
                {
                    return;
                }
            }
        }
    }
}
