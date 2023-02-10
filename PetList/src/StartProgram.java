import java.util.List;
import java.util.Scanner;

import controller.PetListHelper;
import model.Pets;

/**
 * @author stephaniesink - sisink CIS175 - Spring 2022 Feb 8, 2023
 */
public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static PetListHelper lih = new PetListHelper();

	private static void addAPet() {
		// TODO Auto-generated method stub
		System.out.print("Enter an animal: ");
		String animal = in.nextLine();
		System.out.print("Enter a breed: ");
		String breed = in.nextLine();

		Pets toAdd = new Pets(animal, breed);
		lih.insertPet(toAdd);

	}

	private static void deleteAPet() {
		// TODO Auto-generated method stub
		System.out.print("Enter the animal to delete: ");
		String animal = in.nextLine();
		System.out.print("Enter the breed to delete: ");
		String breed = in.nextLine();

		Pets toDelete = new Pets(animal, breed);
		lih.deletePet(toDelete);

	}

	private static void editAPet() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Animal");
		System.out.println("2 : Search by Breed");
		int searchBy = in.nextInt();
		in.nextLine();

		List<Pets> foundPet;
		if (searchBy == 1) {
			System.out.print("Enter the animal type: ");
			String animalType = in.nextLine();
			foundPet = lih.searchForPetByAnimal(animalType);
		} else {
			System.out.print("Enter the breed name: ");
			String breedName = in.nextLine();
			foundPet = lih.searchForPetByBreed(breedName);

		}

		if (!foundPet.isEmpty()) {
			System.out.println("Found Results.");
			for (Pets l : foundPet) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Pets toEdit = lih.searchForPetById(idToEdit);
			System.out.println("Retrieved " + toEdit.getBreed() + " from " + toEdit.getAnimal());
			System.out.println("1 : Update Animal");
			System.out.println("2 : Update Breed");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Animal: ");
				String newAnimal = in.nextLine();
				toEdit.setAnimal(newAnimal);
			} else if (update == 2) {
				System.out.print("New Breed: ");
				String newBreed = in.nextLine();
				toEdit.setBreed(newBreed);
			}

			lih.updatePet(toEdit);

		} else {
			System.out.println("---- No results found ----");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runPetList();

	}

	public static void runPetList() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our pet list! ---");
		while (goAgain) {
			System.out.println("*  Make a selection:");
			System.out.println("*  1 -- Add a pet");
			System.out.println("*  2 -- Edit a pet");
			System.out.println("*  3 -- Delete a pet");
			System.out.println("*  4 -- View the pet list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAPet();
			} else if (selection == 2) {
				editAPet();
			} else if (selection == 3) {
				deleteAPet();
			} else if (selection == 4) {
				viewTheList();
			} else {
				lih.cleanUp();
				System.out.println("   Have a good day!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<Pets> allPets = lih.showAllPets();
		for (Pets singlePet : allPets) {
			System.out.println(singlePet.returnPetInfo());
		}

	}
}
