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
@Builder
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


}
