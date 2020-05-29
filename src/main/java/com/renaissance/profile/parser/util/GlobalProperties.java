package com.renaissance.profile.parser.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalProperties {
	 @Value("${max-assigned-days}")
	    private int maxAssignedDays;

	public int getMaxAssignedDays() {
		return maxAssignedDays;
	}

	public void setMaxAssignedDays(int maxAssignedDays) {
		this.maxAssignedDays = maxAssignedDays;
	}
}
