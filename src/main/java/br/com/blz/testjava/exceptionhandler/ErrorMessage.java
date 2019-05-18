package br.com.blz.testjava.exceptionhandler;

public class ErrorMessage {
	private String messageUser;
	private String messageDeveloper;
	
	public ErrorMessage(String messageUser, String messageDeveloper) {
		this.messageUser = messageUser;
		this.messageDeveloper = messageDeveloper;
	}

	public String getMessageUser() {
		return messageUser;
	}

	public void setMessageUser(String messageUser) {
		this.messageUser = messageUser;
	}

	public String getMessageDeveloper() {
		return messageDeveloper;
	}

	public void setMessageDeveloper(String messageDeveloper) {
		this.messageDeveloper = messageDeveloper;
	}
}