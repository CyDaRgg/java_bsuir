package exceptions;

public class ServerError extends Exception
{
    private String messageError;

    public ServerError(String messageError) {
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
