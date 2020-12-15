package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {


        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner o1= new Owner();
        o1.setFirstName("sudhanshu");
        o1.setLastName("upadhyay");
        o1.setAddress("123 Brickerel");
        o1.setCity("Miami");
        o1.setTelephone("1231231234");

        Pet mikesPet=new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(o1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("rosco");
        o1.getPets().add(mikesPet);

        ownerService.save(o1);

        Owner o2= new Owner();
        o2.setFirstName("shivani");
        o2.setLastName("tripathi");
        o2.setAddress("123 Brickerel");
        o2.setCity("Miami");
        o2.setTelephone("1231231234");

        Pet shivaniCat=new Pet();
        shivaniCat.setPetType(savedCatPetType);
        shivaniCat.setOwner(o2);
        shivaniCat.setBirthDate(LocalDate.now());
        shivaniCat.setName("just cat");
        o2.getPets().add(shivaniCat);

        ownerService.save(o2);

        System.out.println("sab thik se ho gaya no error");

    }
}
