package daos;

import beans.Department;
import beans.Employee;
import beans.Job;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao extends BaseDao{

    public ArrayList<Employee> obtenerEmpleados() {
        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        String sql = "select * from employees e\n" +
                    "inner join jobs j on e.job_id = j.job_id\n" +
                    "left join employees m on e.manager_id = m.employee_id\n" +
                    "left join departments d on e.department_id = d.department_id " +
                    "ORDER BY e.employee_id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmployeeId(rs.getInt(1));
                e.setFirstName(rs.getString(2));
                e.setLastName(rs.getString(3));
                e.setEmail(rs.getString(4));
                e.setPhoneNumber(rs.getString(5));
                e.setHireDate(rs.getString(6));

                Job job = new Job();
                job.setJobId(rs.getString("j.job_id"));
                job.setJobTitle(rs.getString("j.job_title"));
                e.setJob(job);

                e.setSalary(rs.getBigDecimal(8));
                e.setCommissionPct(rs.getBigDecimal(9));

                Employee manager = new Employee();
                manager.setEmployeeId(rs.getInt("m.employee_id"));
                manager.setFirstName(rs.getString("m.first_name"));
                manager.setLastName(rs.getString("m.last_name"));
                e.setManager(manager);

                Department department = new Department();
                department.setDepartmentId(rs.getInt("d.department_id"));
                department.setDepartmentName(rs.getString("d.department_name"));
                e.setDepartment(department);

                listaEmpleados.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    public Employee obtenerEmpleado(int id) {
        Employee e = null;

        String sql = "select *\n" +
                    "from employees e\n" +
                    "inner join jobs j on e.job_id = j.job_id\n" +
                    "left join employees m on e.manager_id = m.employee_id\n" +
                    "left join departments d on e.department_id = d.department_id\n" +
                    "where e.employee_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    e = new Employee();
                    e.setEmployeeId(rs.getInt(1));
                    e.setFirstName(rs.getString(2));
                    e.setLastName(rs.getString(3));
                    e.setEmail(rs.getString(4));
                    e.setPhoneNumber(rs.getString(5));
                    e.setHireDate(rs.getString(6));

                    Job job = new Job();
                    job.setJobId(rs.getString("j.job_id"));
                    job.setJobTitle(rs.getString("j.job_title"));
                    e.setJob(job);

                    e.setSalary(rs.getBigDecimal(8));
                    e.setCommissionPct(rs.getBigDecimal(9));

                    Employee manager = new Employee();
                    manager.setEmployeeId(rs.getInt("m.employee_id"));
                    manager.setFirstName(rs.getString("m.first_name"));
                    manager.setLastName(rs.getString("m.last_name"));
                    e.setManager(manager);

                    Department department = new Department();
                    department.setDepartmentId(rs.getInt("d.department_id"));
                    department.setDepartmentName(rs.getString("d.department_name"));
                    e.setDepartment(department);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public void guardarEmpleado(Employee e) {

        String sql = "INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)\n" +
                "VALUES (?, ?, ?, ?, now(), ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, e.getFirstName());
            pstmt.setString(2, e.getLastName());
            pstmt.setString(3, e.getEmail());
            pstmt.setString(4, e.getPhoneNumber());
            pstmt.setString(5, e.getJob().getJobId());
            pstmt.setBigDecimal(6, e.getSalary());
            pstmt.setBigDecimal(7, e.getCommissionPct());
            pstmt.setInt(8,e.getManager().getEmployeeId());
            pstmt.setInt(9, e.getDepartment().getDepartmentId());

            pstmt.executeUpdate();

        } catch (SQLException ee) {
            ee.printStackTrace();
        }

    }

    public void actualizarEmpleado(Employee e) {
        Employee employee = null;

        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone_number = ?, " +
                "job_id = ?, salary = ?, commission_pct = ?, manager_id = ?, department_id = ? WHERE (employee_id = ?);";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, e.getFirstName());
            pstmt.setString(2, e.getLastName());
            pstmt.setString(3, e.getEmail());
            pstmt.setString(4, e.getPhoneNumber());
            pstmt.setString(5, e.getJob().getJobId());
            pstmt.setBigDecimal(6, e.getSalary());
            pstmt.setBigDecimal(7, e.getCommissionPct());
            pstmt.setInt(8,e.getManager().getEmployeeId());
            pstmt.setInt(9, e.getDepartment().getDepartmentId());
            pstmt.setInt(10, e.getEmployeeId());

            pstmt.executeUpdate();

        } catch (SQLException ee) {
            ee.printStackTrace();
        }

    }

    public void eliminarEmployee(int id) {
        Employee employee = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("delete from employees where employee_id=?")) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Employee ingresarLogin(String user, String pass) {
        Employee employee = null;
        String sql = "SELECT * FROM employees_credentials WHERE email = ? AND sha2(?,256) = password_hashed";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt(1);
                    employee = obtenerEmpleado(id);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employee;
    }
}
