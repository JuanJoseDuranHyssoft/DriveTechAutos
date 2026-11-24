/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package drivetech.mainView;
import Users.LoginView;
import javax.swing.plaf.basic.BasicInternalFrameUI;


/**
 *
 * @author Juan Jose
 */
public class DriveTechAutos {

    public static void main(String[] args) {
        DriveTechAutosMain mainView = new DriveTechAutosMain();
        LoginView loginView = new LoginView();
        
        mainView.setSize(1024, 720);
        mainView.setLocationRelativeTo(null);
        mainView.setResizable(false);

        ((BasicInternalFrameUI) loginView.getUI()).setNorthPane(null);
        loginView.setBorder(null);
        loginView.setSize(mainView.getSize());
        

        mainView.add(loginView);
        mainView.setVisible(true);
        loginView.setVisible(false);

    }
}
