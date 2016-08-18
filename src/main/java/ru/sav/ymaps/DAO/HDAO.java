package ru.sav.ymaps.DAO;

import ru.sav.ymaps.model.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class HDAO implements DAO{
    private final Connection connection;
    private final Statement statement;

    public HDAO() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:mem:TracksDB");
        statement = connection.createStatement();
        statement.executeUpdate("create table ztrack(z_pk bigint, zdistance double, zname varchar, zstarttime timestamp, zendtime timestamp);");
        for (int i = 0; i < 50; i++ ) {
            statement.executeUpdate(String.format("insert into ztrack(z_pk, zdistance, zname, zstarttime, zendtime) values (%d, 100, '%d', now(), now())", i, i));
        }
//        System.out.println(new GregorianCalendar().toString() + " h2 init");
    }

    public synchronized List<Track> listTracks() {
        List<Track> tracks = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("select z_pk, zdistance, zendtime, zstarttime, zname from ztrack order by z_pk desc;");

            while (resultSet.next()) {
                Track t = new Track();

                t.setId(resultSet.getLong("z_pk"));
                t.setName(resultSet.getString("zname"));
                t.setDistance(resultSet.getFloat("zdistance"));
                t.setStartTimeS(resultSet.getString("zstarttime"));
                t.setEndTimeS(resultSet.getString("zendtime"));

                tracks.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tracks;
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
