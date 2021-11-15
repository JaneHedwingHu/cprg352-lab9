
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
import models.Role;
import models.User;
import services.RoleService;
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

        if (request.getParameterMap().containsKey("action")) {
            String action = request.getParameter("action");
            String email = request.getParameter("email");

            try {
                User user = UserService.get(email);
                if (action.equals("edit")) {
                    request.setAttribute("email", user.getEmail());
                    request.setAttribute("status", user.isStatus());
                    request.setAttribute("fistName", user.getFirstName());
                    request.setAttribute("lastName", user.getLastName());
                    request.setAttribute("password", user.getPassword());
                    request.setAttribute("role", user.getRole().getRoleId());

                    session.setAttribute("previousEmail", user.getEmail());
                    List<User> userList = UserService.getAll();

                    request.setAttribute("userList", userList);
                    request.setAttribute("roleN", user.getRole().getRoleId());

                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;
                }
                if (action.equals("delete")) {
                    UserService.delete(email);
                    List<User> userList = UserService.getAll();

                    request.setAttribute("userList", userList);
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                List<User> userList = UserService.getAll();

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
                Role role = RoleService.getRoleFromName(roleS);
                
                User user = new User(emailNew, firstName, lastName, password, role, status);
                if (emailNew == null || emailNew.trim().equals("")) {
                    userList = UserService.getAll();
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
                
                userList = UserService.getAll();

                request.setAttribute("userList", userList);
                try {
                    UserService.insert(user);
                    userList = UserService.getAll();

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
                Role roleE = RoleService.getRoleFromName(roleSE);
                User user = new User(emailNewE, firstNameE, lastNameE, passwordE, roleE, statusE);

                if (previousEmailE.equals(emailNewE) == false) {
                    userList = UserService.getAll();
                    request.setAttribute("userList", userList);
                    request.setAttribute("error", "the email address cannot be changed");
                    request.setAttribute("errorExist", true);
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;

                }
                UserService.update(previousEmailE, user);
                userList = UserService.getAll();
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
