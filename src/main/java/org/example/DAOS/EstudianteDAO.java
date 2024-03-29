package org.example.DAOS;

import org.example.DTOS.EstudianteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    private Connection connection;

    public EstudianteDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(EstudianteDTO estudiante) {
        try {
            String query = "INSERT INTO Estudiantes (nombre, apellido, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, estudiante.getNombre());
            statement.setString(2, estudiante.getApellido());
            statement.setString(3, estudiante.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(EstudianteDTO estudiante) {
        try {
            String query = "UPDATE Estudiantes SET nombre=?, apellido=?, email=? WHERE id_estudiante=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, estudiante.getNombre());
            statement.setString(2, estudiante.getApellido());
            statement.setString(3, estudiante.getEmail());
            statement.setInt(4, estudiante.getIdEstudiante());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idEstudiante) {
        try {
            String query = "DELETE FROM Estudiantes WHERE id_estudiante=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EstudianteDTO getEstudianteById(int idEstudiante) {
        EstudianteDTO estudiante = null;
        try {
            String query = "SELECT * FROM Estudiantes WHERE id_estudiante=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                estudiante = new EstudianteDTO();
                estudiante.setIdEstudiante(resultSet.getInt("id_estudiante"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setApellido(resultSet.getString("apellido"));
                estudiante.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiante;
    }

    public List<EstudianteDTO> getAllEstudiantes() {
        List<EstudianteDTO> estudiantes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Estudiantes";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EstudianteDTO estudiante = new EstudianteDTO();
                estudiante.setIdEstudiante(resultSet.getInt("id_estudiante"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setApellido(resultSet.getString("apellido"));
                estudiante.setEmail(resultSet.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }
}
