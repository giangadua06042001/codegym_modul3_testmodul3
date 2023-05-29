package controller;

import dao.DaoEmployee;
import module.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletEmloyee", value = "/employee")
public class ServletEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoEmployee employeeDao;
    public void init(){
        this.employeeDao=new DaoEmployee();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String choice=request.getParameter("choice");
     if(choice==null){
         choice="";
     }
     try {
         switch (choice){
             case "create":
                 showAdd(request,response);
                 break;
             case "update":
                 showFromUpdate(request,response);
                 break;
             case "delete":
                 delete(request,response);
                 break;
             case "search":
                 seacrh(request,response);
                 break;
             default:
                 selectAll(request,response);
         }

     }catch (SQLException ex) {
         throw new ServletException(ex);
     }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice=request.getParameter("choice");
        if(choice==null){
            choice="";
        }

        try {
            switch (choice){
                case "create":
                     addEmployees(request,response);
                    break;
                case "update":
                    Update(request,response);
                    break;
                default:

            }

        }catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void selectAll(HttpServletRequest request,HttpServletResponse response)
    throws ServletException ,IOException, SQLException {
        List<Employee>employees=employeeDao.selectAllEmployees();
        request.setAttribute("employees",employees);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view/list.jsp");
        dispatcher.forward(request,response);
    }
    private void showAdd(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view/create.jsp");
        dispatcher.forward(request,response);
    }
    private void addEmployees(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,SQLException,IOException{
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String phone=request.getParameter("phone");
        double salary= Double.parseDouble(request.getParameter("salary"));
        String department=request.getParameter("department");
        Employee employee=new Employee(name,email,address,phone,salary,department);
        employeeDao.insert(employee);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view/list.jsp");
        dispatcher.forward(request,response);

    }
    private void showFromUpdate(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view/update.jsp");
        dispatcher.forward(request,response);
    }
    private void Update(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,SQLException,IOException{
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String phone=request.getParameter("phone");
        double salary= Double.parseDouble(request.getParameter("salary"));
        String pronamer=request.getParameter("proname");
        employeeDao.update(new Employee(name,email,address,phone,salary,pronamer));
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view/list.jsp");
        dispatcher.forward(request,response);
    }
    private void delete(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,SQLException,IOException{
        String id=request.getParameter("id");
        employeeDao.delete(id);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view/list.jsp");
        dispatcher.forward(request,response);
    }
    private void seacrh(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,SQLException,IOException{
        String name=request.getParameter("name");
        employeeDao.search(name);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/view/list.jsp");
        dispatcher.forward(request,response);
    }
}
