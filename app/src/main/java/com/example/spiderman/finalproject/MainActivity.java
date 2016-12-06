package com.example.spiderman.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;

import static android.R.attr.bitmap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView list;

    String[] namaPercetakan = {
            "Burjo Katineung",
            "Luxury Cafe",
            "Kolam Ikan",
            "Nasi Padang Sendowo",
            "Foodcourt UGM"
    };
    Integer[] fotoTempat = {
            R.drawable.amber_chocolate_bar,
            R.drawable.american_barbercue,
            R.drawable.branche_bistro,
            R.drawable.brunch_at_umabo,
            R.drawable.three_buns
    };
    String[] alamatPercetakan = {
            "Sendowo Yogyakarta",
            "Jalan Kaliurang KM 5",
            "Sendowo Blok B Yogyakarta",
            "Sendowo Blok C Yogyakarta",
            "Gelanggang UGM"
    };
    String[] detailTempat = {
            "Burjo Katineung adalah sebuah tempat makan dimana tersedia berbagai jenis makanan dan minuman. Burjo ini berasal dari tanah sunda" +
                    ".Tidak heran jika kita makan di burjo, pelayannya orang sunda semua atau sering disebut aak. burjo selalu ramai" +
                    "oleh mahasiswa-mahasiswa, tidak hanya mahasiswa warga biasa juga sering makan di burjo",
            "Luxury Cafe adalah sebuah cafe yang bertempat di jalan kaliurang km 5 yogyakarta. Luxury Cafe juga menyediakan wifi corner" +
                    " dan menjual berbagai jenis makanan dan minuman. Luxury Cafe juga sering dikunjungi oleh mahasiswa-mahasiswa." +
                    " Pada umumnya mahasiswa berkunjung untuk mengerjakan tugas, nongkrong bareng teman ataupun bermain game.",
            "Kolam Ikan adalah sebuah restaurant kecil yang bertempat di sendowo yogyakarta. Kolam Ikan terkenal dengan harga yang ekonomis " +
                    "tidak heran apabila kolam ikan ini selalu ramai dikunjungi oleh para mahasiswa maupun pegawai. Untuk cita rasa juga " +
                    "tidak kalah lezat...",
            "Nasi Padang Sendowo adalah sebuah rumah makan nasi padang yang bertempat di sendowo yogyakarta. Siapa yang tidak tahu dengan " +
                    "nasi padang dimana hampir ada di seluruh penjuru daerah indonesia dengan cita rasa makanannya yang khas dan tentunya lezat.",
            "FoodCourt UGM adalah sebuah tempat makan dimana tersedia berbagai jenis makanan dan minuman. Foodcourt selalu ramai apalagi jam-jam " +
                    "istirahat kuliah mahasiswa. Berbagai makanan dan minuman disajikan oleh para penjual dengan harga yang ekonomsi."

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CustomList adapter = new CustomList(this, namaPercetakan, alamatPercetakan, fotoTempat);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent keDetail = new Intent(MainActivity.this, DetailTempat.class);

                String nama    = ((TextView) view.findViewById(R.id.namaPercetakan)).getText().toString();
                String alamat    = ((TextView) view.findViewById(R.id.alamatPercetakan)).getText().toString();
                ImageView foto = (ImageView) findViewById(R.id.fotoTempat);

                Bundle TempatData = new Bundle();
                TempatData.putString("nama",nama);
                TempatData.putString("alamat",alamat);
                TempatData.putInt("foto",fotoTempat[+position]);
                TempatData.putString("detail",detailTempat[+position]);

                keDetail.putExtras(TempatData);
                startActivity(keDetail);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.kontak) {
            Intent intent = new Intent(MainActivity.this,Kontak.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
