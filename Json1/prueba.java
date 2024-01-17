package Json1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class prueba {
    public static void main(String[] args) {
        Potencia potenciaFuente = new Potencia(750, "KW");
        FuenteAlimentacion fuente = new FuenteAlimentacion("Manker", "ATX", potenciaFuente);
        TarjetaGrafica grafica = new TarjetaGrafica("iAmanoob", "4080", "2 bitcoins", "Trillon cudas");
        DiscoDuro discoDuro1 =  new DiscoDuro("Manker", 1, "HDD", "2TB");
        DiscoDuro duscoDuro2 = new DiscoDuro("Rager", 2, "SDD", "500GB");
        Procesador procesador = new Procesador("Manker", "i9.5", "5Ghz");
        Ram ram1 =  new Ram("Flaming", 1, "DDR4", "8GB");
        Ram ram2 =  new Ram("Flaming", 2, "DDR4", "8GB");
        PlacaBase placaBase = new PlacaBase("B470", "iAmanoob", 4);
        Monitor monitor = new Monitor("4K feeder pro-max","Rager", "2160p");
        Raton raton = new Raton("Insultating pro 1", "Flamer Gaming SL");
        Teclado teclado = new Teclado("Flaming pro eco 2", "Flamer Gaming SL");
        List<periferico> listaPerifericos = new ArrayList<>();
        listaPerifericos.add(raton);
        listaPerifericos.add(teclado);
        List<Componente> listaComponentes = new ArrayList<>();
        listaComponentes.add(fuente);
        listaComponentes.add(grafica);
        listaComponentes.add(discoDuro1);
        listaComponentes.add(duscoDuro2);
        listaComponentes.add(procesador);
        listaComponentes.add(ram1);
        listaComponentes.add(ram2);
        listaComponentes.add(placaBase);
        listaComponentes.add(monitor);
        ordenador pc = new ordenador("Flameador 2024", 1900, listaPerifericos, listaComponentes);

        //Gson gson = new Gson();
        //ordenador pcdef = gson.fromJson(String.valueOf(pc), ordenador.class);
        System.out.println(pc.toString());


        /*File file = new File("PSP/src/Json1/ordenadores.json");

        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)){
            String json = br.readLine();
            Gson gson = new Gson();
            List<ordenador> pcs = gson.fromJson(json, new TypeToken<List<ordenador>>(){}.getType());
            for(ordenador p : pcs) {
                System.out.println(p);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
    }

