package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/** Schedule class that handles different courses
 * @author haash
 *
 */
public class Schedule {
	
	/**
	 * The main schedule of the courses
	 */
	private ArrayList<Course> schedule;
	
	/**
	 * The title of the schedule
	 */
	private String title;
	
	/**
	 * Creates the schedule in the default position
	 */
	public Schedule() {
		resetSchedule();
	}
	
	/** Adds a course to the schedule after checking if there was a duplicate or a conflict
	 * @param wasd - The course that is going to be added
	 * @return - True or a Throw based on if it was added
	 */
	public boolean addCourseToSchedule(Course wasd) {
		if (!schedule.isEmpty()) {
			for(int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).isDuplicate(wasd)) {
					throw new IllegalArgumentException("You are already enrolled in " + wasd.getName());
				}
				try {
					schedule.get(i).checkConflict(wasd);
				} catch (Exception e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
		}
		
		schedule.add(wasd);
		return true;
	}
	
	/** Removes a course in the schedule based on if it is in the schedule or not. Sees if there is, if not return false
	 * @param wasd - The course that is going to be removed
	 * @return - True or false if it was removed or not
	 */
	public boolean removeCourseFromSchedule(Course wasd) {
		
		if (wasd == null) {
			return false;
		}
		
		if (schedule.isEmpty()) {
			return false;
		} else {
			for(int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).isDuplicate(wasd)) {
					schedule.remove(i);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Resets the schedule to a default appearance
	 */
	public void resetSchedule() {
		schedule = new ArrayList<>();
		setTitle("My Schedule");
	}
	
	/** Gives the scheduled courses in a 2d fashion
	 * @return - A 2d ArrayList that contains the different courses of the schedule
	 */
	public String[][] getScheduledCourses() {
		if (schedule == null) {
			return null;
		}
		String[][] wdsa = new String[schedule.size()][5];
		for(int i = 0; i < schedule.size(); i++) {
			wdsa[i] = schedule.get(i).getShortDisplayArray();
		}
		
		return wdsa;
	}
	
	/** Sets teh title of the schedule
	 * @param title - the title of the schedule you want
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/** Gives the current title of the schedule
	 * @return - A string of the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * returns sum of credits in schedule
	 * 
	 * @return credits
	 */
	public int getScheduleCredits() {
		int x = 0;
		for(int i = 0; i < schedule.size(); i++) {
			x += schedule.get(i).getCredits();
		}
		return x;
	}
	
	/**
	 * can add
	 * 
	 * @param c course
	 * @return boolean
	 */
	public boolean canAdd(Course c) {
		if(c == null) {
			return false;
		}
		for(int x = 0; x < schedule.size(); x++) {
			if(c.equals(schedule.get(x))) {
				return false;
			}
			
			
			for (int i = 0; i < c.getMeetingDays().length(); i++) {
				for (int j = 0; j < schedule.get(x).getMeetingDays().length(); j++) {
					if (c.getMeetingDays().charAt(i) == 'A' && schedule.get(x).getMeetingDays().charAt(j) == 'A') {
						break;
					} else if (c.getMeetingDays().charAt(i) == schedule.get(x).getMeetingDays().charAt(j)) {
						if (c.getStartTime() <= schedule.get(x).getStartTime()
								&& schedule.get(x).getStartTime() <= c.getEndTime()) {
							return false;
						} else if (c.getStartTime() <= schedule.get(x).getEndTime()
								&& schedule.get(x).getEndTime() <= c.getEndTime()) {
							return false;
						} else if (c.getStartTime() >= schedule.get(x).getStartTime()
								&& schedule.get(x).getEndTime() >= c.getStartTime()) {
							return false;
						}
					}
				}
			}
			
		}
		
		return true;
	}
	
	
}
