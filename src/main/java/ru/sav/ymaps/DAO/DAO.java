package ru.sav.ymaps.DAO;

import ru.sav.ymaps.model.Track;

import java.util.List;

public interface DAO {
    List<Track> listTracks();
    void close();
}
