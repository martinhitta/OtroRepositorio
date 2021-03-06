package Persistencia;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Dominio.Partida;

/**
 * Created by cristian castro on 04/08/2016.
 */
public class PerPartida extends SqlLite{
    public PerPartida(Context contexto) {
        super(contexto);
    }
    public boolean guardar(Partida p)
    {
        boolean retorno = false;
        if(p != null) {
            this.ejecutarSentencia("INSERT INTO Partida (IdUP,PuntajePartida,FechaPartidaN) VALUES (" + p.getIdUP() + ", " + p.getPuntajePartida() + ", '" + p.getFechaPartidaN() + "')");
            retorno = true;
        }
        return retorno;
    }
    public Partida SeleccionarEspecificas(String x) throws ParseException {
        Partida partida;
        this.seleccionar("SELECT * FROM Partida "+ "where IdUP = '"+ x + "'");
        if(this.c.isAfterLast() == false)
        {
            partida = new Partida();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
            partida.setFechaPartidaN(formato.parse(c.getString(2)));
            partida.setIdUP(c.getInt(0));
            partida.setIdPartida(c.getInt(3));
            partida.setPuntajePartida(c.getInt(1));
            this.c.moveToNext();
        }
        else
        {
            partida = null;
        }
        this.c.close();
        return partida;
    }
    public ArrayList<Partida> Partidas() throws ParseException {
        ArrayList<Partida> Partidas = new ArrayList<Partida>();
        Partida Partida;
        //Loretornado se asigna al cursor que se encuentra en SQLite
        this.seleccionar("SELECT * FROM Partida");
        //Recorremos el cursor y agregamos cada elemento a un ArrayList
        while(c.isAfterLast() == false)
        {
            Partida = new Partida();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
            Partida.setFechaPartidaN(formato.parse(c.getString(2)));
            Partida.setIdUP(c.getInt(0));
            Partida.setIdPartida(c.getInt(3));
            Partida.setPuntajePartida(c.getInt(1));
            Partidas.add(Partida);
            this.c.moveToNext();
        }
        this.c.moveToNext();
        return Partidas;
    }
}
