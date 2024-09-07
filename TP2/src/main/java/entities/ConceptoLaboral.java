package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "concepto_laboral")
public class ConceptoLaboral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;

	@Getter @Setter
	String nombre;

	@Getter @Setter
	boolean laborable;

	@Getter @Setter
	private Integer hs_minimo;

	@Getter @Setter
	private Integer hs_maximo;

}
