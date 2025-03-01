package online.clugo19.examen_cesar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener referencias a los elementos de la UI
        val etHoras = findViewById<EditText>(R.id.etHoras)
        val etTarifa = findViewById<EditText>(R.id.etTarifa)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvSalarioBruto = findViewById<TextView>(R.id.tvSalarioBruto)
        val tvDeduccionINSS = findViewById<TextView>(R.id.tvDeduccionINSS)
        val tvSalarioNeto = findViewById<TextView>(R.id.tvSalarioNeto)

        // Asignar evento al botón
        btnCalcular.setOnClickListener {
            calcularSalario(etHoras, etTarifa, tvSalarioBruto, tvDeduccionINSS, tvSalarioNeto)
        }
    }

    private fun calcularSalario(etHoras: EditText, etTarifa: EditText, tvSalarioBruto: TextView, tvDeduccionINSS: TextView, tvSalarioNeto: TextView) {
        val horasStr = etHoras.text.toString().trim()
        val tarifaStr = etTarifa.text.toString().trim()

        // Validar que los valores no sean vacíos
        if (horasStr.isEmpty() || tarifaStr.isEmpty()) {
            Toast.makeText(this, "Ingrese todos los valores requeridos", Toast.LENGTH_SHORT).show()
            return
        }

        val horas = horasStr.toDoubleOrNull()
        val tarifa = tarifaStr.toDoubleOrNull()

        // Validar que sean números válidos
        if (horas == null || tarifa == null) {
            Toast.makeText(this, "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show()
            return
        }

        // Calcular salario
        val salarioBruto = horas * tarifa
        val deduccionINSS = salarioBruto * 0.07
        val salarioNeto = salarioBruto - deduccionINSS

        // Mostrar resultados
        tvSalarioBruto.text = "Salario Bruto: %.2f".format(salarioBruto)
        tvDeduccionINSS.text = "Deducción INSS (7%): %.2f".format(deduccionINSS)
        tvSalarioNeto.text = "Salario Neto: %.2f".format(salarioNeto)
    }
}
