package program;

import forHonor.Faccion;
import forHonor.Personaje;
/*
 * Esta clase hace de puente entre la clase Main y la clase HibernateManager.
 * Tiene los metodos definidos que piden input de usuario para gestionar cada
 * faccion o personaje.
 */
public class BridgeClass {
	
	/*
	 * Metodo que imprime las instancias que haya persistidas de la clase que se le pase por parametro.
	 */
	public static <T> void printInstance(Class<T> classType) {
		for(T t : HibernateManager.getInstancesList(classType)) {
			System.out.println(t);
		}
	}
	
	/*
	 * Metodo que pide a que personaje cambiar el ataque y llama al metodo correspondiente
	 * de HibernateManager
	 */
	public static void modificaAtaque() {
		String text = "Introduce el ID del personaje al que se le modificará el ataque: ";
		Personaje p = (Personaje) getInstanceFromUserInput(Personaje.class, text);
		if(p == null) {
			System.out.println("¡ERROR! ¡Este personaje no existe!");
			return;
		}
		System.out.println("Introduce el ataque nuevo: ");
		int ataque = Main.validateIntInput();
		p.setAtaque(ataque);
		HibernateManager.updateInstance(p);
	}
	
	/*
	 * Metodo que devuelve una instancia que el usuario indique mediante el ID.
	 * El parametro de text es un String que se imprimirá pidiendo lo que hay que hacer
	 * con el input.
	 */
	public static <T> Object getInstanceFromUserInput(Class<T> classType, String text) {
		printInstance(classType);
		System.out.println(text);
		int id = Main.validateIntInput();
		return HibernateManager.getInstance(classType, id);
	}
	
	/*
	 * Metodo que pide que faccion eliminar y hace las comprobaciones antes de llamar
	 * al metodo de eliminar de HibernateManager
	 */
	public static void deleteFaccion() {
		String text = "Introduce el ID de la facción a eliminar: ";
		Faccion f = (Faccion) getInstanceFromUserInput(Faccion.class, text);
		if(f == null) {
			System.out.println("¡ERROR! ¡La facción no existe!");
			return;
		}
		if(HibernateManager.isFaccionEmpty(f)) {
			HibernateManager.deleteInstance(f);
		}else {
			System.out.println("¡ERROR! ¡La facción debe estar vacía para poder ser eliminada!");
		}
	}
	
	/*
	 * Metodo que pide que personaje eliminar y llama al metodo para eliminar
	 * instancias de HibernateManager
	 */
	public static void deletePersonaje() {
		Personaje p = (Personaje) BridgeClass.getInstanceFromUserInput(Personaje.class, "Introduce el ID del personaje a borrar: ");
		if(p == null) {
			System.out.println("¡ERROR! ¡Este personaje no existe!");
			return;
		}
		HibernateManager.cleanFaccionList(p.getFaccion(), p);
		HibernateManager.deleteInstance(p);
	}
	
	/*
	 * Metodo que pide el personaje al que se le cambiara la faccion y la faccion nueva y llama al metodo
	 * de HibernateManager que efectuara el cambio
	 */
	public static void modificaPersonajeFaccion() {
		String personajeText = "Introduce el ID del personaje al que se le va a modificar la facción:";
		String faccionText = "Introduce el ID de la nueva facción del personaje: ";
		Personaje p = (Personaje) getInstanceFromUserInput(Personaje.class, personajeText);
		if(p == null) {
			System.out.println("¡ERROR! ¡Este personaje no existe!");
			return;
		}
		Faccion f = (Faccion) getInstanceFromUserInput(Faccion.class, faccionText);
		if(f == null) {
			System.out.println("¡ERROR! ¡Esta facción no existe!");
			return;
		}
		HibernateManager.updatePersonajeFaccion(p, f);
	}
}
