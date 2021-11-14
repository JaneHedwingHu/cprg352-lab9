
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author WebChaiQuan
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = new UserService();

        if (request.getParameterMap().containsKey("action")) {
            String action = request.getParameter("action");
            String email = request.getParameter("email");

            try {
                User user = userService.get(email);

                if (action.equals("edit")) {
                    request.setAttribute("email", user.getEmail());
                    request.setAttribute("status", user.isStatus());
                    request.setAttribute("fistName", user.getFirstName());
                    request.setAttribute("lastName", user.getLastName());
                    request.setAttribute("password", user.getPassword());
                    request.setAttribute("role", userService.getRoleString(user));

                    session.setAttribute("previousEmail", user.getEmail());
                    List<User> userList = userService.getAll();

                    request.setAttribute("userList", userList);
                    request.setAttribute("roleN", user.getRole());

                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;
                }
                if (action.equals("delete")) {
                    userService.delete(email);
                    List<User> userList = userService.getAll();

                    request.setAttribute("userList", userList);
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                List<User> userList = userService.getAll();

                request.setAttribute("userList", userList);

                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                return;

            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        UserService userService = new UserService();
        String action = request.getParameter("action");
        
        List<User> userList = null;
        if (action.equals("new")) {
            try {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String emailNew = request.getParameter("emailN");
                System.out.println("new post " + emailNew);

                String password = request.getParameter("password");
                String statusS = request.getParameter("status");
                boolean status = false;
                if (statusS.equals("active")) {
                    status = true;
                } else {
                    status = false;
                }
                String roleS = request.getParameter("role");
                int roleN = userService.getRoleNumber(roleS);
                User user = new User(emailNew, firstName, lastName, password, roleN, status);
                if (emailNew == null || emailNew.trim().equals("")) {
                    userList = userService.getAll();
                    request.setAttribute("userList", userList);
                    request.setAttribute("error", "the email address cannot be null or empty");
                    request.setAttribute("errorExist", true);
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;
                }
                
                /*
                if (userService.checkExist(emailNew)) {
                     List<User> userList = userService.getAll();
                request.setAttribute("userList", userList);
                    request.setAttribute("error", "the email address is existed already");
                    request.setAttribute("errorExist", true);
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;

                }
               

                userService.insert(user);
                List<User> userList = userService.getAll();

                }*/ 
                
                userList = userService.getAll();

                request.setAttribute("userList", userList);
                try {
                    userService.insert(user);
                     userList = userService.getAll();

                request.setAttribute("userList", userList);
                } catch (SQLException e) {
                    request.setAttribute("error", "the email address is existed already");
                    request.setAttribute("errorExist", true);
                }
                
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                return;
                
            } 
                catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("edit")) {
            try {
                String firstNameE = request.getParameter("firstNameE");
                String lastNameE = request.getParameter("lastNameE");
                String emailNewE = request.getParameter("emailNE");
                System.out.println("email new: " + emailNewE);
                String previousEmailE = (String) session.getAttribute("previousEmail");
                System.out.println("previous E " + previousEmailE);
                String passwordE = request.getParameter("passwordE");
                String statusSE = request.getParameter("statusE");
                boolean statusE = false;
                if (statusSE.equals("active")) {
                    statusE = true;
                } else {
                    statusE = false;
                }
                String roleSE = request.getParameter("roleE");
                int roleNE = userService.getRoleNumber(roleSE);
                User user = new User(emailNewE, firstNameE, lastNameE, passwordE, roleNE, statusE);

                if (previousEmailE.equals(emailNewE) == false) {
                    userList = userService.getAll();
                    request.setAttribute("userList", userList);
                    request.setAttribute("error", "the email address cannot be changed");
                    request.setAttribute("errorExist", true);
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;

                }
                userService.update(previousEmailE, user);
                userList = userService.getAll();
                request.setAttribute("userList", userList);

                request.setAttribute("userList", userList);
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                return;
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
