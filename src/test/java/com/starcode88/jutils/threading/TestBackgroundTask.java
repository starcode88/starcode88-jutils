package com.starcode88.jutils.threading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestBackgroundTask implements BackgroundTaskListener<Integer> {

    private static final Logger logger = LogManager.getLogger(TestBackgroundTask.class);
    
    @Test
    @DisplayName("UT-0000-01")
    public void testRunEmptyTask() {
        logger.trace(">>> public void testRunEmptyTask()");
        BackgroundTask<Integer> task = new BackgroundTask<Integer>();
        task.addListener(this);
        task.start(() -> testRunEmptyTaskImpl(), "UT-1000-01");
        task.removeListener(this);
        logger.trace("<<< public void testRunEmptyTask()");
    }

    public Integer testRunEmptyTaskImpl() {
        logger.trace(">>> public TaskResult task()");
        logger.trace("<<< public TaskResult task()");
        return null;
    }

    @Test
    @DisplayName("UT-0000-02")
    public void testRunTaskWithLoop() {
        logger.trace(">>> public void testRunTaskWithLoop()");
        BackgroundTask<Integer> task = new BackgroundTask<Integer>();
        task.start(() -> testRunTaskWithLoopImpl(), "UT-1000-02");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        logger.trace("<<< public void testRunTaskWithLoop()");
    }

    public Integer testRunTaskWithLoopImpl() {
        logger.trace(">>> public TaskResult testRunTaskWithLoopImpl()");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.fatal("Somebody interrupted me");
            }
            logger.trace("Loop " + i);
        }
        logger.trace("<<< public TaskResult testRunTaskWithLoopImpl()");
        return null;
    }

	@Override
	public void beforeExecution(BackgroundTask<Integer> task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer afterExecution(BackgroundTask<Integer> task, Integer result) {
		// TODO Auto-generated method stub
		return null;
	}
}