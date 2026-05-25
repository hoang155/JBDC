package CustomException;

public class InvalidPhoneNumberLengthException extends Exception {

    // Constructor nhận thông báo lỗi (message) và truyền lên lớp cha
    public InvalidPhoneNumberLengthException(String message) {
        super(message);
    }
}