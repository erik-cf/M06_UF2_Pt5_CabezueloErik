package forHonor;
// Generated 12-ene-2020 16:22:18 by Hibernate Tools 5.0.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Esta clase define las facciones
 */
@Entity
@Table(name = "faccion", catalog = "for_honor")
public class Faccion implements java.io.Serializable {

	/*
	 * Atributos de clase
	 */
	private int faccionId;
	private String nombreFaccion;
	private String lore;
	private Set<Personaje> personajes = new HashSet<Personaje>(0);

	/*
	 * Constructor vacío
	 */
	public Faccion() {
	}

	/*
	 * Constructor con solo ID
	 */
	public Faccion(int faccionId) {
		this.faccionId = faccionId;
	}

	/*
	 * constructor con todos los campos
	 */
	public Faccion(int faccionId, String nombreFaccion, String lore, Set<Personaje> personajes) {
		this.faccionId = faccionId;
		this.nombreFaccion = nombreFaccion;
		this.lore = lore;
		this.personajes = personajes;
	}

	/*
	 * Getters y Setters
	 */
	@Id
	@Column(name = "faccion_id", unique = true, nullable = false)
	public int getFaccionId() {
		return this.faccionId;
	}

	public void setFaccionId(int faccionId) {
		this.faccionId = faccionId;
	}

	@Column(name = "nombre_faccion", length = 15)
	public String getNombreFaccion() {
		return this.nombreFaccion;
	}

	public void setNombreFaccion(String nombreFaccion) {
		this.nombreFaccion = nombreFaccion;
	}

	@Column(name = "lore", length = 65535)
	public String getLore() {
		return this.lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "faccion")
	public Set<Personaje> getPersonajes() {
		return this.personajes;
	}

	public void setPersonajes(Set<Personaje> personajes) {
		this.personajes = personajes;
	}

	/*
	 * Metodo toString
	 */
	@Override
	public String toString() {
		return "Faccion con ID " + faccionId + " y nombre " + nombreFaccion + ": \n" +
				"\t- Lore: "+ lore + "\n" +
				"\t- Personajes: \n" + getPersonajesString();
	}
	
	/*
	 * Metodo que lista los personajes de una faccion
	 */
	@Transient
	public String getPersonajesString() {
		String text = "";
		for(Personaje p : personajes) {
			text = text + "\t\t- " + p.getNombrePersonaje() + "\n";
		}
		return text;
	}

}
