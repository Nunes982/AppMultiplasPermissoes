package app.daazi.appmultiplaspermissoes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Recomendação
 *
 * Para o melhor entendimento desta aula, é importante você ter
 * entendido as aulas anteriores ou já tem um nível além do
 * iniciante.
 *
 * Este é um projeto que contém apenas o ESQUELETO que deverá
 * ser utilizado por você para acompanhar as próximas aulas
 * relacionadas com permissões múltiplas
 */
public class MainActivity extends AppCompatActivity {

    TextView txtPermissoes;
    private static final int REQUEST_PERMISSIONS_CODE = 100;

    private final String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.SEND_SMS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPermissoes = findViewById(R.id.txtPermissoes);

        checkAndRequestPermissions();

    }


    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissionsToRequest = new ArrayList<>();

            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(permission);
                }
            }

            if (!permissionsToRequest.isEmpty()) {
                ActivityCompat.requestPermissions(
                        this,
                        permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                        REQUEST_PERMISSIONS_CODE
                );
            } else {
                txtPermissoes.setText("Ok, todas as permissões ativas.");
                // Acessar as funcionalidades do aplicativo
            }
        } else {
            txtPermissoes.setText("Ok, todas as permissões ativas.");
            // Acessar as funcionalidades do aplicativo
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    txtPermissoes.append("\n\n" + permissions[i] + " Ativa");
                } else {
                    txtPermissoes.append("\n" + permissions[i] + " sem permissão");
                }
            }
        }
    }
}
