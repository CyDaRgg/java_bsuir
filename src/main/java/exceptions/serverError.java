package exceptions;

public class serverError extends Exception
{
    private String messageError;

    public serverError(String messageError) {
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
