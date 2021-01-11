package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="pets")
public class Pet extends BaseEntity {


    @Builder
    public Pet(Long id, String name, PetType petType, LocalDate birthDate, Owner owner, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.birthDate = birthDate;
        this.owner = owner;
        this.visits = visits;
    }

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


}
