package depaul.csc452.group2.campusconnect.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String msg) {
        super(msg);
    }
}
