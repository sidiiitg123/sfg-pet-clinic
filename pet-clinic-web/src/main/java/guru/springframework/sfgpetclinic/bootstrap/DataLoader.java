package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0 ){
            LoadData();
        }

    }

    private void LoadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

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

        Visit catVisit=new Visit();
        catVisit.setPet(shivaniCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("sneezy kitty");
        visitService.save(catVisit);


        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("sab thik se ho gaya no error");
    }
}
