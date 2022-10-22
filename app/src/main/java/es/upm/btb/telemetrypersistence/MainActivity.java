package es.upm.btb.telemetrypersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String classname = "MainActivity";
    ListView lvListadoCSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvListadoCSV = findViewById(R.id.listadoCSV);

        // Datos csv
        List<String[]> rows = new ArrayList<>();
        CSVReader csvReader = new CSVReader(this, "telemetrias.csv");
        ArrayList<String> lst = new ArrayList<String>();
        try {
            rows = csvReader.readCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < rows.size()-1; i++) {
            // En este caso recogemos los tres primeros campos
            String sRow = String.format("Fila %s: [%s | %s | %s]", i, rows.get(i)[0], rows.get(i)[1], rows.get(i)[2]);
            lst.add(sRow);
            Log.d(classname, sRow);
        }

        ArrayAdapter<String> adapterCSV = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lst);
        lvListadoCSV.setAdapter(adapterCSV);

    }
}