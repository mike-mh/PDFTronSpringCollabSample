package com.example.messagingstompwebsocket;

public class XfdfMessage {

	private String documentId;
	private String annotationId;
	private String xfdfString;

	public XfdfMessage(String documentId, String annotationId, String xfdfString) {
		this.documentId = documentId;
		this.annotationId = annotationId;
		this.xfdfString = xfdfString;
	}

	public String getDocumentId() {
		return documentId;
	}

	public String getAnnotationId() {
		return annotationId;
	}

	public String getXfdfString() {
		return xfdfString;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public void setAnnotationId(String annotationId) {
		this.annotationId = annotationId;
	}

	public void setXfdfString(String xfdfString) {
		this.xfdfString = xfdfString;
	}
}
