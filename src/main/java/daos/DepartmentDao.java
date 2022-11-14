package daos;

import beans.Department;
import beans.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartmentDao extends BaseDao{
    public ArrayList<Department> obtenerDepartamentos(){

        ArrayList<Department> listaDepartamentos = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs= stm.executeQuery("SELECT * FROM departments")){
            while (rs.next()){
                Department department = new Department();
                department.setDepartmentId(rs.getInt("department_id"));
                department.setDepartmentName(rs.getString("department_name"));
                department.setLocationId(rs.getInt("location_id"));

                Employee m = new Employee();
                m.setEmployeeId(rs.getInt("manager_id"));
                department.setManager(m);
                listaDepartamentos.add(department);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaDepartamentos;
    }
}
