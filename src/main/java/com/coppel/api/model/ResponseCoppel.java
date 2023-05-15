package com.coppel.api.model;

//@Builder
public class ResponseCoppel {

	private Meta meta;
	private Object data;

	public ResponseCoppel() {

	}

	public ResponseCoppel(Meta meta, Object data) {
		this.meta = meta;
		this.data = data;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseCoppel [meta=");
		builder.append(meta);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
