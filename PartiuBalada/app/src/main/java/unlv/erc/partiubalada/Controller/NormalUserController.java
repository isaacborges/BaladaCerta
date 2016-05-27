package unlv.erc.partiubalada.Controller;


        import android.view.View;
        import android.widget.EditText;
        import android.widget.ProgressBar;

        import com.firebase.client.Firebase;

        import unlv.erc.partiubalada.DAO.NormalUserDAO;
        import unlv.erc.partiubalada.R;
        import unlv.erc.partiubalada.model.NormalUser;
        import unlv.erc.partiubalada.view.MainActivity;
        import unlv.erc.partiubalada.view.SignUpActivity;

/**
 * Created by goliveira on 5/8/16.
 */

public class NormalUserController {

    private NormalUser user;
    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText email;
    private EditText password;
    private EditText city;
    private EditText state;
    private ProgressBar progressBar;
    NormalUserDAO userDAO;
    SignUpActivity activity;

    public NormalUserController(SignUpActivity activity){
        this.activity = activity;
    }

    public NormalUser getUser() {
        return user;
    }

    public void setUser(NormalUser user) {
        this.user = user;
    }

    public void startComponents() {

        name = (EditText) activity.findViewById(R.id.edit_text_username);
        age=(EditText) activity.findViewById(R.id.edit_text_age);
        gender= (EditText) activity.findViewById(R.id.edit_text_sex);
        city=(EditText) activity.findViewById(R.id.edit_text_city);
        state=(EditText) activity.findViewById(R.id.edit_text_state);
        email = (EditText) activity.findViewById(R.id.edit_text_new_email);
        password = (EditText) activity.findViewById( R.id.edit_text_new_password);
        progressBar = (ProgressBar) activity.findViewById(R.id.progress_bar_sign_up);
    }

    protected void setUpUser(){
        user = new NormalUser();
        user.setName(name.getText().toString());
        user.setAge(age.getText().toString());
        user.setGender(gender.getText().toString());
        user.setCity(city.getText().toString());
        user.setState(state.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
    }


    public void saveUser(){

        //progressBar.setVisibility(View.VISIBLE);

        setUpUser();

        userDAO = new NormalUserDAO();

        userDAO.saveUserOnFireBase(activity,user);


    }



}
