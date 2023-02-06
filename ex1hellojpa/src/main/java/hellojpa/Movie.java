package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("M")
public class Movie extends Item{
    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
