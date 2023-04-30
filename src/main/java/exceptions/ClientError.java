package exceptions;

public class ClientError extends Exception
{
    private String messageError;

    public ClientError(String message) {
        this.messageError = message;
    }

    public String GetMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
