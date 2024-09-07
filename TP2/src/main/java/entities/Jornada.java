package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "jornada")
public class Jornada {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private Integer nroDocumento;

    @Getter @Setter
    private String nombreCompleto;

    @Getter @Setter
    private String fecha;

    @Getter @Setter
    private String concepto;

    @Getter @Setter
    private Integer horasTrabajadas;

}
