package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="pets")
public class Pet extends BaseEntity {

    @Column(name="name")
    public String name;

    @ManyToOne
    @JoinColumn(name="type_id")
    public PetType petType;

    @Column(name="birth_date")
    public LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name="owner_id")
    public Owner owner;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pet")
    public Set<Visit> visits=new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
