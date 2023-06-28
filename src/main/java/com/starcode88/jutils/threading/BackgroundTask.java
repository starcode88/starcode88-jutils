package com.starcode88.jutils.threading;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BackgroundTask<T> implements Runnable {
	
	private static Logger logger = LogManager.getLogger(BackgroundTask.class);
	
	private volatile LinkedList<BackgroundTaskListener<T>> listeners = 
			new LinkedList<BackgroundTaskListener<T>>();
	
	private Thread thread = null;

	private String name = null;
	
	private Task<T> task = null;

	private volatile T result = null;
	
	private volatile boolean threadIsRunning = false;
	
	public synchronized void start(Task<T> task, String name) { 
		if (threadIsRunning) {
			throw new IllegalStateException("You are calling method \"start(Task task, String name)\", but the current thread is still running. You should wait unitl the current thread has finished, then you can start a new thread.");
		}
		threadIsRunning = true;
		this.task = task;
		this.name = name;
		this.thread = new Thread(this, name);
		this.thread.start();
	}

	public synchronized void addListener(BackgroundTaskListener<T> listener) {
		this.listeners.add(listener);
	}

	public synchronized void removeListener(BackgroundTaskListener<T> listener) {
		this.listeners.remove(listener);
	}

	public T getResult() {
		return this.result;
	}

	@Override
	public void run() {
		this.callListenersBeforeExecution();
		this.result = task.execute();
		this.callListenersAfterExecution();
		threadIsRunning = false;
	}
	
	private synchronized LinkedList<BackgroundTaskListener<T>> getCopyOfListeners() {
		LinkedList<BackgroundTaskListener<T>> copyOfListeners = new LinkedList<BackgroundTaskListener<T>>();
		copyOfListeners.addAll(listeners);
		return copyOfListeners;
	}

	private void callListenersBeforeExecution() {
		LinkedList<BackgroundTaskListener<T>> copyOfListeners = getCopyOfListeners();
		for (BackgroundTaskListener<T> listener : copyOfListeners) {
			listener.beforeExecution(this);
		}
	}

	private void callListenersAfterExecution() {
		LinkedList<BackgroundTaskListener<T>> copyOfListeners = getCopyOfListeners();
		for (BackgroundTaskListener<T> listener : copyOfListeners) {
			listener.afterExecution(this, this.result);
		}
	}
}
