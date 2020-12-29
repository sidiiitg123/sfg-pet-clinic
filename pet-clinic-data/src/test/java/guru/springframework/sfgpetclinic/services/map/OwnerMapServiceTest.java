package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId=1L;
    final String lastName="Smith";

    @BeforeEach
    void setUp() {
        ownerMapService=new OwnerMapService(new PetTypeMapService(),new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners=ownerMapService.findAll();
        assertEquals(1,owners.size());
    }

    @Test
    void findById() {
        Owner owner=ownerMapService.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id=2L;
        Owner o2=Owner.builder().id(id).build();
        Owner savedOwner=ownerMapService.save(o2);
        assertEquals(id,savedOwner.getId());


    }

    @Test
    void saveNoId(){
        Owner saveOwner=ownerMapService.save(Owner.builder().build());

        assertNotNull(saveOwner);
        assertNotNull(saveOwner.getId());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());

    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
       Owner smith=ownerMapService.findByLastName(lastName);
       assertNotNull(smith);
       assertEquals(ownerId,smith.getId());

    }
    @Test
    void findByLastNameNotFound() {
        Owner smith=ownerMapService.findByLastName("foo");
        assertNull(smith);
    }
}
