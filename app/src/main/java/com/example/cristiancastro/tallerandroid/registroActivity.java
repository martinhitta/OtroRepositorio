package com.example.cristiancastro.tallerandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import Dominio.Ahorcado;
import Dominio.UsuarioPublico;

public class registroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registro);
    }

    public void Enviar(View e)
    {
        if (ValidarCampos()) {
            TextView Nombre = (TextView) findViewById(R.id.txtNombre);
            TextView Contraseña = (TextView) findViewById(R.id.txtPass);
            TextView Usuario = (TextView) findViewById(R.id.txtUsuario);
            TextView Apellido = (TextView) findViewById(R.id.txtApellido);
            TextView Edad = (TextView) findViewById(R.id.txtEdad);
            TextView Email = (TextView) findViewById(R.id.txtEmail);
            TextView Nacionalidad = (TextView) findViewById(R.id.txtNacionalidad);
            Ahorcado ahorcado = new Ahorcado();
            UsuarioPublico up = new UsuarioPublico();

            up.setUsuarioU(Usuario.toString());

            if (ahorcado.existeUsuarioUsuarioPublico(up)) {
                Toast.makeText(getApplicationContext(), "Ya existe ese Usuario", Toast.LENGTH_SHORT).show();
            } else {
                up.setNacionalidadUP(Nacionalidad.toString());
                up.setEmailUP(Email.toString());
                up.setNombreU(Nombre.toString());
                up.setContraseñaU(Contraseña.toString());
                up.setApellidoU(Apellido.toString());
                up.setEdadUP(Integer.parseInt(Edad.toString()));

                ahorcado.guardarUsuarioPublico(up);
                Toast.makeText(getApplicationContext(), "Usuario Agregado", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Completar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean ValidarCampos()
    {
        return (findViewById(R.id.txtNombre).toString() != "" && findViewById(R.id.txtPass).toString() != "" && findViewById(R.id.txtUsuario).toString() != "" &&
                findViewById(R.id.txtApellido).toString() != "" && findViewById(R.id.txtEdad).toString() != "" && findViewById(R.id.txtEmail).toString() != "" && findViewById(R.id.txtNacionalidad).toString() != "");
    }
}