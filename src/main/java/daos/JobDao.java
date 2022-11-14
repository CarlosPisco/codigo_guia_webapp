package daos;

import beans.Job;

import java.sql.*;
import java.util.ArrayList;

public class JobDao extends BaseDao{

    public ArrayList<Job> obtenerTrabajos(){
        ArrayList<Job> listaTrabajos = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM jobs")){
            while(rs.next()){
                Job job = new Job();
                job.setJobId(rs.getString("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setMinSalary(rs.getInt("min_salary"));
                job.setMaxSalary(rs.getInt("max_salary"));
                listaTrabajos.add(job);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaTrabajos;
    }

    public void crearTrabajo(String jobId, String jobTitle, int minSalary, int maxSalary){

        String sql = "INSERT INTO jobs(job_id, job_title, min_salary, max_salary) VALUES (?, ?, ?, ?)";
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, jobId);
            pstm.setString(2, jobTitle);
            pstm.setInt(3, minSalary);
            pstm.setInt(4, maxSalary);

            pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Job obtenerTrabajo(String id){

        String sql = "SELECT * FROM jobs WHERE job_id = ?";
        Job job = null;
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, id);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                job = new Job();
                job.setJobId(rs.getString(1));
                job.setJobTitle(rs.getString(2));
                job.setMinSalary(rs.getInt(3));
                job.setMaxSalary(rs.getInt(4));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return job;
    }

    public void editarTrabajo(String jobId, String jobTitle, int minSalary, int maxSalary){

        String sql = "UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? WHERE job_id = ?";
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, jobTitle);
            pstm.setInt(2, minSalary);
            pstm.setInt(3, maxSalary);
            pstm.setString(4, jobId);

            pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void eliminarTrabajo(String jobId){

        String sql = "DELETE FROM jobs WHERE job_id = ?";
        try(Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, jobId);

            pstm.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
