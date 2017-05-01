package pw.cdmi.exception;

public interface ErrorReason {
	public int getHttpStatus();
	public int getCode();
	public String getReason();
}
