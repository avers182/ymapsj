package ru.sav.ymaps.DAO;

import ru.sav.ymaps.model.Track;

import java.sql.*;
import java.util.*;

public class SqliteDAO implements DAO {
    private final Connection connection;
    private final Statement statement;
//    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SqliteDAO(String connectionString) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(connectionString);
        statement = connection.createStatement();
    }

    public synchronized List<Track> listTracks() {
        List<Track> tracks = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("select z_pk, zdistance, datetime(zendtime + 978307200.0, 'unixepoch', 'localtime') as zendtime, datetime(zstarttime + 978307200.0, 'unixepoch', 'localtime') as zstarttime, zname from ztrack order by z_pk desc;");

            while (resultSet.next()) {
                Track t = new Track();

                t.setId(resultSet.getLong("z_pk"));
                t.setName(resultSet.getString("zname"));
                t.setDistance(resultSet.getFloat("zdistance"));
//                    try {
//                        t.setStartTime(fmt.parse(resultSet.getString("zstarttime")));
//                        t.setEndTime(fmt.parse(resultSet.getString("zendtime")));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                t.setStartTimeS(resultSet.getString("zstarttime"));
                t.setEndTimeS(resultSet.getString("zendtime"));

                tracks.add(t);
            }

            resultSet.close();
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
