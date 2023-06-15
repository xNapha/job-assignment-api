# Job Assignment API

<!-- {add test badges here, all projects you build from here on out will have tests, therefore you should have github workflow badges at the top of your repositories: [Github Workflow Badges](https://docs.github.com/en/actions/monitoring-and-troubleshooting-workflows/adding-a-workflow-status-badge)} -->

<!-- ## Demo & Snippets

- Include hosted link
- Include images of app if CLI or Client App

--- -->

## Requirements / Purpose

The task is to build a Resourcing API using the Java Spring Boot framework, that allows consumers to assign temps to jobs.

### Assumptions

- Temps can only have one job at a time (can't be doing 2 jobs on the same date)
- Temps can have many jobs, and job can have 1 temp assigned
- Should be able to assign existing temps to jobs via the POST /jobs & PATCH /jobs/{id}
- You must use a relational database.

---

<!-- ## Build Steps

- how to build / run project
- use proper code snippets if there are any commands to run

---

## Design Goals / Approach

--- -->

## Features

### Endpoints

- POST /jobs - Creates a job
- PATCH /jobs/{id} - Updates job, endpoint should be used to assign temps to jobs
- GET /jobs - Fetch all jobs
- GET /jobs?assigned={true|false} - Filter by whether a job is assigned to a temp or not
- GET /jobs/{id} - (id, name, tempId, startDate, endDate)
- POST /temps - Create a temp
- GET /temps - List all temps
- GET /temps?jobId={jobId} - List temps that are available for a job based on the jobs date range -GET /temps/{id} - get temp by id (should also display jobs they've been assigned to)

### Payloads

```java
{
	id: ...,
	name: ...,
	startDate: ...,
	endDate: ...,
	temp: {
		id: ...,
		firstName: ...,
		lastName: ...,
	} // temp can also be null if a temp hasn't been assigned to the job
}

// GET /temps/{id}
{
	id: ...,
	firstName: ...,
	lastName: ...,
	jobs: [{
		id: ...,
		name: ...,
		startDate: ...,
		endDate: ...,
	}, ...] // can be empty if temp hasn't been assigned to jobs
}
```

---

<!-- ## Known issues

- Remaining bugs, things that have been left unfixed
- Features that are buggy / flimsy

--- -->

<!-- ## Future Goals

- What are the immediate features you'd add given more time

--- -->

## Change logs

### 10/06/2023 | Project Creation

- Nothing too interesting happened today, just created the project and created the necessary files

### 13/06/2023 | Creating all the basic POST and GET methods

- Create a diagram of the database, even though it is simple it was still good to create a visualisation of it, [link here](https://dbdiagram.io/d/6487bd63722eb77494d83e11)
- Watched a tutorial on how to create a OneToMany relationship in java spring, easier that I thought it would be
- Added the less complicated methods of POST and GET for both entities
- Read up on trying to create custom queries in the Repository files for the end points

### 14/06/2023 | Implemented several more endpoints

- Changed the DTO's to store an Optional<Long> temp, where the body will either provide a null or a long value
- Added the RequestBody annotation to the GET endpoint for jobs
- Added the Patch Endpoint

### 15/06/2023 | Implemeneted an Endpoint Assumption

- Added the logic for the assumption, "Temps can only have one job at a time (can't be doing 2 jobs on the same date)"

---

## What did you struggle with?

### 14/06/2023 | Optionals

I spent a good hour trying to figure out how to set a null value to a primitive, to only find out that I can't do it. Realising the stupidity of what I was trying to do, I failed to use Optionals even though i was introduced to them. For some reason I was stuck thinking that Optionals should only be used in the situation where I'm trying to fetch data from the database, I failed to realise that Optionals are used when you dont know if what you will get is either a null value or not. Realising the gap in knowledge I quickly replaced some typing with Optional<{type}>. Atleast I learnt something from my mistakes, but it feels bad to have made it to begin with.

### 15/06/2023 | Confusing myself with long comparations

For the assumption, "Temps can only have one job at a time (can't be doing 2 jobs on the same date)". I ended up doing a long comparative line that confused me half way through, maybe i should have drew out a diagram of some sorts to help me instead of thinking of all the possible solutions.

-------- Code below for reference --------

```java
public boolean checkDates(Job currentJob, Job requestedJob) {
		Date cJStart = currentJob.getStartDate();
		Date cJEnd = currentJob.getEndDate();
		Date rJStart = requestedJob.getStartDate();
		Date rJEnd = requestedJob.getEndDate();

		boolean isCJStartWithinRJDate = cJStart.compareTo(rJStart) > 0 && cJStart.compareTo(rJEnd) < 0;

		boolean isCJEndWithinRJDate = cJEnd.compareTo(rJStart) < 0 && cJEnd.compareTo(rJEnd) > 0;

		boolean isCJStartEqualRJStart = cJStart.compareTo(rJStart) == 0;

		boolean isCJEndEqualRJStart = cJEnd.compareTo(rJStart) == 0;

		boolean isCJStartEqualRJEnd = cJStart.compareTo(rJEnd) == 0;

		boolean isCJEndEqualRJEND = cJEnd.compareTo(rJStart) == 0;

		boolean isRJDatesWithinCJDates = cJStart.compareTo(rJStart) < 0 && cJEnd.compareTo(rJEnd) > 0;

		return (isCJStartWithinRJDate) || (isCJEndWithinRJDate) || (isCJStartEqualRJStart) || (isCJEndEqualRJStart)
				|| (isCJStartEqualRJEnd) || (isCJEndEqualRJEND) || (isRJDatesWithinCJDates);
	}

```

<!-- ---

## Further details, related projects, reimplementations

- Is this project a reimplementation for something you've done in the past? if so explain it and link it here.
- If it's an API, is there a client app that works with this project? link it -->
