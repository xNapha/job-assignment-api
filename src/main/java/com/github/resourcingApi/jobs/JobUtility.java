package com.github.resourcingApi.jobs;

import java.util.Date;

public class JobUtility {
	public static boolean checkDates(Job currentJob, Job requestedJob) {
		Date cJStart = currentJob.getStartDate();
		Date cJEnd = currentJob.getEndDate();
		Date rJStart = requestedJob.getStartDate();
		Date rJEnd = requestedJob.getEndDate();

		boolean isCJStartWithinRJDate = cJStart.compareTo(rJStart) > 0 && cJStart.compareTo(rJEnd) < 0;

		boolean isCJEndWithinRJDate = cJEnd.compareTo(rJStart) < 0 && cJEnd.compareTo(rJEnd) > 0;

		boolean isCJStartEqualRJStart = cJStart.compareTo(rJStart) == 0;

		boolean isCJEndEqualRJStart = cJEnd.compareTo(rJStart) == 0;

		boolean isCJStartEqualRJEnd = cJStart.compareTo(rJEnd) == 0;

		boolean isCJEndEqualRJEnd = cJEnd.compareTo(rJStart) == 0;

		boolean isRJDatesWithinCJDates = cJStart.compareTo(rJStart) < 0 && cJEnd.compareTo(rJEnd) > 0;

		return (isCJStartWithinRJDate) || (isCJEndWithinRJDate) || (isCJStartEqualRJStart) || (isCJEndEqualRJStart)
				|| (isCJStartEqualRJEnd) || (isCJEndEqualRJEnd) || (isRJDatesWithinCJDates);
	}
}
