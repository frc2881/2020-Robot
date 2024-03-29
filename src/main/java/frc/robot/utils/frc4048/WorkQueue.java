package frc.robot.utils.frc4048;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WorkQueue extends ArrayBlockingQueue<String> {

	/**
	 * Initialize a work queue of a fixed size.
	 */
	public WorkQueue(final int queueSize) {
		super(queueSize, true);
	}

	private static final long serialVersionUID = 1L;

	/**
	 * Add an element to the tail of the queue. Returns true if element was added.
	 * Returns false if the queue is at capacity.
	 */
	public boolean append(final String id) {
		try {
			return this.add(id);
		} catch (final IllegalStateException e) {
			return false;
		}
	}

	/**
	 * Returns the next element from the head of the queue. Or null if there are no
	 * more elements.
	 */
	public String getNext(){
		return this.poll();
	}
}
