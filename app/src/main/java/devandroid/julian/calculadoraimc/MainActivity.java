package devandroid.julian.calculadoraimc;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import devandroid.julian.calculadoraimc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String edit_peso;
    private String edit_altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnCalcular.setOnClickListener(view -> {

            edit_peso = binding.editPeso.getText().toString();
            edit_altura = binding.editAltura.getText().toString();

            if (edit_peso.isEmpty()){
                binding.editPeso.setError("Digite seu Peso");
            }else if (edit_altura.isEmpty()) {
                binding.editAltura.setError("Digite sua Altura");
            }else {
                calcularImc();
            }

        });


    }

    private void calcularImc() {
        float imc;
        float pesoConvertido;
        float alturaConvertida;

        pesoConvertido = Float.parseFloat(edit_peso.replace(",","."));
        alturaConvertida = Float.parseFloat(edit_altura.replace(",","."));
        imc = pesoConvertido / (alturaConvertida * alturaConvertida);

        DecimalFormat df = new DecimalFormat("0.00");

        if (imc < 18.5){
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc).replace(".",",")+"\nPeso Baixo");
        } else if (imc > 18.5 && imc < 24.9) {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc).replace(".",",")+"\nPeso Normal");
        } else if (imc > 25.0 && imc < 29.9) {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc).replace(".",",")+"\nSobrepeso");
        } else if (imc > 30.0 && imc < 34.9) {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc).replace(".",",")+"\nObesidade (Grau I)");
        } else if (imc > 35.0 && imc < 39.9) {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc).replace(".",",")+"\nObesidade Severa (Grau II)");
        }else {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc).replace(".",",")+"\nObesidade Mórbida (Grau III)");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();

        if (itemID == R.id.ic_limpar){
            binding.editPeso.setText("");
            binding.editAltura.setText("");
            binding.txtResultado.setText("");
        }
        return super.onOptionsItemSelected(item);
    }
}