package exceptions;

public class clientError extends Exception
{
    private String messageError;

    public clientError(String message) {
        this.messageError = message;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
