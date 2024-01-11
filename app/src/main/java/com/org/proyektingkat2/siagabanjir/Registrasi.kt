package com.org.proyektingkat2.siagabanjir


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.org.proyektingkat2.db.DatabaseHandler
import com.org.proyektingkat2.model.User
import com.org.proyektingkat2.siagabanjir.databinding.RegisterActivityBinding


class Registrasi: AppCompatActivity() {
    private lateinit var binding: RegisterActivityBinding
    private lateinit var databaseHandler: DatabaseHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= RegisterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        databaseHandler = DatabaseHandler(this)
        binding.registrasi.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val namaLengkap = binding.namaLengkapInput.text.toString()
            val noTlp = binding.noTelpInput.text.toString()
            val alamat = binding.alamatInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val konfirmPassword = binding.konfirmPasswordInput.text.toString()
            if (
                isValidEmail(email) &&
                isValidNamaLengkap(namaLengkap) &&
                isValidPhone(noTlp) &&
                isValidAlamat(alamat) &&
                isValidPassword(password, konfirmPassword)
            ) {
                val user = User(0, email, namaLengkap, noTlp, alamat, password)
                val userId = databaseHandler.addUser(user)
                if (userId != -1L) {
                    val intent = Intent(this, UserListActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Registrasi gagal", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Mohon lengkapi data dengan benar", Toast.LENGTH_SHORT).show()

            }
        }

    }
    private fun isValidEmail(email: String): Boolean {
        return if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            true
        } else {
            binding.emailInput.error = "Masukkan email dengan format yang benar"
            false
        }
    }

    private fun isValidNamaLengkap(namaLengkap: String): Boolean {
        return if (namaLengkap.isNotEmpty() && !namaLengkap.matches(Regex(".*\\d.*")) && !namaLengkap.matches(Regex(".*[^\\p{L}\\s].*"))) {
            true
        } else {
            binding.namaLengkapInput.error = "Nama lengkap tidak valid"
            false
        }
    }
    private fun isValidAlamat(alamat: String): Boolean {
        return if (alamat.isNotEmpty()) {
            true
        } else {
            binding.alamatInput.error = "Harap masukkan alamat anda"
            false
        }
    }

    private fun isValidPhone(phone: String): Boolean {
        return if (phone.isNotEmpty() && phone.matches(Regex("[0-9]+")) && phone.length >= 10) {
            true
        } else {
            binding.noTelpInput.error = "Masukkan nomor telepon dengan format yang benar"
            false
        }
    }



    private fun isValidPassword(password: String, konfirmPassword: String): Boolean {
        return if (password.isNotEmpty() && password.length >= 6 && password == konfirmPassword) {
            true
        } else {
            binding.passwordInput.error = "Masukkan password dengan panjang minimal 6 karakter"
            binding.konfirmPasswordInput.error = "Konfirmasi password tidak cocok"
            false
        }
    }

}


