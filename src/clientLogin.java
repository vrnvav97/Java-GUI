
package gui2;

//imported packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class clientLogin extends javax.swing.JFrame {

//default constructor
    public clientLogin() {
        initComponents();
    }

   
   //SupressWarnings annotation
    @SuppressWarnings("unchecked")
   
    private void initComponents() {

        //initializing all the components
        jLabel2 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        passWord = new javax.swing.JPasswordField();
        resetButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
 
        //Password label
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("Password");

        //Username label
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel1.setText("Username");

        //Login Label
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Login Form");

        //Login Button
        loginButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loginButton.setText("Login");

        //adding event: ActionListener to button with anonymous class
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        //Reset Button
        resetButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resetButton.setText("Reset");
        //adding event: ActionListener
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        //Register User button
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Register User");

        //Adding event to button
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        //setting default layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(184, 184, 184))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(loginButton)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userName)
                            .addComponent(passWord))
                        .addGap(74, 74, 74))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton)
                        .addGap(36, 36, 36)
                        .addComponent(jButton1)
                        .addGap(0, 52, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(resetButton)
                    .addComponent(jButton1))
                .addGap(55, 55, 55))
        );

        pack();
    }                     


    //giving body to actionPerformed method of interface ActionListener
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) { 

    //if user has not entered anything                                           
        if (userName.getText().isEmpty() || passWord.getText().isEmpty())
            JOptionPane.showMessageDialog(null,"Enter all the fields");
         else
         {
            //creating connection interface instance variable
        Connection con = null;
        
        try
        {
            //dynamically loading class
            Class.forName ("com.mysql.cj.jdbc.Driver");
            //creating connection with database
            //this example is with database stored on local system, 
            //We can configure it for remote db also by changing url username and password

            //con = DriverManager.getConnection (url,username,password);
            con = DriverManager.getConnection ("jdbc:mysql://localhost/emp","root","4321");

            //Query to be performed
            String sqlQuery = "Select * from logindetails where username=? and password=?";
            
            //creating object of PreparedStatement           
            PreparedStatement pst = con.prepareStatement(sqlQuery);
            pst.setString (1,userName.getText()); //here userName is name of textbox which takes username from user
            pst.setString (2,passWord.getText()); //here passWord is name of textbox which take password from user
            
            //getting output in the form of resultset
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {   
                //displaying the userDashboard if login is success
                this.setVisible(false);
                new Dashboard().setVisible(true);
            }
            else
            {
                //displaying failed popup if username and password fails to match with database
                JOptionPane.showMessageDialog(null,"Credentials did not match");
                //resetting the textboxes
                userName.setText ("");
                passWord.setText("");
            }
        } catch (Exception e) {
            //to print any exception message which has occured
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
    
    //giving body to reset button actionPerformed
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        userName.setText ("");
        passWord.setText("");
    }

    //giving body to add new username button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        new enterData().setVisible(true);
        this.dispose();
    }

    //main method
    public static void main(String args[]) {

        try {
            //setting nimbus look and feel
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(clientLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clientLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clientLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clientLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clientLogin().setVisible(true);
            }
        });
    }

    //global variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passWord;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField userName;
    
}
