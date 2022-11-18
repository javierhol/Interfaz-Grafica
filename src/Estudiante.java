import javax.swing.*;
import java.sql.*;

public class Estudiante extends JFrame {
    public Estudiante() {
        ConsultarBt.addActionListener(e -> {
            try {
                listar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }});
        InsertarBt.addActionListener(e -> {
            try {
                insert();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    JPanel panel;
    private JTextField IdText;
    private JTextField NombreText;
    private JTextField ApellidoText;
    private JTextField EdadText;
    private JTextField TelText;
    private JTextField CursoText;
    private JButton InsertarBt;
    private JButton ConsultarBt;
    private JList Lista;
    Connection conn;
    PreparedStatement p;
    Statement s;
    ResultSet rs;
    DefaultListModel model = new DefaultListModel();

    public void listar() throws SQLException {
        conectar();
        Lista.setModel(model);
        s= conn.createStatement();
        rs= s.executeQuery("SELECT * FROM estudiante");
        model.removeAllElements();
        while (rs.next()) {
            model.addElement(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }

    }

    public void insert() throws SQLException {
        conectar();
        p = conn.prepareStatement("INSERT INTO estudiante VALUES (?, ?, ?, ?, ?, ?)");
        p.setInt(1, Integer.parseInt(IdText.getText()));
        p.setString(2, NombreText.getText());
        p.setString(3, ApellidoText.getText());
        p.setInt(4, Integer.parseInt(EdadText.getText()));
        p.setString(5, TelText.getText());
        p.setString(6, CursoText.getText());

        if (p.executeUpdate() > 0) {
            Lista.setModel(model);
            model.removeAllElements();
            model.addElement("Registro insertado");

            IdText.setText("");
            NombreText.setText("");
            ApellidoText.setText("");
            EdadText.setText("");
            TelText.setText("");
            CursoText.setText("");
        }
    }
    public void conectar() {
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/estudiante", "root", "");
            System.out.println("Conectado");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


