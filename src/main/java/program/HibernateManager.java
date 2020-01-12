package program;

import java.util.ArrayList;

import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import forHonor.Faccion;
import forHonor.Personaje;
/*
 * Clase que contiene los metodos que gestionan Hibernate, así como la Session
 */
public class HibernateManager {

	// Declaramos objeto Session
	private static Session session;
	
	/*
	 * Metodo que inicializa la sesion
	 */
	public static void start() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	/*
	 * Metodo que elimina un personaje de la lista de personajes de la faccion.
	 * Util a la hora de eliminar un personaje, que este ya no este en la lista
	 * de la faccion.
	 */
	public static void cleanFaccionList(Faccion f, Personaje p) {
		f.getPersonajes().remove(p);
	}
	
	/*
	 * Metodo que retorna un ArrayList con todas las instancias que haya de una clase persistida.
	 */
	public static <T>ArrayList<T> getInstancesList(Class<T> classType){
		Query q = session.createQuery("from " + classType.getName());
		return (ArrayList<T>) q.getResultList();
	}
	
	/*
	 * Metodo que retorna una instancia de una clase persistida teniendo el id
	 */
	public static <T> Object getInstance(Class<T> classType, int id){
		return session.get(classType, id);
	}
	
	/*
	 * Metodo que actualiza una instancia que se le pasa por parametro
	 */
	public static void updateInstance(Object o) {
		Transaction trx = session.beginTransaction();
		session.update(o);
		trx.commit();
	}
	
	/*
	 * Metodo que elimina una instancia que se le pase por parametro
	 */
	public static void deleteInstance(Object o) {
		Transaction trx = session.beginTransaction();
		session.remove(o);
		trx.commit();
	}
	
	/*
	 * Metodo que comprueba si una faccion esta vacía (es decir, si su Set de personajes
	 * está vacío).
	 * Util para poder comprobar una faccion si esta va a ser eliminada.
	 */
	public static boolean isFaccionEmpty(Faccion f) {
		if(f.getPersonajes().isEmpty()) {
			return true;
		}
		return false;
	}
	
	/*
	 * Metodo que ejecuta el Stored Procedure guardado en la base de datos
	 * que cambia de faccion un personaje.
	 */
	public static void updatePersonajeFaccion(Personaje p, Faccion f) {
		StoredProcedureQuery query = session
			    .createStoredProcedureQuery("Change_Faction")
			    .registerStoredProcedureParameter(1, Integer.class, 
			        ParameterMode.IN)
			    .registerStoredProcedureParameter(2, Integer.class, 
			        ParameterMode.IN)
			    .setParameter(1, p.getPersonajeId())
			    .setParameter(2, f.getFaccionId());
		query.execute();
		session.refresh(p);
	}
	
	/*
	 * Metode que finalitza la sessio i la SessionFactory
	 */
	public static void finish() {
		session.close();
		HibernateUtil.getSessionFactory().close();
	}
}
