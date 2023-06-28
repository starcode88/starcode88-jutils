package com.starcode88.jutils.threading;

public interface BackgroundTaskListener<T> {

	public void beforeExecution(BackgroundTask<T> task);
	
	public T afterExecution(BackgroundTask<T> task, T result);

}
