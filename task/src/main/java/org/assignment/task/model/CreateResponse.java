package org.assignment.task.model;

public class CreateResponse {

	private String id;
	private String createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "CreateResponse [id=" + id + ", createdAt=" + createdAt + "]";
	}

}
