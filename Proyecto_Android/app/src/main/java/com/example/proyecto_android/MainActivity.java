package com.example.proyecto_android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NuevoUsuarioFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_NuevoUsuario);
            }
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_NuevoUsuario:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NuevoUsuarioFragment()).commit();
                break;
            case R.id.nav_ListarUsuarios:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListarUsuariosFragment()).commit();
                break;
            case R.id.nav_ModificarUsuarios:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ModificarUsuarioFragment()).commit();
                break;
            case R.id.nav_EliminarUsuarios:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EliminarUsuarioFragment()).commit();
                break;
            case R.id.nav_NuevoAlimento:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NuevoAlimentoFragment()).commit();
                break;
            case R.id.nav_ListarAlimentos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListarAlimentosFragment()).commit();
                break;
            case R.id.nav_ModificarAlimentos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ModificarAlimentoFragment()).commit();
                break;
            case R.id.nav_ConsumodeAlimentos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConsumosdeAlimentosFragment()).commit();
                break;
            case R.id.nav_GananciadePeso:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GananciadePesoFragment()).commit();
                break;
            case R.id.nav_EventosClinicos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventosClinicosFragment()).commit();
                break;
            case R.id.nav_Exit:
                Intent intent = new Intent(this, com.example.proyecto_android.MainLogin.class);
                startActivity(intent);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                super.onBackPressed();
            }
        }

    }

