package forHonor;
// Generated 12-ene-2020 16:22:18 by Hibernate Tools 5.0.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Esta clase define los Personajes del juego For Honor
 */
@Entity
@Table(name = "personaje", catalog = "for_honor")
public class Personaje implements java.io.Serializable {

	/*
	 * Atributos de clase
	 */
	private int personajeId;
	private Faccion faccion;
	private String nombrePersonaje;
	private Integer ataque;
	private Integer defensa;

	/*
	 * Constructor vacío
	 */
	public Personaje() {
	}

	/*
	 * Constructor al que se le pasa el ID solamente
	 */
	public Personaje(int personajeId) {
		this.personajeId = personajeId;
	}

	/*
	 * Constructor con el que se rellenan todos los campos
	 */
	public Personaje(int personajeId, Faccion faccion, String nombrePersonaje, Integer ataque, Integer defensa) {
		this.personajeId = personajeId;
		this.faccion = faccion;
		this.nombrePersonaje = nombrePersonaje;
		this.ataque = ataque;
		this.defensa = defensa;
	}

	/*
	 * Getters y Setters
	 */
	@Id
	@Column(name = "personaje_id", unique = true, nullable = false)
	public int getPersonajeId() {
		return this.personajeId;
	}

	public void setPersonajeId(int personajeId) {
		this.personajeId = personajeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faccion_id")
	public Faccion getFaccion() {
		return this.faccion;
	}

	public void setFaccion(Faccion faccion) {
		this.faccion = faccion;
	}

	@Column(name = "nombre_personaje", length = 15)
	public String getNombrePersonaje() {
		return this.nombrePersonaje;
	}

	public void setNombrePersonaje(String nombrePersonaje) {
		this.nombrePersonaje = nombrePersonaje;
	}

	@Column(name = "ataque")
	public Integer getAtaque() {
		return this.ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	@Column(name = "defensa")
	public Integer getDefensa() {
		return this.defensa;
	}

	public void setDefensa(Integer defensa) {
		this.defensa = defensa;
	}

	/*
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return "Personaje con ID " + personajeId + " y nombre " + nombrePersonaje + ":\n" +
				"\t- Faccion: "+ faccion.getNombreFaccion() + "\n" +
				"\t- Ataque: " + ataque + "\n" +
				"\t- Defensa: " + defensa + "\n";
	}
}
