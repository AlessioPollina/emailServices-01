package co.devlhope.emailServices01.API.Controllers;
import co.devlhope.emailServices01.Services.EmailService;
import co.devlhope.emailServices01.Entities.Student;
import co.devlhope.emailServices01.Entities.NotificationDTO;
import co.devlhope.emailServices01.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NotificationController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationDTO payload){
        try {
            Student studentToSendNotification = studentService.getStudentById(payload.getContactId());
            System.out.println("Getting the Student: " + studentToSendNotification);
            if (studentToSendNotification == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not find the Student");
            }
            emailService.sendTo(studentToSendNotification.getEmail(), payload.getTitle(), payload.getText());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            System.err.println("Error in notification controller: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
