package unlv.erc.partiubalada.Controller;

import android.widget.ProgressBar;

import unlv.erc.partiubalada.DAO.NormalUserDAO;
import unlv.erc.partiubalada.model.NormalUser;
import unlv.erc.partiubalada.view.MainActivity;
import unlv.erc.partiubalada.view.SignUpActivity;

/**
 * Created by goliveira on 5/8/16.
 */
public class UserController {

    NormalUser user;
    NormalUserDAO userDAO;

    public NormalUser getUser() {
        return user;
    }

    public void setUser(NormalUser user) {
        this.user = user;
    }


    public void saveUser(UserController user, SignUpActivity activity){

        userDAO = new NormalUserDAO();


        userDAO.saveUserOnFireBase(user, activity);


    }


}
