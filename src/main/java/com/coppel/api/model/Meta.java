package com.coppel.api.model;

//@Builder
public class Meta {
	private String status;

	public Meta(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Meta [status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
