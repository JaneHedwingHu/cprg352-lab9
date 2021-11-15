<%-- 
    Document   : users
    Created on : Oct 27, 2021, 10:29:25 AM
    Author     : WebChaiQuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
    <title>Users Database</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style>
.button {
  background-color: darkslategray;
 
  border: none;
   border-radius: 15px;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body style="background:darkgrey; font-size: large;">
        <h1 style="text-align: center;">User Database Control!</h1>
        <div class="container mt-3" >
            <div class="d-flex p-3 bg-secondary text-white" style="display:flex;" >
        
                <div class="col-3">
                            <h3 style="text-align: center;">Add User</h3>
                    <form action="users" method="post"  style=" background-color:lightgrey;  border: groove;    border-radius: 15px; padding: 15px 32px;  text-decoration: none;  margin: 5px;">
                        <div class="col-auto"> 
                            <input type="hidden" name="action" value="new">
                        </div>
                        <div class="col-auto"> 
                            <input type="email" name="emailN" placeholder="email">
                        </div>
                        <div class="col-auto"> 
                            <input type="text" name="firstName" placeholder="first name">
                        </div>
                        <div class="col-auto"> 
                            <input type="text" name="lastName" placeholder="last name">
                        </div>
                        <div class="col-auto"> 
                            <input type="text" name="password" placeholder="password">
                        </div>
                        <div class="col-auto"> 
                            <select name="status" id="status" class="form-select form-select-sm" aria-label=".form-select-sm example">
                                <option value="active">active</option>
                                <option value="inactive">inactive</option>

                            </select>
                        </div>
                        <div class="col-auto"> 
                            <select name="role" id="role">
                                <option value="system admin">system admin</option>
                                <option value="regular user">regular user</option>
                                <option value="company admin">company admin</option>

                            </select>
                        </div>
                        <div class="col-auto"> 
                            <input class="button" type="submit" value="save">
                        </div>

                    </form>
                </div>
                <div class="col-3">
                    <h3 style="text-align: center;">Manage Users</h3>
                    <table class="table table-dark table-striped">
                        <tr>
                            <th scope="col">Email</th> 
                            <th scope="col">First Name</th> 
                            <th scope="col">Last Name</th> 
                            <th scope="col">Role </th> 
                            <th scope="col">Edit</th> 
                            <th scope="col">Delete</th> 
                        </tr>
                        <c:if test="${userList!=null && userList.size()>0}">
                        <c:forEach var="item" items="${userList}">
                            <tr>
                                <td>${item.email}</td>
                                <td>${item.firstName}</td>
                                <td>${item.lastName}</td>
                                <td>${item.getRole().getRoleName()}</td>
                                <td>

                                    <a href="<c:url value='users'>
                                           <c:param name='action' value="edit"/>
                                           <c:param name='email' value="${item.email}"/>
                                       </c:url>">edit</a></td>
                                <td><a href="<c:url value='users'>
                                           <c:param name='action' value="delete"/>
                                           <c:param name='email' value="${item.email}"/>
                                       </c:url>">delete</a></td>
                            </tr>
                        </c:forEach>
                        </c:if>

                    </table>
                </div>
                <div       class="col-3">
                    <h3 style="text-align: center;">Edit User</h3>

                    <form action="users" method="post" style=" background-color:dimgray;  border: groove;   border-radius: 15px; border-color:darkkhaki;  padding: 15px 32px;  text-decoration: none;  margin: 5px;">

                        <div class="col-auto"> 
                            <input type="hidden" name="action" value="edit">
                        </div>
                        <div class="col-auto"> 
                            <input type="email" name="emailNE" placeholder="email" value="${email}">
                        </div>
                        <div class="col-auto"> 
                            <input type="text" name="firstNameE" placeholder="first name" value="${fistName}">
                        </div>
                        <div class="col-auto"> 
                            <input type="text" name="lastNameE" placeholder="last name" value="${lastName}">
                        </div>
                        <div class="col-auto"> 
                            <input type="text" name="passwordE" placeholder="password" value="${password}">
                        </div>
                        <div class="col-auto"> 
                            <c:choose>  
                                <c:when test="${status==true}">
                                    <select name="statusE" id="status" class="form-select form-select-sm" aria-label=".form-select-sm example" value="${status}">
                                        <option selected value="active">active</option>
                                        <option value="inactive">inactive</option>

                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="statusE" id="status" class="form-select form-select-sm" aria-label=".form-select-sm example" value="${status}">
                                        <option  value="active">active</option>
                                        <option selected value="inactive">inactive</option>

                                    </select>

                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-auto"> 
                            <c:choose>
                                <c:when test="${roleN==1}">
                                    <select name="roleE" id="role" class="form-select form-select-sm" aria-label=".form-select-sm example" value="${role}">
                                        <option selected value="system admin">system admin</option>
                                        <option  value="regular user">regular user</option>
                                        <option value="company admin">company admin</option>

                                    </select>
                                </c:when>
                                <c:when test="${roleN==2}">
                                    <select name="roleE" id="role" class="form-select form-select-sm" aria-label=".form-select-sm example" value="${role}">
                                        <option value="system admin">system admin</option>
                                        <option selected value="regular user">regular user</option>
                                        <option value="company admin">company admin</option>

                                    </select>
                                </c:when>
                                <c:otherwise>

                                    <select name="roleE" id="role" class="form-select form-select-sm" aria-label=".form-select-sm example" value="${role}">
                                        <option value="system admin">system admin</option>
                                        <option value="regular user">regular user</option>
                                        <option selected value="company admin">company admin</option>

                                    </select>


                                </c:otherwise>
                            </c:choose>


                        </div>
                        <div class="col-auto" style="display:flex;"> 
                            <input class="button" type="submit" value="save">
                            <input class="button" type="reset" value="Cancel">
                        </div>
                    </form>
                </div>
            </div>      
                        <c:if test="${errorExist==true}">
                        <p style="text-align: center;">Error: ${error}</p>
                        </c:if>
        </div>
    </body>
</html>
