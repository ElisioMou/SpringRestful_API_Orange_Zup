package br.com.orange.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    @Size(min = 3, message = "Nome deve conter ao menos 3 caracteres")
    private String Nome;

    @NotBlank
    @Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$",
            message = "Email está com o formato incorreto")
    private String Email;

    @CPF(message = "CPF inválido")
    private String CPF;

    @NotBlank
    @Past (message = "Data deve ser anterior a 2003")
    @Pattern (regexp = "dd/MM/yyyy", message = "Data está no formato incorreto")
    private String DataNasc;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getDataNasc() {
        return DataNasc;
    }

    public void setDataNasc(String dataNasc) { DataNasc = dataNasc; }
}
