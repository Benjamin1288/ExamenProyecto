package org.example.DAOS;

import org.example.DTOS.CursoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(CursoDTO curso) {
        try {
            String query = "INSERT INTO Cursos (nombre_curso, profesor) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, curso.getNombreCurso());
            statement.setString(2, curso.getProfesor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(CursoDTO curso) {
        try {
            String query = "UPDATE Cursos SET nombre_curso=?, profesor=? WHERE id_curso=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, curso.getNombreCurso());
            statement.setString(2, curso.getProfesor());
            statement.setInt(3, curso.getIdCurso());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idCurso) {
        try {
            String query = "DELETE FROM Cursos WHERE id_curso=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCurso);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CursoDTO getCursoById(int idCurso) {
        CursoDTO curso = null;
        try {
            String query = "SELECT * FROM Cursos WHERE id_curso=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCurso);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                curso = new CursoDTO();
                curso.setIdCurso(resultSet.getInt("id_curso"));
                curso.setNombreCurso(resultSet.getString("nombre_curso"));
                curso.setProfesor(resultSet.getString("profesor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    public List<CursoDTO> getAllCursos() {
        List<CursoDTO> cursos = new ArrayList<>();
        try {
            String query = "SELECT * FROM Cursos";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CursoDTO curso = new CursoDTO();
                curso.setIdCurso(resultSet.getInt("id_curso"));
                curso.setNombreCurso(resultSet.getString("nombre_curso"));
                curso.setProfesor(resultSet.getString("profesor"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}
