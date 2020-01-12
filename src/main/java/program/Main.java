package program;

import java.util.InputMismatchException;
import java.util.Scanner;

import forHonor.Personaje;
/**
 * Aquesta classe es la principal amb el m�tode Main.
 * Per a fer �til el programa, es pot executar el fitxer SQL al SGBD
 * que es troba a dins del projecte amb el nom 'for_honor.sql'.
 * @author erikc
 *
 */
public class Main {
	// Necessitarem un Scanner per demanar input de l'usuari
	public static Scanner sc = new Scanner(System.in);
	
	/*
	 * Metode que comprova si l'input introduit per l'usuari es un numero enter
	 */
	public static int validateIntInput() {
		int n;
		do {
			try {
				n = sc.nextInt();
				sc.nextLine();
				return n;
			}catch(InputMismatchException e) {
				System.out.println("�ERROR! �Debes introducir un n�mero!");
				sc.nextLine();
			}
		}while(true);
	}
	
	/*
	 * Metode que imprimeix el menu de l'aplicacio.
	 */
	private static boolean menu() {
		System.out.println("Gesti�n FOR HONOR:");
		System.out.println("\t1 - Listar todos los personajes.");
		System.out.println("\t2 - Modificar ataque de un personaje.");
		System.out.println("\t3 - Borrar un personaje.");
		System.out.println("\t4 - Borrar una facci�n(solo si est� vac�a).");
		System.out.println("\t5 - Cambiar personaje de facci�n.");
		System.out.println("\t6 - Salir.");
		return opcion();
	}
	
	/*
	 * Metode que crida altres metodes de la classe pont (BridgeClass) depenent de
	 * la opci� escollida per l'usuari.
	 */
	private static boolean opcion() {
		switch(validateIntInput()) {
		default:
			System.out.println("�Opci�n incorrecta!");
			break;
		case 1:
			BridgeClass.printInstance(Personaje.class);
			break;
		case 2:
			BridgeClass.modificaAtaque();
			break;
		case 3:
			BridgeClass.deletePersonaje();
			break;
		case 4:
			BridgeClass.deleteFaccion();
			break;
		case 5:
			BridgeClass.modificaPersonajeFaccion();
			break;
		case 6:
			System.out.println("�Hasta pronto!");
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		HibernateManager.start();
		boolean end;
		do {
			end = menu();
		}while(!end);
		sc.close();
		HibernateManager.finish();
	}
}
