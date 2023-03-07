package co.devlhope.emailServices01.Entities;

import lombok.Data;

@Data
public class NotificationDTO {
    /** student contact to send data */
    String contactId;
    /** title of the notification */
    String title;
    /** text of the notification */
    String text;
}
