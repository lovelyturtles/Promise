package comp3350.group6.promise.business;

import android.widget.EditText;

public class NotifService {

    //use the unique userName to fetch the user
    // EditText userText = findViewById( R.id.loginNameInput );
    // String userName   = userText.getText().toString();
    private static NotifService instance;

    private NotifService() {}

    public static NotifService getInstance() {
        if(NotifService.instance == null) {
            NotifService.instance = new NotifService();
        }
        return NotifService.instance;
    }

}
