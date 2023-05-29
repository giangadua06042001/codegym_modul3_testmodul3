package dao;

import module.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEmployee implements IEmployee<Employee>{

    private String jdbcURL = "jdbc:mysql://localhost:3306/employee?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "06042001";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insert(Employee employee) throws SQLException {
     String INSERT_INTO_EMPLOYEES="insert into employees(e_name,e_email,address,phone,salary,department) values(?,?,?,?,?,?);";
     try(Connection connection=getConnection();
         PreparedStatement statement=connection.prepareStatement(INSERT_INTO_EMPLOYEES)) {
         statement.setString(1,employee.getName());
         statement.setString(2,employee.getAddress());
         statement.setString(3,employee.getAddress());
         statement.setString(4,employee.getPhone());
         statement.setDouble(5,employee.getSalary());
         statement.setString(6,employee.getDepartment());
         System.out.println(statement);
         statement.executeUpdate();

     }catch (SQLException e){
         printSQLException(e);
     }
    }

    @Override
    public Employee select(String id) {
        String SELECT_EMPLOYEE_BY_ID="select*from employees where id=?";
        Employee employee=null;
        try (Connection connection=getConnection();
        PreparedStatement statement=connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            statement.setString(1,id);
            System.out.println(statement);
            ResultSet set=statement.executeQuery();
            while (set.next()){
                String name=set.getString("e_name");
                String email=set.getString("e_email");
                String address=set.getString("address");
                String phone=set.getString("phone");
                double salary=set.getDouble("salary");
                String department=set.getString("department");
                employee =new Employee(id,name,email,address,phone,salary,department);
            }

        }catch (SQLException ex){
            printSQLException(ex);
        }
        return employee;

    }


    @Override
    public boolean update(Employee employee) throws SQLException {
        boolean checkRow;
        String update_FROM_EMPLOYEES="update employees set e_name=?,e_email=?,address=?,phone=?,salary=?, department=? where id=?;";
        try  (Connection connection=getConnection();
        PreparedStatement statement=connection.prepareStatement( update_FROM_EMPLOYEES)){
            System.out.println(statement);
            statement.setString(1,employee.getName());
            statement.setString(2,employee.getEmail());
            statement.setString(3,employee.getAddress());
            statement.setString(4,employee.getPhone());
            statement.setDouble(5,employee.getSalary());
            statement.setString(6,employee.getDepartment());
            checkRow=statement.executeUpdate()>0;

        }
        return checkRow;
    }



    @Override
    public boolean delete(String id) throws SQLException {
        String DELETE_EMPLOYEES_BY_ID="DELETE FROM  employees WHERE id=?";
        boolean checkRow;
        try (Connection connection=getConnection();
        PreparedStatement statement=connection.prepareStatement(DELETE_EMPLOYEES_BY_ID)) {
            statement.setString(1,id);
            checkRow=statement.executeUpdate()>0;
        }
        return checkRow;
    }
    public List<Employee>selectAllEmployees(){
        String SELECT_FROM_EMPLOYEES="SELECT * FROM employees";
        List<Employee>list=new ArrayList<>();
        try (Connection connection=getConnection();
        PreparedStatement statement=connection.prepareStatement(SELECT_FROM_EMPLOYEES)) {
            System.out.println(statement);
            ResultSet set=statement.executeQuery();
            while (set.next()){
                String id=set.getString("id");
                String name=set.getString("e_name");
                String email=set.getString("e_email");
                String address=set.getString("address");
                String phone=set.getString("phone");
                double salary=set.getDouble("salary");
                String departmen=set.getString("department ");
                list.add(new Employee(id,name,email,address,phone,salary,departmen));
            }

        }
        catch (SQLException ex){
            printSQLException(ex);
        }
        return list;
    }
    public List<Employee>search(String name){
        String SEARCH_FROM_EMPLOYEES_BY_NAME="select*from employees where e_name like'%?%';";
        List<Employee>list=new ArrayList<>();
        try (Connection connection=getConnection();
        PreparedStatement statement=connection.prepareStatement(SEARCH_FROM_EMPLOYEES_BY_NAME)) {
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                String name1=resultSet.getString("e_name");
                String email=resultSet.getString("e_email");
                String address=resultSet.getString("address");
                String phone=resultSet.getString("phone");
                double salary=resultSet.getDouble("salary");
                String department=resultSet.getString("department");
              list.add(new Employee(name1,email,address,phone,salary,department));
            }

        }catch (SQLException ex){
            printSQLException(ex);
        }
        return list;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
