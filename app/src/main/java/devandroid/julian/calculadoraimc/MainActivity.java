package devandroid.julian.calculadoraimc;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

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

        pesoConvertido = Float.parseFloat(edit_peso);
        alturaConvertida = Float.parseFloat(edit_altura);
        imc = pesoConvertido / (alturaConvertida * alturaConvertida);

        DecimalFormat df = new DecimalFormat("####0.00");

        if (imc < 18.5){
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc)+"\nPeso Baixo");
        } else if (imc > 18.5 && imc < 24.9) {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc)+"\nPeso Normal");
        } else if (imc > 25.0 && imc < 29.9) {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc)+"\nSobrepeso");
        } else if (imc > 30.0 && imc < 34.9) {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc)+"\nObesidade (Grau I)");
        } else if (imc > 35.0 && imc < 39.9) {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc)+"\nObesidade Severa (Grau II)");
        }else {
            binding.txtResultado.setText("Seu IMC é: "+df.format(imc)+"\nObesidade Mórbida (Grau III)");
        }
    }
}