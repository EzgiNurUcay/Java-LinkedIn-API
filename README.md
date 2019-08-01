# Java-LinkedIn-API
Answer to the question how I can login to my website with linkedin via Java Codes.

# STEP-1
First of all you have to create an app in Linkedin Developer page. You should save your client screet and client id. In this example we will redirect response to a servlet class.

Create a Dynamic Web Project.
Your directory should be like this.

![directory](https://user-images.githubusercontent.com/25286372/62293682-cc18eb00-b471-11e9-9e87-389026cf5896.PNG)

# STEP-2 
Create classes and write codes. Don't forget json library. You have to add to WebContent-> WEB_INF -> lib 

After all steps you can run your server. It will show user's information in terminal. To see in browser you need to create a User Model. You should store data in user then return object. You should create session in servlet and can call session object in jsp.

From Servlet
--------------
HttpSession session = request.getSession();
	session.setAttribute("userInfo", user.getName());
  ....
  response.sendRedirect("views/yourjsp.jsp");
  
 In JSP
 -----------------
To call session value:
  <%=session.getAttribute("userInfo")%>  
To call and use session object 
<c:set value="${sessionScope.userInfo}" var="userInformation" />
${userInformation.name}
