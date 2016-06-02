package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBManager implements AutoCloseable {

    private Connection connection;

    public DBManager() throws SQLException, NamingException {
        connect();
    }

    private void connect() throws SQLException, NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/Supermercado");
        connection = ds.getConnection();
    }

    /**
     * Close the connection to the database if it is still open.
     *
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    public Cliente getCliente(String email, String pass) throws SQLException {
        Cliente cliente = null;
        String q = "SELECT id, correo, nombre FROM Clientes WHERE correo = ? AND pass = ?";
        int id;
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setString(1, email);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setCorreo(rs.getString("correo"));
            }
            return cliente;
        }
    }

    public List<Producto> getProducto(String nombre) throws SQLException {
        List<Producto> productos = new ArrayList<Producto>();
        String q = "SELECT Productos.id, Tipo_producto.nombre, denominacion, Marcas.nombre"
                + " FROM Productos INNER JOIN Tipo_producto ON tipo_producto ="
                + " Tipo_producto.id INNER JOIN Marcas ON marca = Marcas.id"
                + " WHERE Tipo_producto.nombre = ?";

        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("Tipo_producto.nombre"));
                producto.setMarca(rs.getString("Marcas.nombre"));
                producto.setDenominacion(rs.getString("denominacion"));
                productos.add(producto);
            }
            return productos;
        }
    }

    public Producto getProducto(int idProducto) throws SQLException {
        Producto producto = new Producto();
        String q = "SELECT id, denominacion FROM Productos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setDenominacion(rs.getString("denominacion"));
            }
            return producto;
        }
    }

    public List<Producto> getLista() throws SQLException {
        List<Producto> productos = new ArrayList<Producto>();
        String q = "SELECT Productos.id, tipo_cantidad, cantidad, envase, denominacion FROM Cantidades"
                + " INNER JOIN Presentaciones ON id_presentacion = Presentaciones.id INNER JOIN Productos"
                + " ON Productos.id = Presentaciones.producto";

        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setTipo(rs.getString("tipo_cantidad"));
                producto.setCantidad(rs.getString("cantidad"));
                producto.setEnvase(rs.getString("envase"));
                producto.setDenominacion(rs.getString("denominacion"));
                productos.add(producto);
            }
            return productos;
        }
    }

    public List<Producto> getPresentaciones(int idProducto) throws SQLException {
        List<Producto> productos = new ArrayList<Producto>();
        String q = "SELECT Productos.id, Presentaciones.id, denominacion, envase, cantidad,"
                    + " Precios.precio, Marcas.nombre, stock FROM Presentaciones INNER JOIN Productos"
                    + " ON producto=Productos.id INNER JOIN Tipo_producto ON Tipo_producto.id ="
                    + " Productos.tipo_producto INNER JOIN Cantidades ON Presentaciones.id = id_presentacion"
                    + " INNER JOIN Precios ON Precios.id_presentacion=Presentaciones.id INNER JOIN Marcas"
                    + " ON marca = Marcas.id WHERE producto = ? AND (fecha_inicio < NOW() AND fecha_fin > NOW())";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("Productos.id"));
                producto.setIdPresentacion(rs.getInt("Presentaciones.id"));
                producto.setStock(rs.getInt("stock"));
                producto.setHayStock(rs.getInt("stock") != 0);
                producto.setDenominacion(rs.getString("denominacion"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setMarca(rs.getString("Marcas.nombre"));
                producto.setEnvase(rs.getString("envase"));
                producto.setCantidad(rs.getString("cantidad"));
                productos.add(producto);

            }
            return productos;
        }
    }

    public List<Carro> getCarro(int idCliente) throws SQLException {
        List<Carro> carros = new ArrayList<Carro>();
        String q = " SELECT denominacion, Carro.id_presentacion, unidades, precio FROM Carro"
                 + " INNER JOIN Presentaciones ON id_presentacion = Presentaciones.id"
                 + " INNER JOIN Productos ON producto = Productos.id INNER JOIN Precios"
                 + " ON Precios.id_presentacion = Presentaciones.id WHERE id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setProducto(rs.getString("denominacion"));
                carro.setIdPresentacion(rs.getInt("id_presentacion"));
                carro.setUnidades(rs.getInt("unidades"));
                carro.setPrecio(rs.getFloat("precio"));
                carros.add(carro);

            }
            return carros;
        }

    }

    public List<Compra> getCompra(int idCliente) throws SQLException {
        List<Compra> compras = new ArrayList<Compra>();
        String q = "SELECT * FROM Compra WHERE id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCliente(rs.getInt("id_cliente"));
                compra.setIdRecogida(rs.getInt("id_recogida"));
                compra.setImporte(rs.getFloat("importe"));
                compra.setFechaCompra(rs.getDate("fecha"));
                compra.setFechaRecogida(rs.getDate("recogida"));
                compras.add(compra);

            }
            return compras;
        }
    }

    public boolean existeCarro(int idCliente, int idPresentacion) throws SQLException {
        boolean existe = false;
        String q = "SELECT * FROM Carro WHERE id_cliente = ? AND id_presentacion = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idPresentacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            return existe;
        }
    }


    public boolean existeCarro(int idCliente) throws SQLException {
        boolean existe = false;
        String q = "SELECT * FROM Carro WHERE id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            return existe;
        }
    }

    public boolean anyadirCarro(int idCliente, int idPresentacion, int unidades) throws SQLException {
        boolean success = false;
        connection.setAutoCommit(false);
        if (!existeCarro(idCliente, idPresentacion)) {
            String q1 = "INSERT INTO Carro (id_cliente, id_presentacion, unidades) "
                        + "VALUES (?, ?, ?)";
            try (PreparedStatement stmt1 = connection.prepareStatement(q1)) {
                stmt1.setInt(1, idCliente);
                stmt1.setInt(2, idPresentacion);
                stmt1.setInt(3, unidades);
                int numRows = stmt1.executeUpdate();
                if (numRows > 0) {
                    success = true;
                }
            } finally {
                if (success) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            }
            return success;

        } else {
            String q1 = "UPDATE Carro SET unidades = unidades+? WHERE id_presentacion = ? AND id_cliente = ?";
            try (PreparedStatement stmt1 = connection.prepareStatement(q1)) {
                stmt1.setInt(1, unidades);
                stmt1.setInt(2, idPresentacion);
                stmt1.setInt(3, idCliente);
                int numRows = stmt1.executeUpdate();
                if (numRows > 0) {
                    success = true;
                }
            } finally {
                if (success) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            }
            return success;
        }
    }

    public boolean existeCliente(String correo) throws SQLException {
        boolean existe = false;
        String q = "SELECT * FROM Clientes WHERE correo = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            return existe;
        }
    }

    public boolean registrarUsuario(String correo, String pass, String nombre, String salt) throws SQLException {
        boolean success = false;
        connection.setAutoCommit(false);
        String q1 = "INSERT INTO Clientes (correo, pass, nombre, salt) "
                    + "VALUES (?, ?, ?, ?)";

        if (existeCliente(correo)) {
            return false;
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(q1)) {
            stmt.setString(1, correo);
            stmt.setString(2, pass);
            stmt.setString(3, nombre);
            stmt.setString(4, salt);
            int numRows = stmt.executeUpdate();
            if (numRows > 0) {
                success = true;
            }
        } finally {
            if (success) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.setAutoCommit(true);
        }
        return success;
    }

    public boolean vaciarCarro(int idCliente) throws SQLException {
        boolean success = false;
        connection.setAutoCommit(false);
        String q = "DELETE FROM Carro WHERE id_cliente = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idCliente);
            int numRows = stmt.executeUpdate();
            if (numRows > 0 && !existeCarro(idCliente)) {
                success = true;
            }

            } finally {
                if (success) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            }
            return success;
    }

    public List<Recogida> getLugares(int idCliente) throws SQLException {
        List<Recogida> recogidas = new ArrayList<Recogida>();
        String q = "SELECT * FROM Recogida";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Recogida recogida = new Recogida();
                recogida.setId(rs.getInt("id"));
                recogida.setLugar(rs.getString("lugar"));
                recogidas.add(recogida);

            }
            return recogidas;
        }
    }

    public float precioCarro(Cliente cliente) throws SQLException {
        float precio = 0;
        List<Carro> carros = getCarro(cliente.getId());
        
        if(!carros.isEmpty()) {
            for(Carro carro: carros){
                precio += carro.getPrecio()*carro.getUnidades();
            }
        }
        return precio;
    }

    public boolean vender(int idCliente, int lugar, float precio, int fecha) throws SQLException {
        List<Carro> carros = getCarro(idCliente);

        boolean success = false;
        connection.setAutoCommit(false);
        String q1 = "INSERT INTO Compra (id_cliente, id_recogida, importe, fecha, recogida) "
                    + "VALUES (?, ?, ?, NOW(), NOW() + INTERVAL ? DAY)";
        String q2 = "UPDATE Presentaciones INNER JOIN Precios ON id_presentacion = Presentaciones.id"
                    + " SET stock = stock - ? WHERE id = ? AND (stock >= ? AND fecha_fin > NOW())";
        String q3 = "INSERT INTO Compras_registradas(id_compra, id_presentacion, unidades)"
                    + " SELECT (SELECT id FROM Compra WHERE id_cliente = ? AND fecha = (SELECT MAX(fecha) FROM Compra WHERE id_cliente = ?)),"
                    + " id_presentacion, unidades FROM Carro WHERE id_cliente = ?";
         String q4 = "DELETE FROM Carro WHERE id_cliente = ?";
        try (PreparedStatement stmt1 = connection.prepareStatement(q1);
             PreparedStatement stmt2 = connection.prepareStatement(q2);
             PreparedStatement stmt3 = connection.prepareStatement(q3);
             PreparedStatement stmt4 = connection.prepareStatement(q4)) {
            stmt1.setInt(1, idCliente);
            stmt1.setInt(2, lugar);
            stmt1.setFloat(3, precio);
            stmt1.setInt(4, fecha);
            int numRows = stmt1.executeUpdate();
            if (numRows > 0) {
                success = true;
            }

            int i = 1;
            for (Carro carro: carros) {
                stmt2.setInt(1, carro.getUnidades());
                stmt2.setInt(2, carro.getIdPresentacion());
                stmt2.setInt(3, carro.getUnidades());
                numRows = stmt2.executeUpdate();
                if (numRows == 0) {
                    success = false;
                }
            }

            stmt3.setInt(1, idCliente);
            stmt3.setInt(2, idCliente);
            stmt3.setInt(3, idCliente);
            numRows = stmt3.executeUpdate();

            if (success && numRows > 0) {
                success = true;
            }

            stmt4.setInt(1,idCliente);
            numRows = stmt4.executeUpdate();

            if (success && numRows > 0) {
                success = true;
            }
        } finally {
            if (success) {
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.setAutoCommit(true);
        }
        return success;
    }

    public List<Resumen> getResumen(int idCliente) throws SQLException {
        List<Resumen> resumenes = new ArrayList<Resumen>();
        String q = "SELECT unidades, recogida, lugar, denominacion, precio,"
                + " importe, Marcas.nombre FROM Compras_registradas INNER JOIN Compra"
                + " ON Compra.id = id_compra INNER JOIN Recogida ON Recogida.id = id_recogida"
                + " INNER JOIN Presentaciones ON Presentaciones.id = id_presentacion INNER JOIN"
                + " Productos ON Productos.id = producto INNER JOIN Tipo_producto ON Tipo_producto.id"
                + " = tipo_producto INNER JOIN Precios ON Precios.id_presentacion = Presentaciones.id"
                + " INNER JOIN Marcas ON Marcas.id = marca WHERE id_cliente = ? AND id_compra = (SELECT MAX(id)"
                + " FROM Compra INNER JOIN Compras_registradas ON id_compra = Compra.id WHERE id_cliente = ?) AND (fecha_inicio < NOW() AND fecha_fin > NOW())";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Resumen resumen = new Resumen();
                resumen.setUnidades(rs.getInt("unidades"));
                resumen.setFechaRecogida(rs.getDate("recogida"));
                resumen.setLugar(rs.getString("lugar"));
                resumen.setNombreProducto(rs.getString("denominacion"));
                resumen.setNombreMarca(rs.getString("Marcas.nombre"));
                resumen.setPrecio(rs.getFloat("precio"));
                resumen.setImporte(rs.getFloat("importe"));
                resumenes.add(resumen);

            }
            return resumenes;
        }
    }

    public String getSalt(String email) throws SQLException {
        String salt = "";
        String q = "SELECT salt FROM Clientes WHERE correo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                salt = rs.getString("salt");
            }
            return salt;
        }
    }

    public List<Integer> getAgotados(int idCliente) throws SQLException {
        List<Integer> presentaciones = new ArrayList<Integer>();
        String q = "SELECT Carro.id_presentacion from Carro INNER JOIN Presentaciones"
                + " ON id_presentacion = Presentaciones.id INNER JOIN Precios ON Presentaciones.id"
                + " = Precios.id_presentacion  WHERE (unidades > stock OR fecha_fin < NOW()) AND id_cliente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer presentacion= rs.getInt("id_presentacion");
                presentaciones.add(presentacion);
            }
            return presentaciones;
        }

    }

    public boolean eliminarAgotados(int idCliente, List<Integer> agotados) throws SQLException {
        boolean success = false;
        connection.setAutoCommit(false);
        String q = "DELETE FROM Carro WHERE id_cliente = ? AND id_presentacion = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(q)) {
            stmt.setInt(1, idCliente);
            int numRows;
            for (Integer agotado:agotados) {
                stmt.setInt(2, agotado);
                numRows = stmt.executeUpdate();
                if (numRows > 0) {
                    success = true;
                }
            }

            } finally {
                if (success) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            }
            return success;
    }

}
