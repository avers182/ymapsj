package ru.sav.ymaps;

import org.junit.Test;
import ru.sav.ymaps.DAO.DAO;
import ru.sav.ymaps.DAO.HDAO;
import ru.sav.ymaps.model.Track;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTest {
    @Test
    public void listTracksTest() {
//        Track t = new Track();
//        t.setId(1l);
//        t.setName("name");
//        t.setDistance(100f);
//
//        List<Track> l = new ArrayList<>();
//        l.add(t);
//
//
//        DAO dao = mock(HDAO.class);
//        when(dao.listTracks()).thenReturn(l);
//
//        Service service = new Service();
//        service.setDao(dao);
//        String s = service.listTracks();
//
//        assertNotNull(s);
//        assertThat(s, containsString("<tr><td>name</td><td>100,000000</td><td>null</td><td>null</td></tr>"));
    }
}
