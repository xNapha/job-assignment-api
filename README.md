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

### 10/06/2023 | Project Creations

---

<!-- ## What did you struggle with? -->

<!-- ---

## Further details, related projects, reimplementations

- Is this project a reimplementation for something you've done in the past? if so explain it and link it here.
- If it's an API, is there a client app that works with this project? link it -->
